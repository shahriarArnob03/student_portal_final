//package com.studentPortalRegistration;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//import java.io.File;
//import java.io.IOException;
//import java.sql.*;
//
//
//
//@WebServlet("/deleteSubmission")
//public class DeleteSubmissionServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("userId") == null) {
//            resp.sendRedirect("login.jsp");
//            return;
//        }
//
//        int currentUserId = (int) session.getAttribute("userId"); // 🔁 correct type
//        String currentUserRole = (String) session.getAttribute("role"); // 🔁 correct key
//
//        String filePath = req.getParameter("filePath");
//        String studentIdParam = req.getParameter("studentId");
//
//        if (filePath == null || studentIdParam == null) {
//            resp.sendRedirect("viewSubmissions");
//            return;
//        }
//
//        int studentId;
//        try {
//            studentId = Integer.parseInt(studentIdParam);
//        } catch (NumberFormatException e) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID.");
//            return;
//        }
//
//        // 🔐 Check permission
//        if (!"admin".equals(currentUserRole) && currentUserId != studentId) {
//            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Unauthorized deletion attempt.");
//            return;
//        }
//
//        // 🗑️ Delete from database
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement("DELETE FROM submissions WHERE file_path = ?")) {
//            ps.setString(1, filePath);
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            throw new ServletException("Failed to delete submission from DB", e);
//        }
//
//        // 🧹 Delete physical file
//        String fullPath = getServletContext().getRealPath("/") + filePath;
//        File file = new File(fullPath);
//        if (file.exists()) file.delete();
//
//        // 🔄 Redirect back
//        if ("admin".equals(currentUserRole)) {
//            resp.sendRedirect("adminDashboard.jsp");
//        } else {
//            resp.sendRedirect("viewSubmissions");
//        }
//    }
//}

package com.studentPortalRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;

@WebServlet("/deleteSubmission")
public class DeleteSubmissionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        int    currentUserId   = (int)    session.getAttribute("userId");
        String currentUserRole = (String) session.getAttribute("role");
        int    submissionId;

        try {
            submissionId = Integer.parseInt(req.getParameter("submissionId"));
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid submission ID");
            return;
        }

        String filePath, ownerId;
        // 1) Lookup owner & path
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT s.file_path, u.author_id " +
                             "FROM submissions s JOIN users u ON s.user_id = u.id " +
                             "WHERE s.submission_id = ?")) {
            ps.setInt(1, submissionId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                filePath = rs.getString(1);
                ownerId  = rs.getString(2);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        // 2) Permission check
        if (!"admin".equals(currentUserRole) && !ownerId.equals(session.getAttribute("authorId"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Not allowed");
            return;
        }

        // 3) Delete DB row
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "DELETE FROM submissions WHERE submission_id = ?")) {
            ps.setInt(1, submissionId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("Failed to delete submission", e);
        }

        // 4) Delete file
        File f = new File(req.getServletContext().getRealPath("/") + filePath);
        if (f.exists()) f.delete();

        // 5) Redirect back
        if ("admin".equals(currentUserRole)) {
            resp.sendRedirect("adminDashboard.jsp");
        } else {
            resp.sendRedirect("viewSubmissions?studentId=" + ownerId);
        }
    }
}

