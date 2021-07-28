


package com.gavima_kanido.utils;

import java.sql.*;
import java.time.LocalDate;

import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;

public class DatabaseOperationUtil {

    public static String getPasswordToken(String email) throws SQLException {
        String token = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM userLogIn WHERE email = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                token = rs.getString("password");
            }

        } catch (SQLException  e) {
            System.err.println(e);
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return token;

    }

    public static String getUserRef(String email, String pwdToken) throws SQLException {
        String userRef = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM userLogIn WHERE email = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            //ps.setString(2, pwdToken);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                userRef = rs.getString("userRef");
            }
                
        } catch (SQLException  e) {
            System.err.println(e);
            return null;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return userRef;

    }


    public static int getPrivileges(String userRef) throws SQLException {
        int berechtigung = -1;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM privileges WHERE userRef = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userRef);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                berechtigung = rs.getInt("privilege");
            }
                
        } catch (SQLException  e) {
            System.err.println(e);
            return berechtigung;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return berechtigung;

    }

    public static User getPerson(String userRef) throws SQLException{
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM personaldata Where userRef = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userRef);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                
                // TO-DO add all set-methods from user
                // noch anzupassen an User-Klasse
                user = new User(rs.getString("userRef"), rs.getString("name"), rs.getString("lastName"), rs.getString("street"), rs.getString("city"), rs.getString("country"), rs.getString("superiorName"), rs.getString("department"), rs.getString("team"), rs.getInt("zip"), rs.getString("phoneNumber"), rs.getString("eMail")); //TO-DO add properties
            }
                
        } catch (SQLException  e) {
            System.err.println(e);
            return user;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return user;

    }

        public static int addProject(String projectName, String customer, String description, int budget) throws SQLException{
        Connection conn = null;
        PreparedStatement ps = null;
        int row = 0;
        String projectId = Double.toString(System.currentTimeMillis() / 1000L);


        if (projectName != null && customer != null && description != null && budget != 0) {

        try {
            String sql = "INSERT INTO projects (projectId, projectName, projectCustomer, projectDescription, projectBudget) VALUES (?, ?, ?, ?, ?);";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, projectId);
            ps.setString(2, projectName);
            ps.setString(3, customer);
            ps.setString(4, description);
            ps.setInt(5, budget);
            row = ps.executeUpdate();        

                
        } catch (SQLException  e) {
            System.err.println(e);
            return row;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        } else {}

        return row;

    }
 

}
