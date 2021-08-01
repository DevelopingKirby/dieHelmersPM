package com.gavima_kanido.utils;

import java.sql.*;

public class ConnectionUtil {
    
    public static Connection getConnection() {
        final String CONNSTRING = "jdbc:mysql://35.202.56.172/gavimakanido";
        final String USER = "gavimaUser";
        final String PASSWORD = "AT3v9IzCP05Yysjo";
        
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(CONNSTRING, USER, PASSWORD);            
            return conn;
        } catch (SQLException | ClassNotFoundException ex) {

            System.err.println("ConnectionUtil : "+ex.getMessage());
            return null;
        }
    }
}
