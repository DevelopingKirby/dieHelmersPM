package com.gavima_kanido.handler;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.gavima_kanido.models.Holiday;
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }

        return bookSuccessful;
    }


    
}
