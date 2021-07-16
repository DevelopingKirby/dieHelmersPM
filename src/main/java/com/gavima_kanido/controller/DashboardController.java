package com.gavima_kanido.controller;


import java.io.IOException;

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

public class DashboardController {

    @FXML
    private Button btnTime;

    @FXML
    private Button btnProjects;

    @FXML
    private Button btnHoli;

    @FXML
    private Button btnTeam;
    
    @FXML
    public void handleButtonAction(MouseEvent event) throws IOException {
        System.out.println("Lol");
        if (event.getSource() == btnTime) {
            Stage stage = (Stage) btnTime.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));   
            loader.setController(new LoginController());
            Scene scene = new Scene((Parent) loader.load());
            stage.setScene(scene);
        }

    }

    //btnTime.setOnAction(e -> stage.setScene(new Scene(root)));




}
