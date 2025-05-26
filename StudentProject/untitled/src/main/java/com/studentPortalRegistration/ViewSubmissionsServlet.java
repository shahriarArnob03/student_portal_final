//
//
//package com.studentPortalRegistration;
//
//import java.io.IOException;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.*;
//
//@WebServlet("/viewSubmissions")
//public class ViewSubmissionsServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session == null || session.getAttribute("authorId") == null) {
//            resp.sendRedirect("login.jsp");
//            return;
//        }
//
//        String studentId = req.getParameter("studentId");
//        if (studentId == null || studentId.trim().isEmpty()) {
//            studentId = (String) session.getAttribute("authorId");
//        }
//
//        List<Submission> subs = new ArrayList<>();
//        String sql = """
//            SELECT s.submission_id, s.file_name, s.file_path, s.submission_type, s.upload_date, u.author_id
//              FROM submissions s
//              JOIN users u ON s.user_id = u.id
//             WHERE u.author_id = ?
//            """;
//
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, studentId);
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    subs.add(new Submission(
//                            rs.getInt("submission_id"),
//                            rs.getString("file_name"),
//                            rs.getString("file_path"),
//                            rs.getString("submission_type"),
//                            rs.getTimestamp("upload_date"),
//                            rs.getString("author_id")
//                    ));
//                }
//            }
//        } catch (SQLException e) {
//            throw new ServletException("Error fetching submissions", e);
//        }
//
//        req.setAttribute("subs", subs);
//        req.getRequestDispatcher("/viewSubmissions.jsp").forward(req, resp);
//    }
//}

package com.studentPortalRegistration;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/viewSubmissions")
public class ViewSubmissionsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("authorId") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Check for ?studentId= in URL (for admins/other views)
        String studentId = req.getParameter("studentId");
        if (studentId == null || studentId.trim().isEmpty()) {
            studentId = (String) session.getAttribute("authorId");
        }

        List<Submission> subs = new ArrayList<>();
        String sql = """
            SELECT s.submission_id, s.file_name, s.file_path, s.submission_type, s.upload_date, u.author_id
              FROM submissions s
              JOIN users u ON s.user_id = u.id
             WHERE u.author_id = ?
            """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    subs.add(new Submission(
                            rs.getInt("submission_id"),
                            rs.getString("file_name"),
                            rs.getString("file_path"),
                            rs.getString("submission_type"),
                            rs.getTimestamp("upload_date"),
                            rs.getString("author_id")
                    ));
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Error fetching submissions", e);
        }

        req.setAttribute("subs", subs);
        req.setAttribute("studentId", studentId); // Optional: show who's being viewed
        req.getRequestDispatcher("/viewSubmissions.jsp").forward(req, resp);
    }
}

