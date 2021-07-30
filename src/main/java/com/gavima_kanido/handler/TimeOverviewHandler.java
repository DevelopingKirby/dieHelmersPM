package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

public class TimeOverviewHandler {
    

    public List<Project> getProjects(User user) {
        List<Project> userProjects = new ArrayList<Project>();
        try {
             DatabaseOperationUtil.getProjects(user.getUserRef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userProjects;
    }

    public double calcTotalHours(List<Project> projects, User user) {
        double totalHoursWorked = 0.0;

        for (Project p : projects) {
            try {
                totalHoursWorked += DatabaseOperationUtil.getHoursWorkedOnProject(user.getUserRef(), p.getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return totalHoursWorked;
        
    }

    public String getHoursWorkedOnProject(User user, Project p) {
        String returnStr = "";
        try {
           returnStr = String.valueOf(DatabaseOperationUtil.getHoursWorkedOnProject(user.getUserRef(), p.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnStr;
    }

}
