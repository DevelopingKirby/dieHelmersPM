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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

public class DashboardController {

    private User user;

    public DashboardController(User user) {
        this.user = user;
    }

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnHoli;

    @FXML
    private Button btnTeam;

    @FXML
    private Button btnTime;

    @FXML
    private Button btnPersonalData;

    @FXML
    private Label lblUserRef;

    @FXML
    private Label lblName;

    @FXML
    private Label lblDepartment;

    @FXML
    private Label lblTeam;

    @FXML
    private Label lblEMail;

    @FXML
    private Label lblPhone;

    

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == btnTime) {
            StageHandler.changeToTimeOverview((Stage) btnTime.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnProjects) {
            StageHandler.changeToTrackProjects((Stage) btnProjects.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnHoli) {
            StageHandler.changeToHoliday((Stage) btnHoli.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnTeam) {
            StageHandler.changeToTeams((Stage) btnTeam.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnPersonalData) {
            StageHandler.changeToPersonalData((Stage) btnPersonalData.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }

    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
        lblName.setText(user.getFirstName().substring(0, 1).toUpperCase() + user.getFirstName().substring(1) + " " + user.getName().substring(0, 1).toUpperCase() + user.getName().substring(1));
        lblDepartment.setText(user.getDepartment());
        lblTeam.setText(user.getTeam());
        lblEMail.setText(user.getEMail());
        lblPhone.setText(user.getPhoneNumber());

    }
    
}
