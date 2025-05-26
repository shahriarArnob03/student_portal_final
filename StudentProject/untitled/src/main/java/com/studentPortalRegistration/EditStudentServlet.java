package com.studentPortalRegistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handles displaying and processing the “Edit Student” form in the admin panel.
 */
@WebServlet("/editStudent")
public class EditStudentServlet extends HttpServlet {

    private static final String SQL_SELECT_USER =
            "SELECT name, email, profile_picture FROM users WHERE author_id=?";
    private static final String SQL_UPDATE_USER =
            "UPDATE users SET name=?, email=? WHERE author_id=?";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // (Optional) Ensure only admin can access
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String authorId = req.getParameter("authorId");
        User user = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_USER)) {

            ps.setString(1, authorId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User(
                            authorId,
                            rs.getString("name"),
                            rs.getString("profile_picture"),
                            rs.getString("email")
                    );
                }
            }

        } catch (SQLException e) {
            throw new ServletException("Failed to load student for editing", e);
        }

        req.setAttribute("user", user);
        req.getRequestDispatcher("editStudent.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // (Optional) Ensure only admin can perform update
        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        String authorId = req.getParameter("authorId");
        String name     = req.getParameter("name");
        String email    = req.getParameter("email");

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE_USER)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, authorId);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new ServletException("Failed to update student", e);
        }

        resp.sendRedirect(req.getContextPath() + "/admin");
    }
}
