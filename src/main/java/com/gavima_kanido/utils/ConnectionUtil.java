package com.gavima_kanido.utils;

import java.sql.*;

public class ConnectionUtil {
    
    public static Connection getConnection() {
        final String CONNSTRING = "jdbc:mysql://sql11.freesqldatabase.com/sql11426166";
        final String USER = "sql11426166";
        final String PASSWORD = "6cergw8Izd";
        try {
            Connection conn = DriverManager.getConnection(CONNSTRING, USER, PASSWORD);            
            return conn;
        } catch (SQLException ex) {

            System.err.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }
    }
}
