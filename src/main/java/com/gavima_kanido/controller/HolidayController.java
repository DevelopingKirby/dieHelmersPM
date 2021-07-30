package com.gavima_kanido.controller;


import java.io.IOException;
import java.sql.SQLException;
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


public class HolidayController {

    private User user;
    private List<Holiday> userHolidays = new ArrayList<Holiday>();
    private HolidayHandler handler = new HolidayHandler();

    public HolidayController (User user) {
        this.user = user;
        try {
            this.userHolidays = DatabaseOperationUtil.getHolidaysForUserRef(user.getUserRef());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    private ListView<?> listDates;

    @FXML
    private Button btnBookHoliday;

    @FXML
    private DatePicker datePickerEndDate;

    @FXML
    private DatePicker datePickerStartDate;

    @FXML
    private ListView<?> listStatus;

    @FXML
    private Label lblUserRef1;

    @FXML
    void handleButtonAction(MouseEvent event) throws IOException {
        if (event.getSource() == topmenu_dashboard) {
            StageHandler.changeToDashboard((Stage) topmenu_dashboard.getScene().getWindow(), getClass());
        }
        else if (event.getSource() == btnLogout) {
            StageHandler.changeToLoggedOut((Stage) btnLogout.getScene().getWindow(), getClass());
        }

        else if (event.getSource() == btnBookHoliday) {
            System.out.println(datePickerStartDate.getValue());
            System.out.println(datePickerEndDate.getValue());
            int bookholiday = handler.bookHoliday(datePickerStartDate.getValue(), datePickerEndDate.getValue());

            if (bookholiday == 2) {
                lblInfo.setText("Startdate cannot be after Enddate!");
            }
        }
    }

    public void populateStatusTable() {

    }

    @FXML
    public void initialize(){
        
        lblUserRef.setText(user.getUserRef());
    }

}
