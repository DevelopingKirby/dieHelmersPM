package com.gavima_kanido.controller;


import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.handler.HolidayHandler;
import com.gavima_kanido.handler.StageHandler;
import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;


public class HolidayController {

    private User user;
    private List<Holiday> userHolidays = new ArrayList<Holiday>();
    private int availableDaysLeft;
    private HolidayHandler holidayHandler = new HolidayHandler();

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

    // @FXML
    // private ListView<String> listStatus;

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

            if (datePickerStartDate.getValue() != null && datePickerEndDate.getValue() != null) {
                int bookholiday = holidayHandler.bookHoliday(user.getUserRef(), datePickerStartDate.getValue(), datePickerEndDate.getValue());

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
            } else {
                lblInfo.setTextFill(Color.YELLOW);
                lblInfo.setText("Please provide an Start and End date!"); 
            }

        }
    }

    public void populateStatusTable() {
        this.userHolidays = holidayHandler.getHolidaysForUser(user);
        listDates.getItems().clear();
        //listStatus.getItems().clear();

        for (Holiday h : userHolidays) {

        
            listDates.getItems().add(h.getStartDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " - " + h.getEndDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\t\t\t\t" + h.getStatus() + "\n" + "Total Days: " + Long.toString(h.getTotalDays()));
            //listStatus.getItems().addAll(h.getStatus() + "\n" + " ");

        }

    }

    public void populateAvailableDays() {
        this.availableDaysLeft = holidayHandler.getAvailableHolidays(user);        
        lblAvailableDays.setText(Integer.toString(availableDaysLeft));
    }

    @FXML
    public void initialize(){
        populateStatusTable();
        populateAvailableDays();
        lblUserRef.setText(user.getUserRef());
    }

}
