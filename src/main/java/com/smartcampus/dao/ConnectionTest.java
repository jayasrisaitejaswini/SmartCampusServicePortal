package com.smartcampus.dao;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {

        try {

            Connection con = DBConnection.getConnection();

            System.out.println("================================");
            System.out.println("DATABASE CONNECTION SUCCESSFUL");
            System.out.println("Smart Campus database connected!");
            System.out.println("================================");

            con.close();

        } catch (Exception e) {

            System.out.println("DATABASE CONNECTION FAILED");
            e.printStackTrace();

        }
    }
}