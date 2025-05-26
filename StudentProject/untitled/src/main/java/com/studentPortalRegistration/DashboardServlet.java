
package com.studentPortalRegistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final String SQL_SELECT_STUDENTS =
            "SELECT author_id, name, profile_picture FROM users WHERE role = 'student'";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        // 1) Auth check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("authorId") == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        // 2) Load all student profiles
        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_STUDENTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("author_id"),
                        rs.getString("name"),
                        rs.getString("profile_picture")
                ));
            }
        } catch (SQLException e) {
            getServletContext().log("Failed to load dashboard", e);
            req.setAttribute("error", "Unable to load dashboard at this time.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        // 3) Forward into JSP
        req.setAttribute("users", users);
        req.getRequestDispatcher("/studentDashboard.jsp")
                .forward(req, resp);
    }
}
