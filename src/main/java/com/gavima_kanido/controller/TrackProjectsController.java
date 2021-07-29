
package com.gavima_kanido.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;
import com.gavima_kanido.models.Project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TrackProjectsController {

    private User user;
    private List<Project> userProjects = new ArrayList<Project>();

    public TrackProjectsController(User user) {
        this.user = user;
        try {
            this.userProjects = DatabaseOperationUtil.getProjects(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @FXML
    private Label lblUserRef;

    @FXML
    private Button topmenu_overview;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Label totalTimeSpent;

    @FXML
    private Button startTime;


    @FXML
    private Button stopTime;

    @FXML
    private Button btn_create_project;

    @FXML
    private ComboBox<String> comboBoxProject;

    @FXML
    private Label lblName;

    @FXML
    private Label lblCustomer;

    @FXML
    private Label lblBudget;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblTimeInfo;

    @FXML
    private void comboAction(ActionEvent event) {


        for (Project p : userProjects) {
            if (p.getName().equals(comboBoxProject.getSelectionModel().getSelectedItem())) {
               
                lblName.setText(p.getName());
                lblCustomer.setText(p.getCustomer());
                lblBudget.setText(Integer.toString(p.getBudget()));
                lblDescription.setText(p.getDescription());

            }
        }
    }

    public static void main(String[] args) {


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

        long unixTime = System.currentTimeMillis() / 1000L;

        System.out.println(unixTime);
        double diff = (unixTime - 1627589467);
        System.out.println(diff/60);
        double hours = round((unixTime - 1627589467)/60.0, 2);

        System.out.println(hours);
    }

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == topmenu_overview) {
            StageHandler.changeToTimeOverview((Stage) topmenu_overview.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btn_create_project) {
            StageHandler.changeToCreateProject((Stage) btn_create_project.getScene().getWindow(), getClass());
        }
    }

    public void populateProjectsList() {   
        for(Project p : userProjects) {
            comboBoxProject.getItems().add(p.getName());
        }
    }

    @FXML
    public void initialize(){
        populateProjectsList();
        lblUserRef.setText(user.getUserRef());
        if (user.getPrivileges() == 2){
            btn_create_project.setVisible(false);
        }


    }

}
