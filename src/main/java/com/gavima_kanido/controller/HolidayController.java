package com.gavima_kanido.controller;


import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.HolidayHandler;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public class HolidayController {

    private User user;
    private List<Holiday> userHolidays = new ArrayList<Holiday>();
    private int availableDaysLeft;
    private HolidayHandler handler = new HolidayHandler();

    public HolidayController (User user) {
        this.user = user;

    }

    @FXML
    private Label lblUserRef;

    @FXML
    private Button topmenu_dashboard;

    @FXML
    private Button btnLogout;

    @FXML
    private Label lblInfo;

    @FXML
    private ListView<String> listDates;

    @FXML
    private Button btnBookHoliday;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private ListView<String> listStatus;

    @FXML
    private Label lblAvailableDays;

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }

        else if (event.getSource() == btnBookHoliday) {
            int bookholiday = handler.bookHoliday(user.getUserRef(), datePickerStartDate.getValue(), datePickerEndDate.getValue());

            if (bookholiday == 1) {
                lblInfo.setTextFill(Color.GREEN);
                lblInfo.setText("Saved Holiday request");
                populateStatusTable();
                populateAvailableDays();

            } 
            else if (bookholiday == 2) {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("Neither Startdate nor Enddate can be \nin the past!");
            } 
            else if (bookholiday == 3) {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("Enddate cannot be before Startdate");
            }

            else if (bookholiday == 4) {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("No more available Days left!");
            }

            else if (bookholiday == 5) {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("Booking request exceeds available Days!");
            }
        }
    }

    public void populateStatusTable() {
        try {
            this.userHolidays = DatabaseOperationUtil.getHolidaysForUserRef(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        listDates.getItems().clear();
        listStatus.getItems().clear();

        for (Holiday h : userHolidays) {

        
            listDates.getItems().add(h.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + h.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n" + "Total Days: " + Long.toString(h.getTotalDays()));
            listStatus.getItems().addAll(h.getStatus() + "\n" + " ");

        }

    }

    public void populateAvailableDays() {
        try {
            this.availableDaysLeft = DatabaseOperationUtil.getAvailableHolidays(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        lblAvailableDays.setText(Integer.toString(availableDaysLeft));
    }

    @FXML
    public void initialize(){
        populateStatusTable();
        populateAvailableDays();
        lblUserRef.setText(user.getUserRef());
    }

}
