package com.gavima_kanido.controller;

import java.io.IOException;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateProjectController {

    private User user;

    public CreateProjectController(User user) {
        this.user = user;
    }

    @FXML
    private TextField team_names_input;

    @FXML
    private TextField project_dexcription_input;

    @FXML
    private TextField project_name_input;

    @FXML
    private TextField customer_name_input;

    @FXML
    private TextField project_budget_input;

    @FXML
    private Button topmenu_trackprojects;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btn_save_project;

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

}