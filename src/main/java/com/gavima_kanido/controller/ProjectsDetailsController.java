package com.gavima_kanido.controller;

import java.io.IOException;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProjectsDetailsController {

    private User user;

    public ProjectsDetailsController(User user) {
        this.user = user;
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
    private Button btn_managers_view;

    @FXML
    private Button btnTeams;

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

}