//
//
//package com.studentPortalRegistration;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//@WebServlet("/submitFile")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
//        maxFileSize = 1024 * 1024 * 10,               // 10MB
//        maxRequestSize = 1024 * 1024 * 50)            // 50MB
//public class SubmitFileServlet extends HttpServlet {
//    private static final String UPLOAD_DIR = "uploads";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "admin";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String role = (String) session.getAttribute("role");
//        Integer userId = (Integer) session.getAttribute("userId");
//
//        if (userId == null || !"student".equals(role)) {
//            response.sendRedirect("login.jsp");
//            return;
//        }
//
//        String submissionType = request.getParameter("submissionType");
//        Part filePart = request.getPart("file");
//        String fileName = extractFileName(filePart);
//
//        // Save file to uploads directory
//        String appPath = request.getServletContext().getRealPath("");
//        String savePath = appPath + File.separator + UPLOAD_DIR;
//        File fileSaveDir = new File(savePath);
//        if (!fileSaveDir.exists()) fileSaveDir.mkdir();
//
//        String fullPath = savePath + File.separator + fileName;
//        String relativePath = UPLOAD_DIR + "/" + fileName;  // Save this in DB
//
//        filePart.write(fullPath);
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
//            String sql = "INSERT INTO submissions (user_id, file_name, file_path, submission_type) VALUES (?, ?, ?, ?)";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, userId);
//            stmt.setString(2, fileName);
//            stmt.setString(3, relativePath); // Save relative path
//            stmt.setString(4, submissionType);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "Database error: " + e.getMessage());
//            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
//            return;
//        }
//
//        response.sendRedirect("submissionSuccess.jsp");  // Redirect to success page
//    }
//
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        for (String s : contentDisp.split(";")) {
//            if (s.trim().startsWith("filename")) {
//                return s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//        return "";
//    }
//}
//

package com.studentPortalRegistration;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/submitFile")
@MultipartConfig(fileSizeThreshold = 2 * 1024 * 1024, // 2MB
        maxFileSize       = 10 * 1024 * 1024, // 10MB
        maxRequestSize    = 50 * 1024 * 1024) // 50MB
public class SubmitFileServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        Integer userId = (Integer) session.getAttribute("userId");
        String role   = (String)  session.getAttribute("role");
        if (userId == null || !"student".equals(role)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String submissionType = req.getParameter("submissionType");
        Part filePart         = req.getPart("file");
        String originalName   = java.nio.file.Paths
                .get(filePart.getSubmittedFileName())
                .getFileName().toString();

        try (Connection conn = DBConnection.getConnection()) {
            // 1) Insert placeholder to get generated submission_id
            PreparedStatement insert = conn.prepareStatement(
                    "INSERT INTO submissions (user_id, file_name, file_path, submission_type) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            insert.setInt(1, userId);
            insert.setString(2, originalName);
            insert.setString(3, "");           // temp
            insert.setString(4, submissionType);
            insert.executeUpdate();

            ResultSet keys = insert.getGeneratedKeys();
            if (!keys.next()) throw new SQLException("No submission_id returned");
            int submissionId = keys.getInt(1);
            keys.close();
            insert.close();

            // 2) Build unique filename
            String ext = "";
            int dot = originalName.lastIndexOf('.');
            if (dot > 0) ext = originalName.substring(dot);
            String uniqueName = "sub_" + submissionId + "_" + System.currentTimeMillis() + ext;

            // 3) Save to disk
            String appPath = req.getServletContext().getRealPath("");
            File   uploadDir = new File(appPath, UPLOAD_DIR);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            File   dest = new File(uploadDir, uniqueName);
            filePart.write(dest.getAbsolutePath());

            // 4) Update record with real path
            PreparedStatement update = conn.prepareStatement(
                    "UPDATE submissions SET file_path = ? WHERE submission_id = ?");
            update.setString(1, UPLOAD_DIR + "/" + uniqueName);
            update.setInt(2, submissionId);
            update.executeUpdate();
            update.close();
        } catch (Exception e) {
            throw new ServletException("File upload failed", e);
        }

        resp.sendRedirect("submissionSuccess.jsp");
    }
}

