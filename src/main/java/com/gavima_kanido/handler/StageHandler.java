package com.gavima_kanido.handler;

import java.io.IOException;
import java.util.List;

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

    public static void changeToTimeOverview(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/TimeOverview.fxml"));   
        loader.setController(new TimeOverviewController(stageUser));
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

    public static void changeToTrackProjects(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/TrackProjects.fxml"));   
        loader.setController(new TrackProjectsController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToPersonalData(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/PersonalData.fxml"));   
        loader.setController(new PersonalDataController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToEmployeeData(Stage oldstage, Class actual_class, User user) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/PersonalData.fxml"));
        PersonalDataController ctrData = new PersonalDataController(stageUser);
        loader.setController(ctrData);
        Scene scene = new Scene((Parent) loader.load());
        ctrData.setEmployeeData(user);
        stage.setScene(scene);
    }

    public static void changeToLoggedOut(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/LoggedOut.fxml"));
        LoggedOutController ctrLogOut = new LoggedOutController();
        loader.setController(ctrLogOut);
        Scene scene = new Scene((Parent) loader.load());
        ctrLogOut.setStage(stage);
        stage.setScene(scene);
        ctrLogOut.logOut();
    }

    public static void changeToCreateProject(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/CreateProject.fxml"));   
        loader.setController(new CreateProjectController(stageUser));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToLogIn(Stage oldstage, Class actual_class) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/Login.fxml"));   
        loader.setController(new LoginController());
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void changeToHolidayBookingRequest(Stage oldstage, Class actual_class, List<User> employees) throws IOException
    {
        Stage stage = oldstage;

        FXMLLoader loader = new FXMLLoader(actual_class.getResource("/fxml/HolidayBookingRequests.fxml"));   
        loader.setController(new HolidayBookingRequestsController(stageUser, employees));
        Scene scene = new Scene((Parent) loader.load());
        stage.setScene(scene);
    }

    public static void setUser(User user)
    {
        stageUser = user;
    }
}
