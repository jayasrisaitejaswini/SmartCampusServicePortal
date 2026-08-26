package com.smartcampus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/smartcampus";

    private static final String USER =
            "root";

    private static final String PASSWORD =
            System.getenv("SMARTCAMPUS_DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (PASSWORD == null || PASSWORD.isEmpty()) {
            throw new SQLException(
                    "SMARTCAMPUS_DB_PASSWORD environment variable is not set.");
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}