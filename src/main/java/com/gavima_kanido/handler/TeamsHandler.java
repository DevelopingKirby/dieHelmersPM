package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

public class TeamsHandler {
    
    public List<User> getTeamMembers(User user) {
        List<User> employees = new ArrayList<User>();

        try {
            employees = DatabaseOperationUtil.getTeamMembers(user.getTeam());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return employees;
    }

    public List<Project> getProjects() {
        List<Project> projects = new ArrayList<Project>();

        try {
            projects = DatabaseOperationUtil.getProjects();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return projects;
    }

    public int addToProject(String[] split, Project p) {
        int dbOperationSuccessful = 0;
        if (p.getMember().isEmpty()) {
            try {
                dbOperationSuccessful =  DatabaseOperationUtil.addToProject(split[1], p.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        for (User u : p.getMember()) {
        
            if (u.getName().equals(split[1])) {
                        
            } else {
                try {
                    dbOperationSuccessful = DatabaseOperationUtil.addToProject(split[1], p.getId());        
                } catch (SQLException e) {
                    e.printStackTrace();
                }            
            }        
        }         
        return dbOperationSuccessful;
    }
    
    public int removeFromProject(String[] split, Project p) {
        int dbOperationSuccessful = 0;

        for (User u : p.getMember()) {

            if (u.getName().equals(split[1])) {
                try {
                   dbOperationSuccessful = DatabaseOperationUtil.removeFromProject(split[1], p.getId());                                    
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
        }   
        
        return dbOperationSuccessful;
    }
}
