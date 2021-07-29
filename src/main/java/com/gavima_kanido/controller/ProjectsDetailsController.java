package com.gavima_kanido.controller;
import java.io.IOException;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProjectsDetailsController {

    private User user;
    private Project project;

    public ProjectsDetailsController(User user, Project project) {
        this.user = user;
        this.project = project;
    }

    @FXML
    private Button topmenu_trackprojects;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button topmenu_overview;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTeams;

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
        else if (event.getSource() == topmenu_trackprojects) {
            StageHandler.changeToTrackProjects((Stage) topmenu_trackprojects.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_overview) {
            StageHandler.changeToTimeOverview((Stage) topmenu_overview.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnTeams) {
            StageHandler.changeToTeams((Stage) btnTeams.getScene().getWindow(), getClass());
        }
    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
    }

}