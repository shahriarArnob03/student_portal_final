package com.studentPortalRegistration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet("/profile-image")
public class ProfileImageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String authorId = request.getParameter("authorId");

        if (authorId == null || authorId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Author ID is required.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT profile_picture FROM users WHERE author_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, authorId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                byte[] imageBytes = rs.getBytes("profile_picture");

                if (imageBytes != null) {
                    response.setContentType("image/jpeg"); // or "image/png"
                    OutputStream out = response.getOutputStream();
                    out.write(imageBytes);
                    out.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "No image found.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found.");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error.");
        }
    }
}
