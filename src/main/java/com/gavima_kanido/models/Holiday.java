package com.gavima_kanido.models;
import java.time.*;

public class Holiday {

    private String userRef;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private long totalDays;


    public Holiday(String userRef, LocalDate startDate, LocalDate endDate, String status) {
        this.userRef = userRef;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        int totalDaysBetween = 0;

        if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
            for (LocalDate date = startDate; date.isBefore(endDate) ; date = date.plusDays(1)) {

                totalDaysBetween += 1; 
    
            }
        } else {
            for (LocalDate date = startDate; !date.isAfter(endDate) ; date = date.plusDays(1)) {

                totalDaysBetween += 1; 
    
            }
        }


        
        for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            DayOfWeek d = date.getDayOfWeek();
            
            if (d == DayOfWeek.SATURDAY || d == DayOfWeek.SUNDAY) {
                totalDaysBetween -= 1;
            }

        }
        this.totalDays = totalDaysBetween;
    }


    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }


    public String getStatus() {
        return this.status;
    }

    public long getTotalDays() {
        return this.totalDays;
    }

    public String getUserRef() {
        return this.userRef;
    }


    
}
