package com.gavima_kanido.handler;

import java.io.IOException;

import com.gavima_kanido.controller.*;
import com.gavima_kanido.models.User;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageHandler {

    private static User stageUser = null;

    public static void changeToDashboard(Stage oldstage, Class actual_class) throws IOException {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/Dashboard.fxml"));
        loader.setController(new DashboardController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToTimeTracker(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/TimeTrackerOverview.fxml"));   
        loader.setController(new TimeTrackerOverviewController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToHoliday(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/Holiday.fxml"));   
        loader.setController(new HolidayController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToTeams(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/Teams.fxml"));   
        loader.setController(new TeamsController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToProjects(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/TimeTrackerProjects.fxml"));   
        loader.setController(new TimeTrackerProjectsController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void setUser(User user)
    {
        stageUser = user;
    }
}
