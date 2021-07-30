


package com.gavima_kanido.utils;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import java.time.temporal.ChronoUnit;

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

    public static List<User> getTeamMembers(String team) throws SQLException{
        List<User> user = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM personaldata Where team = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, team);
            rs =  ps.executeQuery();
            
            while (rs.next()) {
                
                // TO-DO add all set-methods from user
                // noch anzupassen an User-Klasse
                user.add(new User(rs.getString("userRef"), rs.getString("name"), rs.getString("lastName"), rs.getString("street"), rs.getString("city"), rs.getString("country"), rs.getString("superiorName"), rs.getString("department"), rs.getString("team"), rs.getInt("zip"), rs.getString("phoneNumber"), rs.getString("eMail"))); //TO-DO add properties
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

    public static List<Project> getProjects() throws SQLException{
        List<Project> projects = new ArrayList<Project>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM projects";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs =  ps.executeQuery();
            
            while (rs.next()) {

                projects.add(new Project(rs.getString("projectId"),rs.getString("projectName"), rs.getString("projectCustomer"), rs.getString("projectDescription"), rs.getInt("projectBudget"), DatabaseOperationUtil.getProjectMember(rs.getString("projectId"))));
            }
                
        } catch (SQLException  e) {
            System.err.println(e);
            return projects;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return projects;

    }

    public static List<Project> getProjects(String userRef) throws SQLException{
        List<Project> projects = new ArrayList<Project>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM projectAssignment WHERE userRef = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userRef);
            rs =  ps.executeQuery();
            
            while (rs.next()) {

                sql = "SELECT * FROM projects WHERE projectId = ?";
                

                ps = conn.prepareStatement(sql);
                ps.setString(1, rs.getString("projectRef"));
                ResultSet rs2 = ps.executeQuery();

                while (rs2.next()) {

                    projects.add(new Project(rs2.getString("projectId"),rs2.getString("projectName"), rs2.getString("projectCustomer"), rs2.getString("projectDescription"), rs2.getInt("projectBudget"), DatabaseOperationUtil.getProjectMember(rs2.getString("projectId"))));
                }
            
            }
                
        } catch (SQLException  e) {
            System.err.println(e);
            return projects;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return projects;

    }


    public static List<User> getProjectMember(String projectRef) throws SQLException{
        List<User> users = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM projectAssignment WHERE projectRef = ?";
            conn = ConnectionUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, projectRef);
            rs = ps.executeQuery();

            while (rs.next()) {
                users.add(DatabaseOperationUtil.getPerson(rs.getString("userRef")));
            }

                
        } catch (SQLException  e) {
            System.err.println(e);
            return users;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return users;

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

        public static int addToProject(String lastName, String projectRef) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            int row = 0;

            try {
                String sql2 = "SELECT userRef FROM personaldata WHERE lastName = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql2);
                ps.setString(1, lastName);
                ResultSet rs2 = ps.executeQuery();

                while (rs2.next()) {

                    String sql = "INSERT INTO projectAssignment (projectRef, userRef, startTime, totalTime ) VALUES (?, ?, ?, ?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, projectRef);
                    ps.setString(2, rs2.getString("userRef"));
                    ps.setInt(3, 0);
                    ps.setDouble(4, 0.0);
                    
                    row = ps.executeUpdate();        

                }


    
                    
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

            return row;
        }

        public static int removeFromProject(String lastName, String projectRef) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            int row = 0;

            try {
                String sql2 = "SELECT userRef FROM personaldata WHERE lastName = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql2);
                ps.setString(1, lastName);
                ResultSet rs2 = ps.executeQuery();

                while (rs2.next()) {

                    String sql = "DELETE FROM projectAssignment WHERE projectRef = ? AND userRef = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, projectRef);
                    ps.setString(2, rs2.getString("userRef"));
                    row = ps.executeUpdate();        

                }


    
                    
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

            return row;
        }


        public static int addStartTimeForProject(String userRef, String projectRef) throws SQLException {

            Connection conn = null;
            PreparedStatement ps = null;
            int row = 0;
            ResultSet rs = null;

            try {
                String sql = "SELECT * FROM projectAssignment WHERE userRef = ? AND projectRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                ps.setString(2, projectRef);
                rs = ps.executeQuery();

                while (rs.next()) {

                    if (rs.getInt("startTime") == 0) {
                        sql = "UPDATE projectAssignment SET startTime = ? WHERE userRef = ? AND projectRef = ?";
                        ps = conn.prepareStatement(sql);
                        ps.setLong(1, System.currentTimeMillis() / 1000L);
                        ps.setString(2, userRef);
                        ps.setString(3, projectRef);
                        row = ps.executeUpdate();
                    }
                }
   
                    
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

            return row;
        }

        public static int addEndTimeForProject(String userRef, String projectRef) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            int row = 0;
            ResultSet rs = null;

            try {
                String sql = "SELECT * FROM projectAssignment WHERE userRef = ? AND projectRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                ps.setString(2, projectRef);
                rs = ps.executeQuery();

                while (rs.next()) {

                    if (rs.getInt("startTime") != 0) {
                        sql = "UPDATE projectAssignment SET totalTime = ?, startTime = ? WHERE userRef = ? AND projectRef = ?";
                        ps = conn.prepareStatement(sql);
    
                        double hoursWorked = ((System.currentTimeMillis() / 1000L - rs.getInt("startTime"))/3600.0);
                        double hoursWorkedRounded = Math.round(hoursWorked * 100.0) / 100.0;
                        double hoursWorkedRoundedTotal = rs.getDouble("totalTime") + hoursWorkedRounded;
                        ps.setDouble(1, hoursWorkedRoundedTotal);
                        ps.setInt(2, 0);
                        ps.setString(3, userRef);
                        ps.setString(4, projectRef);
                        row = ps.executeUpdate();
                    }

                }
   
                    
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

            return row;
        }

		public static double getHoursWorkedOnProject(String userRef, String projectRef) throws SQLException {
            double hoursWorked = 0.0;
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;

            try {
                String sql = "SELECT totalTime FROM projectAssignment WHERE userRef = ? AND projectRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                ps.setString(2, projectRef);
                rs = ps.executeQuery();

                while (rs.next()) {

                    return rs.getDouble("totalTime");

                }
   
                    
            } catch (SQLException  e) {
                System.err.println(e);
                return hoursWorked;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }

            return hoursWorked;
		}

        public static List<Holiday> getHolidaysForUserRef(String userRef) throws SQLException {
            List<Holiday> holidays = new ArrayList<Holiday>();
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM holidayBookings WHERE userRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                rs = ps.executeQuery();

            while (rs.next()) {
                holidays.add(new Holiday(rs.getDate("startDate").toLocalDate(), rs.getDate("endDate").toLocalDate(), rs.getString("status")));
            }

                
            } catch (SQLException  e) {
                System.err.println(e);
                return holidays;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }

            return holidays;
        }

        public static int bookHoliday(String userRef, LocalDate startDate, LocalDate endDate) throws SQLException {
            Connection conn = null;
            PreparedStatement ps = null;
            int row = 0;
            ResultSet rs = null;

            try {
 
                String sql = "SELECT * FROM holidays WHERE userRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                rs = ps.executeQuery();

                while (rs.next()) {
                    if (rs.getInt("availableDays") > 0) {

                        int totalDaysBetween = (int) ChronoUnit.DAYS.between(startDate, endDate);

                        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
                            DayOfWeek d = date.getDayOfWeek();
                            
                            if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY) {
                                totalDaysBetween -= 1;
                            }

                        }

                        if (rs.getInt("availableDays") - totalDaysBetween < 0) {
                            row = 5;
                        } else {
    
                            sql = "INSERT INTO holidayBookings (userRef, startDate, endDate, status) VALUES (?, ?, ?, ?)";
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, userRef);
                            ps.setDate(2, Date.valueOf(startDate));
                            ps.setDate(3, Date.valueOf(endDate));
                            ps.setString(4, "Open");
                            row = ps.executeUpdate();
            
                            sql = "UPDATE holidays SET availableDays = ? WHERE userRef = ?";
                            ps = conn.prepareStatement(sql);
                            ps.setInt(1, rs.getInt("availableDays") - totalDaysBetween);
                            ps.setString(2, userRef);
                            row = ps.executeUpdate();
                        }
    
        
                    } else {
                        row = 4;
                    }
                }
     
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

            return row;
        }

        public static int getAvailableHolidays(String userRef) throws SQLException {
            int leftDays = -1;
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                String sql = "SELECT * FROM holidays WHERE userRef = ?";
                conn = ConnectionUtil.getConnection();
                ps = conn.prepareStatement(sql);
                ps.setString(1, userRef);
                rs = ps.executeQuery();

                while (rs.next()) {
                    leftDays = rs.getInt("availableDays");
                }

                
            } catch (SQLException  e) {
                System.err.println(e);
                return leftDays;
            } finally {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            }

            return leftDays;
        }
 

}
