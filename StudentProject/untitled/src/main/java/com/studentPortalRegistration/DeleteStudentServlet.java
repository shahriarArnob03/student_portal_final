package com.studentPortalRegistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles deletion of a student record (and optionally their uploads) by the admin.
 */
@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {

    private static final String SQL_DELETE_USER =
            "DELETE FROM users WHERE author_id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // (Optional) Ensure only admin can delete
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String authorId = req.getParameter("authorId");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE_USER)) {

            ps.setString(1, authorId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException("Failed to delete student", e);
        }

        // After deletion, return to admin dashboard
        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}

