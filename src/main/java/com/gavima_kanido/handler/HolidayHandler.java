package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.gavima_kanido.models.Holiday;
import com.gavima_kanido.models.User;
import com.gavima_kanido.utils.DatabaseOperationUtil;

public class HolidayHandler {

    public int bookHoliday(String userRef, LocalDate startDate, LocalDate endDate) {
        int bookSuccessful = 0;
        LocalDate today = LocalDate.now();
        // Logik
        if(startDate.isAfter(endDate)) {

            bookSuccessful = 3;

        
        } else {

            if (startDate.isBefore(today) || endDate.isBefore(today)) {

                bookSuccessful = 2;

            } else {
                try {
                    bookSuccessful = DatabaseOperationUtil.bookHoliday(userRef, startDate, endDate);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            
        }

        return bookSuccessful;
    }

    public List<Holiday> getHolidaysForUser(User user) {
        List<Holiday> userHolidays = new ArrayList<Holiday>();
        try {
            userHolidays = DatabaseOperationUtil.getHolidaysForUserRef(user.getUserRef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userHolidays;

    }

    public int getAvailableHolidays(User user) {
        int availableDaysLeft = 0;
        try {
            availableDaysLeft = DatabaseOperationUtil.getAvailableHolidays(user.getUserRef());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableDaysLeft;
    }


    
}
