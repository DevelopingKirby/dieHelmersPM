package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

public class TrackProjectsHandler {

    public List<Project> getProjects(User user) {
        List<Project> userProjects = new ArrayList<Project>();
        try {
             DatabaseOperationUtil.getProjects(user.getUserRef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userProjects;
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

    public int startTime(User user, Project p) {
        int dbOperationSuccessful = 0;
        try {
            dbOperationSuccessful = DatabaseOperationUtil.addStartTimeForProject(user.getUserRef(), p.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbOperationSuccessful;
    }

    public int stopTime(User user, Project p) {
        int dbOperationSuccessful = 0;
        try {
            dbOperationSuccessful = DatabaseOperationUtil.addEndTimeForProject(user.getUserRef(), p.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbOperationSuccessful;
    }

}
