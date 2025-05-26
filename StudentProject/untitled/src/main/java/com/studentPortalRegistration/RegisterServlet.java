//package com.studentPortalRegistration;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//@WebServlet("/register")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
//        maxFileSize = 1024 * 1024 * 10,      // 10MB
//        maxRequestSize = 1024 * 1024 * 50)   // 50MB
//public class RegisterServlet extends HttpServlet {
//    private static final String UPLOAD_DIR = "uploads";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "admin";
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String confirmPassword = request.getParameter("confirmPassword");
//        String authorId = request.getParameter("authorId");
//        Part profilePicturePart = request.getPart("profilePicture");
//
//        // পাসওয়ার্ড মিলছে কিনা চেক করা
//        if (!password.equals(confirmPassword)) {
//            request.setAttribute("error", "পাসওয়ার্ড মিলছে না!");
//            request.getRequestDispatcher("register.jsp").forward(request, response);
//            return;
//        }
//
//        // ছবি আপলোড এবং পাথ স্টোর করা
//        String profilePicturePath = null;
//        if (profilePicturePart != null && profilePicturePart.getSize() > 0) {
//            String fileName = extractFileName(profilePicturePart);
//            String appPath = request.getServletContext().getRealPath("");
//            String savePath = appPath + File.separator + UPLOAD_DIR;
//            File fileSaveDir = new File(savePath);
//            if (!fileSaveDir.exists()) {
//                fileSaveDir.mkdir();
//            }
//            profilePicturePath = savePath + File.separator + fileName;
//            profilePicturePart.write(profilePicturePath);
//            profilePicturePath = UPLOAD_DIR + "/" + fileName; // ডাটাবেসে রিলেটিভ পাথ স্টোর করা
//        }
//
//        // ডাটাবেসে ডাটা সেভ করা
//        try {
//            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            String sql = "INSERT INTO users (name, email, password, author_id, role, profile_picture) VALUES (?, ?, ?, ?, 'student', ?)";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, name);
//            stmt.setString(2, email);
//            stmt.setString(3, password);
//            stmt.setString(4, authorId);
//            stmt.setString(5, profilePicturePath);
//            stmt.executeUpdate();
//            stmt.close();
//            conn.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "ডাটাবেসে সমস্যা: " + e.getMessage());
//            request.getRequestDispatcher("registration.jsp").forward(request, response);
//            return;
//        }
//
//        response.sendRedirect("login.jsp");
//    }
//
//    private String extractFileName(Part part) {
//        String contentDisp = part.getHeader("content-disposition");
//        String[] items = contentDisp.split(";");
//        for (String s : items) {
//            if (s.trim().startsWith("filename")) {
//                return s.substring(s.indexOf("=") + 2, s.length() - 1);
//            }
//        }
//        return "";
//    }
//}

package com.studentPortalRegistration;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Read form fields
        String name     = request.getParameter("name");
        String email    = request.getParameter("email");
        String password = request.getParameter("password");
        String authorId = request.getParameter("authorId");

        // 2. Handle uploaded profile picture
        Part filePart = request.getPart("profilePicture");
        InputStream imageStream = null;
        if (filePart != null && filePart.getSize() > 0) {
            imageStream = filePart.getInputStream();
        }

        // 3. Store into database
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO users "
                    + "(author_id, name, email, password, profile_picture, role) "
                    + "VALUES (?, ?, ?, ?, ?, 'student')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, authorId);
            stmt.setString(2, name);
            stmt.setString(3, email);
            stmt.setString(4, password);

            if (imageStream != null) {
                stmt.setBinaryStream(5, imageStream, (int) filePart.getSize());
            } else {
                stmt.setNull(5, java.sql.Types.BINARY);
            }

            int rows = stmt.executeUpdate();
            stmt.close();

            if (rows > 0) {
                response.sendRedirect("registrationSuccess.jsp");
            } else {
                request.setAttribute("error", "Registration failed. Please try again.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }
}
