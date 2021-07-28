package com.gavima_kanido.controller;

import java.io.IOException;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateProjectController {

    private User user;

    public CreateProjectController(User user) {
        this.user = user;
    }

   
    @FXML
    private TextField project_description_input;

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
    private Label lblUserRef;

    @FXML
    private Label lblInfo;

    @FXML
    void handleButtonAction(MouseEvent event) throws Exception {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_trackprojects) {
            StageHandler.changeToTrackProjects((Stage) topmenu_trackprojects.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btn_save_project){
            if (customer_name_input.getText().isEmpty() || customer_name_input.getText().isEmpty() || project_description_input.getText().isEmpty() || project_budget_input.getText().isEmpty() || !(project_budget_input.getText().matches("-?\\d+"))){
                lblInfo.setTextFill(Color.TOMATO);
                lblInfo.setText("all fields has to be filled\nand budget field has to\nbe an Int");
            }
            else
            {
                if (DatabaseOperationUtil.addProject(customer_name_input.getText(), customer_name_input.getText(), project_description_input.getText(), Integer.parseInt(project_budget_input.getText())) == 0){
                    lblInfo.setTextFill(Color.TOMATO);
                    lblInfo.setText("adding project failed");
                }
                else{
                    lblInfo.setTextFill(Color.GREEN);
                    lblInfo.setText("added project successfully");
                }
            }
        }
           
    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
    }

}