package com.studentPortalRegistration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT author_id, name, profile_picture, email FROM users";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        

        List<User> users = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SQL_SELECT_ALL_USERS)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getString("author_id"),
                        rs.getString("name"),
                        rs.getString("profile_picture"),
                        rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            throw new ServletException("Error loading users for admin", e);
        }

        req.setAttribute("users", users);
        req.getRequestDispatcher("adminDashboard.jsp")
                .forward(req, resp);
    }
}
