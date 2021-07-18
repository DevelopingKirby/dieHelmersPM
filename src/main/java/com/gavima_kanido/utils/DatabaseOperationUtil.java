package com.gavima_kanido.utils;

import java.sql.*;

import com.gavima_kanido.models.Mitarbeiter;
import com.gavima_kanido.models.User;
import com.gavima_kanido.models.Vorgesetzer;


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

    public static User getPerson(String userRef, int privileges) throws SQLException{
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
                if (privileges == 1) {
                    user = new Vorgesetzer();
                } else if (privileges == 2) {
                    user = new Mitarbeiter();
                } else {

                }
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



    

}
