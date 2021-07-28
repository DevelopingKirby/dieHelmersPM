package com.gavima_kanido.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.gavima_kanido.handler.LoginHandler;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;



/**
 *
 * @author Kirby
 */
public class LoginController {

    @FXML
    private Label lblInfo;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    public void handleButtonAction(Event event) throws IOException {

        if (event.getSource() == btnSignin) {
            logInSteps();
        }
    };

    @FXML
    public void onEnter(ActionEvent ae){
       logInSteps();
    }



    public void logInSteps() {
        if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()) {
                
            setLabelInfo(Color.TOMATO, "Empty credentials");

        } else {
            setLabelInfo(Color.GREEN, "Checking credentials, please wait...");
            User user = LoginHandler.logIn(txtUsername.getText(), txtPassword.getText());

            if (user != null) {
                setLabelInfo(Color.GREEN, "Log In successful, redirecting...");
                System.out.println("Login successful");
                try {
                    StageHandler.setUser(user);
                    StageHandler.changeToDashboard((Stage) btnSignin.getScene().getWindow(), getClass());   
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            } else {
                setLabelInfo(Color.TOMATO, "Enter Correct Email/Password");
            }

        }
    }


    
    private void setLabelInfo(Color color, String text) {
        lblInfo.setTextFill(color);
        lblInfo.setText(text);
        
    }
}
