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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PersonalDataController {

    private User user;

    public PersonalDataController(User user) {
        this.user = user;
    }

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTeams;

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
    private Label lblZip;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblStreet;

    @FXML
    private Label lblSuperior;


    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnTeams) {
            StageHandler.changeToTeams((Stage) btnTeams.getScene().getWindow(), getClass());
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
        lblCity.setText(user.getCity());
        lblCountry.setText(user.getCountry());
        lblZip.setText(Integer.toString(user.getZip()));
        lblStreet.setText(user.getStreet());
        lblSuperior.setText(user.getSuperiorName());


    }

} 