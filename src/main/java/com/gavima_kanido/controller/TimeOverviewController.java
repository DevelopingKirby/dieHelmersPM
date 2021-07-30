package com.gavima_kanido.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.handler.TimeOverviewHandler;
import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TimeOverviewController {

    private User user;
    private List<Project> userProjects = new ArrayList<Project>();
    private TimeOverviewHandler timeOverwiewHandler = new TimeOverviewHandler();
    

    public TimeOverviewController(User user) {
        this.user = user;
        this.userProjects = timeOverwiewHandler.getProjects(user);
        
    }

    @FXML
    private Button topmenu_trackprojects;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblUserRef;

    @FXML
    private Label lblHoursSum;

    @FXML
    private ListView<String> listProjects;

    @FXML
    private ListView<String> listCustomers;

    @FXML
    private ListView<String> listHoursSpend;


    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_trackprojects) {
            StageHandler.changeToTrackProjects((Stage) topmenu_trackprojects.getScene().getWindow(), getClass());
        }
    }

    public void populateProjectsTable() {

        for (Project p : userProjects) {
            listProjects.getItems().add(p.getName());
        }
    }

    public void populateProjectsCustomerTable() {

        for (Project p : userProjects) {
            listCustomers.getItems().add(p.getCustomer());
        }
    }
    public void populateHoursSpendTable() {

        for (Project p : userProjects) {
            listHoursSpend.getItems().add(timeOverwiewHandler.getHoursWorkedOnProject(user, p));
        }
    }



    @FXML
    public void initialize(){
        populateProjectsTable();
        populateProjectsCustomerTable();
        populateHoursSpendTable();
        lblHoursSum.setText(String.valueOf(timeOverwiewHandler.calcTotalHours(userProjects, user)));
        lblUserRef.setText(user.getUserRef());
    }
}
