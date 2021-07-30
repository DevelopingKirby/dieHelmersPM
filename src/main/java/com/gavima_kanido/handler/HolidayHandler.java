package com.gavima_kanido.handler;

import java.time.LocalDate;

public class HolidayHandler {

    public int bookHoliday(LocalDate startDate, LocalDate endDate) {
        int bookSuccessful = 0;
        // Logik
        if(endDate.after(startDate) && startDate.before(endDate)) {

            int = DatabaseOperationUtil.bookHoliday()

        } else {
            return bookSuccessful = 2;
        }
    }
    
}
