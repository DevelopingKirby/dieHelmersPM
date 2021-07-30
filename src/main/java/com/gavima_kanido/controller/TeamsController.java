package com.gavima_kanido.controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Project;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;
import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class TeamsController {
    private User user;

    private List<User> employees = new ArrayList<User>();
    private List<Project> projects = new ArrayList<Project>();

    public TeamsController(User user) {
        this.user = user;
        try {
            this.employees = DatabaseOperationUtil.getTeamMembers(user.getTeam());
            this.projects = DatabaseOperationUtil.getProjects();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private ComboBox<String> projectsComboBox;

    @FXML
    private Label lblTeamName;

    @FXML
    private MenuButton employeeMenuButton;

    @FXML
    private Button btnAddToProject;

    @FXML
    private ComboBox<String> employeeComboBox;

    @FXML
    private Button btnGetPersonaldata;

    @FXML
    private Label lblUserRef;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btn_create_project;

    @FXML
    private Button btnReviewHolidays;


    @FXML
    private void comboAction(ActionEvent event) {

        ObservableList<MenuItem> employeesInProjects =  employeeMenuButton.getItems();

        for (MenuItem m : employeesInProjects) {

            CustomMenuItem menuItem = ((CustomMenuItem) m);

            CheckBox checkbox = (CheckBox) menuItem.getContent();

            checkbox.setSelected(false);

        }

        for (Project p : projects) {
            if (p.getName().equals(projectsComboBox.getSelectionModel().getSelectedItem())) {
                

                for (User u : employees) {

                    for (User u2 : p.getMember()) {



                        if (u.getUserRef().equals(u2.getUserRef())) {

                            for (MenuItem m : employeesInProjects) {

                                CustomMenuItem menuItem = ((CustomMenuItem) m);
                
                                CheckBox checkbox = (CheckBox) menuItem.getContent();

                                if (checkbox.getText().toLowerCase().equals(u2.getFirstName() + " " + u2.getName())) {
                                    checkbox.setSelected(true);
                                }

                
                            }

                        }

                    }

                }

            }
        }

        
    
    }
  

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {

        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }

        else if (event.getSource() == btn_create_project) {
            StageHandler.changeToCreateProject((Stage) btn_create_project.getScene().getWindow(), getClass());
        }

        else if (event.getSource() == btnAddToProject) {   
            lblInfo.setTextFill(Color.GREEN);
            lblInfo.setText("loading...");    
            System.out.println("loading...");     
            ObservableList<MenuItem> employeesInProjects =  employeeMenuButton.getItems();
            
            for (MenuItem m : employeesInProjects) {

                CustomMenuItem menuItem = ((CustomMenuItem) m);

                CheckBox checkbox = (CheckBox) menuItem.getContent();

                if (checkbox.isSelected() == true) {

                    for (Project p : projects) {

                        if (p.getName().equals(projectsComboBox.getSelectionModel().getSelectedItem())) {
                                String[] split = checkbox.getText().toLowerCase().split("\\s+");

                            if (p.getMember().isEmpty()) {
                                    try {
                                        DatabaseOperationUtil.addToProject(split[1], p.getId());
                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                            }

                            for (User u : p.getMember()) {

                                if (u.getName().equals(split[1])) {

                                    System.out.println("User already in Project");

                                } else {
                                    try {
                                        DatabaseOperationUtil.addToProject(split[1], p.getId());        
                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                }

                            }




                        }

                    }
                
                } else if (checkbox.isSelected() == false) {

                    for (Project p : projects) {

                        if (p.getName().equals(projectsComboBox.getSelectionModel().getSelectedItem())) {

                            for (User u : p.getMember()) {

                                String[] split = checkbox.getText().toLowerCase().split("\\s+");
   

                                if (u.getName().equals(split[1])) {
                                    try {
                                        DatabaseOperationUtil.removeFromProject(split[1], p.getId());
                                        
                                    } catch (SQLException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
    
                                }
    
                            }


                        }

                    }

                }



            }

            lblInfo.setText("");
            try {
                this.projects = DatabaseOperationUtil.getProjects();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } 

        
        
        
        else if (event.getSource() == btnGetPersonaldata) {
            String selection = employeeComboBox.getSelectionModel().getSelectedItem().toLowerCase();
            for (User u : employees) {
                String name = u.getFirstName() + " " + u.getName();
                
                if (selection.equals(name)){ 
                    StageHandler.changeToEmployeeData((Stage) btnGetPersonaldata.getScene().getWindow(), getClass(), u);
                }
            }
        }

    }

    public void populateEmployeeListProjects() {
        employeeMenuButton.getItems().setAll();
        for(User u : employees) {
            CustomMenuItem item = new CustomMenuItem(new CheckBox(u.getFirstName().substring(0, 1).toUpperCase() + u.getFirstName().substring(1) + " " + u.getName().substring(0, 1).toUpperCase() + u.getName().substring(1)));
            item.setHideOnClick(false);
            employeeMenuButton.getItems().add(item);
        }
    }
    
    public void populateEmployeeList() {
        
        employeeComboBox.getItems().setAll();
        for(User u : employees) {
            employeeComboBox.getItems().add(u.getFirstName().substring(0, 1).toUpperCase() + u.getFirstName().substring(1) + " " + u.getName().substring(0, 1).toUpperCase() + u.getName().substring(1));
        }
    }

    public void populateProjectsList() {
        for(Project p : projects) {
            projectsComboBox.getItems().add(p.getName());
        }
    }

    public void initialize() {
        lblUserRef.setText(user.getUserRef());
        lblTeamName.setText(user.getTeam());
        populateProjectsList();
        populateEmployeeListProjects();
        populateEmployeeList();


    }


}
