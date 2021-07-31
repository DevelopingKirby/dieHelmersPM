package com.gavima_kanido.controller;
import com.gavima_kanido.handler.CreateProjectHandler;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CreateProjectController {

    private User user;
    private CreateProjectHandler createProjectHandler = new CreateProjectHandler();

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

            int creationSuccessful = createProjectHandler.createProject(project_name_input.getText(), customer_name_input.getText(), project_description_input.getText(), project_budget_input.getText());

            if ( creationSuccessful == 2 ) {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("All fields have to be filled\nand the budget field has to\nbe a Number");
            }
            else if ( creationSuccessful == 0) {
            
            lblInfo.setTextFill(Color.TOMATO);
                    lblInfo.setText("Adding project failed");    
            } 
            else if ( creationSuccessful == 1) {
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("Added project\nsuccessfully");
            }
        }
           
    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
    }

}