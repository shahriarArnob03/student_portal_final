////package com.studentPortalRegistration;
////
////import java.io.IOException;
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.PreparedStatement;
////import java.sql.ResultSet;
////import java.sql.SQLException;
////import javax.servlet.ServletException;
////import javax.servlet.annotation.WebServlet;
////import javax.servlet.http.HttpServlet;
////import javax.servlet.http.HttpServletRequest;
////import javax.servlet.http.HttpServletResponse;
////import javax.servlet.http.HttpSession;
////
////@WebServlet("/login")
////public class LoginServlet extends HttpServlet {
////    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_portal";
////    private static final String DB_USER = "root";
////    private static final String DB_PASS = "admin";
////
////    @Override
////    protected void doPost(HttpServletRequest request, HttpServletResponse response)
////            throws ServletException, IOException {
////        String authorId = request.getParameter("authorId");
////        String password = request.getParameter("password");
////
////
////
////        try {
////            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
////            String sql = "SELECT id, name, role, profile_picture FROM users WHERE author_id = ? AND password = ?";
////            PreparedStatement stmt = conn.prepareStatement(sql);
////            stmt.setString(1, authorId);
////            stmt.setString(2, password);
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                HttpSession session = request.getSession();
////                session.setAttribute("userId", rs.getInt("id"));
////                session.setAttribute("name", rs.getString("name"));
////                session.setAttribute("authorId", authorId);
////                session.setAttribute("role", rs.getString("role"));
////                session.setAttribute("profilePicture", rs.getString("profile_picture"));
////
////                // redirect by the role
////                if ("admin".equals(rs.getString("role"))) {
////                    response.sendRedirect("adminDashboard.jsp");
////                } else {
////                    response.sendRedirect("studentDashboard.jsp");
////                }
////            } else {
////                request.setAttribute("error", "Wrong Password!");
////                request.getRequestDispatcher("login.jsp").forward(request, response);
////            }
////
////            rs.close();
////            stmt.close();
////            conn.close();
////        } catch (SQLException e) {
////            e.printStackTrace();
////            request.setAttribute("error", "ডাটাবেসে সমস্যা: " + e.getMessage());
////            request.getRequestDispatcher("login.jsp").forward(request, response);
////        }
////    }
////}
//
//
//package com.studentPortalRegistration;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "admin";
//
//    // Handle GET request to redirect to login.jsp
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.sendRedirect("login.jsp");
//    }
//
//    // Handle POST request for login form submission
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String authorId = request.getParameter("authorId");
//        String password = request.getParameter("password");
//
//        try {
//            // Ensure JDBC driver is loaded (optional from JDBC 4.0+, but good practice)
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            String sql = "SELECT id, name, role, profile_picture FROM users WHERE author_id = ? AND password = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, authorId);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("userId", rs.getInt("id"));
//                session.setAttribute("name", rs.getString("name"));
//                session.setAttribute("authorId", authorId);
//                session.setAttribute("role", rs.getString("role"));
//                session.setAttribute("profilePicture", rs.getString("profile_picture"));
//
//                // Redirect based on role
//                if ("admin".equals(rs.getString("role"))) {
//                    response.sendRedirect("adminDashboard.jsp");
//                } else {
//                    response.sendRedirect("studentDashboard.jsp");
//                }
//            } else {
//                request.setAttribute("error", "Wrong Password!");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
//            }
//
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "JDBC Driver not found: " + e.getMessage());
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            request.setAttribute("error", "ডাটাবেসে সমস্যা: " + e.getMessage());
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }

//package com.studentPortalRegistration;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@WebServlet("/login")
//public class LoginServlet extends HttpServlet {
//    private static final String DB_URL  = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String DB_USER = "root";
//    private static final String DB_PASS = "admin";
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Simply show the login form
//        response.sendRedirect(request.getContextPath() + "/login.jsp");
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        String authorId = request.getParameter("authorId");
//        String password = request.getParameter("password");
//
//        try {
//            // Load driver and connect
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//
//            String sql = "SELECT id, name, role, profile_picture "
//                    + "FROM users WHERE author_id = ? AND password = ?";
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, authorId);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                // Set session attributes
//                HttpSession session = request.getSession();
//                session.setAttribute("userId", rs.getInt("id"));
//                session.setAttribute("userName", rs.getString("name"));
//                session.setAttribute("authorId", authorId);
//                session.setAttribute("role", rs.getString("role"));
//                session.setAttribute("profilePicture", rs.getString("profile_picture"));
//
//                // Redirect to the DashboardServlet
//                if ("admin".equals(rs.getString("role"))) {
//                    response.sendRedirect(request.getContextPath() + "/admin");
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/dashboard");
//                }
//            } else {
//                request.setAttribute("error", "Wrong ID or Password!");
//                request.getRequestDispatcher("/login.jsp").forward(request, response);
//            }
//
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (ClassNotFoundException e) {
//            throw new ServletException("JDBC Driver not found", e);
//        } catch (SQLException e) {
//            throw new ServletException("Database error during login", e);
//        }
//    }
//}

// new code testing //

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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Show the login page
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String authorId = request.getParameter("authorId");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            // SQL query for PostgreSQL - users table
            String sql = "SELECT id, name, role, profile_picture "
                    + "FROM users WHERE author_id = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, authorId);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // User authenticated - set session attributes
                HttpSession session = request.getSession();
                session.setAttribute("userId", rs.getInt("id"));
                session.setAttribute("userName", rs.getString("name"));
                session.setAttribute("authorId", authorId);
                session.setAttribute("role", rs.getString("role"));
                session.setAttribute("profilePicture", rs.getString("profile_picture"));

                // Redirect based on role
                if ("admin".equals(rs.getString("role"))) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    response.sendRedirect(request.getContextPath() + "/dashboard");
                }
            } else {
                // Invalid credentials
                request.setAttribute("error", "Wrong ID or Password!");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}
