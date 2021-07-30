package com.gavima_kanido.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TimeOverviewController {

    private User user;
    private List<Project> userProjects = new ArrayList<Project>();
    

    public TimeOverviewController(User user) {
        this.user = user;
        try {
            this.userProjects = DatabaseOperationUtil.getProjects(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
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
            try {
                listHoursSpend.getItems().add(String.valueOf(DatabaseOperationUtil.getHoursWorkedOnProject(user.getUserRef(), p.getId())));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public double calcTotalHours() {
        double totalHoursWorked = 0.0;

        for (Project p : userProjects) {
            try {
                totalHoursWorked += DatabaseOperationUtil.getHoursWorkedOnProject(user.getUserRef(), p.getId());
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return totalHoursWorked;
        
    }

    @FXML
    public void initialize(){
        populateProjectsTable();
        populateProjectsCustomerTable();
        populateHoursSpendTable();
        lblHoursSum.setText(String.valueOf(calcTotalHours()));
        lblUserRef.setText(user.getUserRef());
    }
}
