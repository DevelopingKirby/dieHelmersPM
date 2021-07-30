package com.gavima_kanido.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.handler.TrackProjectsHandler;
import com.gavima_kanido.models.User;
import com.gavima_kanido.models.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TrackProjectsController {

    private User user;
    private List<Project> userProjects = new ArrayList<Project>();
    private TrackProjectsHandler trackProjectsHandler = new TrackProjectsHandler();


    public TrackProjectsController(User user) {
        this.user = user;
        this.userProjects = trackProjectsHandler.getProjects(user);
    }


    @FXML
    private Label lblUserRef;

    @FXML
    private Button topmenu_overview;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Label totalTimeSpent;

    @FXML
    private Button startTime;


    @FXML
    private Button stopTime;

    @FXML
    private Button btn_create_project;

    @FXML
    private ComboBox<String> comboBoxProject;

    @FXML
    private Label lblName;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblBudget;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblTimeInfo;

    @FXML
    private void comboAction(ActionEvent event) {


        for (Project p : userProjects) {
            if (p.getName().equals(comboBoxProject.getSelectionModel().getSelectedItem())) {
               
                lblName.setText(p.getName());
                lblCustomer.setText(p.getCustomer());
                lblBudget.setText(Integer.toString(p.getBudget()));
                lblDescription.setText(p.getDescription());
                totalTimeSpent.setText(trackProjectsHandler.getHoursWorkedOnProject(user, p) + " h");
            }
        }
    }


    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_overview) {
            StageHandler.changeToTimeOverview((Stage) topmenu_overview.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btn_create_project) {
            StageHandler.changeToCreateProject((Stage) btn_create_project.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == startTime) {
            int dbOperationWorked = 0;
            for (Project p : userProjects) {
                if (p.getName().equals(comboBoxProject.getSelectionModel().getSelectedItem())) {
                    lblTimeInfo.setTextFill(Color.GREEN);
                    lblTimeInfo.setText("Time tracking is running...");
                    
                        dbOperationWorked =  trackProjectsHandler.startTime(user, p);
                        if (dbOperationWorked == 0) {
                            lblTimeInfo.setText("Time tracking already started!");
                        }
    
                }
            }       
        }
        else if (event.getSource() == stopTime) {
            int dbOperationWorked = 0;
            for (Project p : userProjects) {
                if (p.getName().equals(comboBoxProject.getSelectionModel().getSelectedItem())) {
                    
                    lblTimeInfo.setTextFill(Color.YELLOW);
                    lblTimeInfo.setText("Time tracking stopped!");
                    
                        dbOperationWorked = trackProjectsHandler.stopTime(user, p);
                        if (dbOperationWorked == 1) {
                            totalTimeSpent.setText(trackProjectsHandler.getHoursWorkedOnProject(user, p) + " h");
                        }
                        else {
                            lblTimeInfo.setTextFill(Color.YELLOW);
                            lblTimeInfo.setText("Time tracking hasn't started!");
                        }

                }
            }
        }
    }

    public void populateProjectsList() {   
        for(Project p : userProjects) {
            comboBoxProject.getItems().add(p.getName());
        }
    }

    @FXML
    public void initialize(){
        populateProjectsList();
        
        lblUserRef.setText(user.getUserRef());
        if (user.getPrivileges() == 2){
            btn_create_project.setVisible(false);
        }


    }

}
