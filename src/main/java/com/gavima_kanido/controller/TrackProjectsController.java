
package com.gavima_kanido.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;
import com.gavima_kanido.models.Project;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TrackProjectsController {

    private User user;
    private List<Project> userProjects = new ArrayList<Project>();

    public TrackProjectsController(User user) {
        this.user = user;
        try {
            this.userProjects = DatabaseOperationUtil.getProjects(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private Button startTime;

    @FXML
    private Button stopTime;

    @FXML
    private Button projectDetails;

    @FXML
    private ComboBox<String> comboBoxProject;

    @FXML
    private Button topmenu_overview;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;


    @FXML
    private Button btn_create_project;

    @FXML
    private Label lblUserRef;

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == projectDetails) {
            for (Project p : userProjects) {

                if (comboBoxProject.getSelectionModel().getSelectedItem().equals(p.getName())) {
                    StageHandler.changeToProjectsDetails((Stage) projectDetails.getScene().getWindow(), getClass(), p);
                }
            }
        }
        else if (event.getSource() == topmenu_overview) {
            StageHandler.changeToTimeOverview((Stage) topmenu_overview.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btn_create_project) {
            StageHandler.changeToCreateProject((Stage) btn_create_project.getScene().getWindow(), getClass());
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
