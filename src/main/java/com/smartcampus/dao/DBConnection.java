package com.smartcampus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://mysql-1b2e86f5-smartcampusserviceportal.b.aivencloud.com:10062/defaultdb?sslMode=REQUIRED";

    private static final String USER =
            "avnadmin";

    public static Connection getConnection() throws SQLException {

        String password = System.getenv("SMARTCAMPUS_DB_PASSWORD");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found.", e);
        }

        if (password == null || password.trim().isEmpty()) {
            throw new SQLException(
                "SMARTCAMPUS_DB_PASSWORD environment variable is not set in Render."
            );
        }

        System.out.println("Attempting database connection...");

        Connection connection =
                DriverManager.getConnection(URL, USER, password);

        System.out.println("DATABASE CONNECTION SUCCESSFUL");

        return connection;
    }
}