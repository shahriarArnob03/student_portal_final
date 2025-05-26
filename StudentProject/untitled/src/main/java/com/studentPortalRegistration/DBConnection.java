//package com.studentPortalRegistration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    private static final String URL      = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String USERNAME = "root";
//    private static final String PASSWORD = "admin";
//
//    public static Connection getConnection() throws SQLException {
//        // Load driver if needed:
//        // Class.forName("com.mysql.cj.jdbc.Driver");
//        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
//    }
//}

//
//package com.studentPortalRegistration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/student_portal";
//    private static final String USER = "root";
//    private static final String PASSWORD = "admin";
//
//    public static Connection getConnection() throws SQLException {
//        try {
//            // Load MySQL JDBC Driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found.", e);
//        }
//
//        // Return connection
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//}

//package com.studentPortalRegistration;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
//    private static final String USER = "your_mysql_username";
//    private static final String PASSWORD = "your_mysql_password";
//
//    public static Connection getConnection() throws SQLException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL driver class
//        } catch (ClassNotFoundException e) {
//            throw new SQLException("MySQL JDBC Driver not found.", e);
//        }
//        return DriverManager.getConnection(URL, USER, PASSWORD);
//    }
//}

package com.studentPortalRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://ep-raspy-paper-a1kskhm0-pooler.ap-southeast-1.aws.neon.tech/neondb?sslmode=require";
    private static final String USER = "neondb_owner";
    private static final String PASSWORD = "npg_iJ1h7kWCPsSg";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver"); // PostgreSQL driver class
        } catch (ClassNotFoundException e) {
            throw new SQLException("PostgreSQL JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}



