package com.gavima_kanido.controller;

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

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import com.gavima_kanido.handler.HolidayBookingRequestsHandler;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.User;


public class HolidayBookingRequestsController {

    private User user;
    private List<User> employees = new ArrayList<User>();
    private List<Holiday> teamMemberHolidays = new ArrayList<Holiday>();
    private HolidayBookingRequestsHandler holidayBookingRequestsHandler = new HolidayBookingRequestsHandler();

    public HolidayBookingRequestsController (User user, List<User> employees) {
        this.user = user;
        this.employees = employees;
        this.teamMemberHolidays = holidayBookingRequestsHandler.getTeamMembersHoliday(user);
    }

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Label lblUserRef;

    @FXML
    private Button btnLogout;

    @FXML
    private ComboBox<String> teamMembersComboBox;

    @FXML
    private Label lblTeamName;

    @FXML
    private MenuButton requestsMenuButton;

    @FXML
    private Button btnAllow;

    @FXML
    private Label lblInfo;

    @FXML
    private Button btnDeny;

    @FXML
    void comboAction(ActionEvent event) {

        requestsMenuButton.getItems().setAll();

        for (User u : employees) {
            if ((u.getFirstName() + " " + u.getName()).equals(teamMembersComboBox.getSelectionModel().getSelectedItem().toLowerCase())) {
                for (Holiday h : teamMemberHolidays) {

                    System.out.println(h.getUserRef());

                    if (u.getUserRef().equals(h.getUserRef())) {
                        CustomMenuItem item = new CustomMenuItem(new CheckBox(h.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + h.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" + "Total Days: " + Long.toString(h.getTotalDays())));
                        item.setHideOnClick(false);
                        requestsMenuButton.getItems().add(item);
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
        else if (event.getSource() == btnAllow) {
            int dbOperationSuccessful = 0;

            ObservableList<MenuItem> listHolidayRequestsFromUser =  requestsMenuButton.getItems();
            
            for (MenuItem m : listHolidayRequestsFromUser) {

                CustomMenuItem menuItem = ((CustomMenuItem) m);

                CheckBox checkbox = (CheckBox) menuItem.getContent();

                if (checkbox.isSelected() == true) {

                   for (Holiday h : teamMemberHolidays) {
                        String[] split = checkbox.getText().toLowerCase().split("\\n");
                        if (split[0].equals(h.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + h.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) {
                           dbOperationSuccessful = holidayBookingRequestsHandler.allowHolidayBooking(h);                        
                       }
                   }           
                }             
            }

            if (dbOperationSuccessful == 1) {
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("Successfully saved allowed Holiday!");
                this.teamMemberHolidays = holidayBookingRequestsHandler.getTeamMembersHoliday(user);
                populateEmployeeList();
            } else {
                lblInfo.setTextFill(Color.TOMATO);
                lblInfo.setText("An error occurred while trying to save holiday!");
            }
        }

        else if (event.getSource() == btnDeny) {
            int dbOperationSuccessful = 0;

            ObservableList<MenuItem> listHolidayRequestsFromUser =  requestsMenuButton.getItems();
            
            for (MenuItem m : listHolidayRequestsFromUser) {

                CustomMenuItem menuItem = ((CustomMenuItem) m);

                CheckBox checkbox = (CheckBox) menuItem.getContent();

                if (checkbox.isSelected() == true) {

                   for (Holiday h : teamMemberHolidays) {
                        String[] split = checkbox.getText().toLowerCase().split("\\n");
                        if (split[0].equals(h.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + h.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))) {
                           dbOperationSuccessful = holidayBookingRequestsHandler.denyHolidayBooking(h);
                       }
                   }                             
                }   
            }
            if (dbOperationSuccessful == 1) {
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("Successfully saved denied Holiday!");
                this.teamMemberHolidays = holidayBookingRequestsHandler.getTeamMembersHoliday(user);
                populateEmployeeList();
            } 
            else {
                lblInfo.setTextFill(Color.TOMATO);
                lblInfo.setText("An error occured while trying to save holiday!");
            }  
        }
    }

    public void populateEmployeeList() {
        
        teamMembersComboBox.getItems().setAll();
        for(User u : employees) {
            teamMembersComboBox.getItems().add(u.getFirstName().substring(0, 1).toUpperCase() + u.getFirstName().substring(1) + " " + u.getName().substring(0, 1).toUpperCase() + u.getName().substring(1));
        }
    }

    public void initialize() {
        populateEmployeeList();
        requestsMenuButton.getItems().clear();
        lblUserRef.setText(user.getUserRef());
        lblTeamName.setText(user.getTeam());
    }

}

    

