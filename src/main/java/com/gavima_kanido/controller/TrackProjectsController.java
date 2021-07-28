
package com.gavima_kanido.controller;

import java.io.IOException;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TrackProjectsController {

    private User user;

    public TrackProjectsController(User user) {
        this.user = user;
    }

    @FXML
    private Button play_project_1;

    @FXML
    private Button play_project_2;

    @FXML
    private Button play_project_3;

    @FXML
    private Button play_project_4;

    @FXML
    private Button stop_project_1;

    @FXML
    private Button stop_project_2;

    @FXML
    private Button stop_project_3;

    @FXML
    private Button stop_project_4;

    @FXML
    private Button project1_details;

    @FXML
    private Button project2_details;

    @FXML
    private Button project3_details;

    @FXML
    private Button project4_details;

    @FXML
    private Button topmenu_overview;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private TextField project_search;

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
        else if (event.getSource() == project1_details) {
            StageHandler.changeToProjectsDetails((Stage) project1_details.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_overview) {
            StageHandler.changeToTimeOverview((Stage) topmenu_overview.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btn_create_project) {
            StageHandler.changeToCreateProject((Stage) btn_create_project.getScene().getWindow(), getClass());
        }
    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
        if (user.getPrivileges() == 2){
            btn_create_project.setVisible(false);
        }
    }

}
