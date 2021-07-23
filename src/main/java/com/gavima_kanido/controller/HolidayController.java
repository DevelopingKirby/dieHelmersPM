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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;


public class HolidayController {

    private User user;

    public HolidayController(User user) {
        this.user = user;
    }

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    private Pane pane3;

    @FXML
    private Pane pane4;

    @FXML
    private Pane pane5;

    @FXML
    private Pane pane6;

    @FXML
    private Pane pane7;

    @FXML
    private Pane pane8;

    @FXML
    private Pane pane9;

    @FXML
    private Pane pane10;

    @FXML
    private Pane pane11;

    @FXML
    private Pane pane12;

    @FXML
    private Pane pane13;

    @FXML
    private Pane pane14;

    @FXML
    private Pane pane15;

    @FXML
    private Pane pane16;

    @FXML
    private Pane pane17;

    @FXML
    private Pane pane18;

    @FXML
    private Pane pane19;

    @FXML
    private Pane pane20;

    @FXML
    private Pane pane21;

    @FXML
    private Pane pane22;

    @FXML
    private Pane pane23;

    @FXML
    private Pane pane24;

    @FXML
    private Pane pane25;

    @FXML
    private Pane pane26;

    @FXML
    private Pane pane27;

    @FXML
    private Pane pane28;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Label end_month_day_year;

    @FXML
    private Label start_month_day_year;

    @FXML
    private Button book_holiday;

    @FXML
    private ChoiceBox<?> pick_month;

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
    }

}
