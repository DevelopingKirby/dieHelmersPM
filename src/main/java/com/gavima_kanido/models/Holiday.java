package com.gavima_kanido.models;

import java.sql.Date;
import java.time.temporal.ChronoUnit;
import java.time.*;

public class Holiday {

    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private long totalDays;


    public Holiday(LocalDate startDate, LocalDate endDate, String status) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.totalDays = ChronoUnit.DAYS.between(startDate, endDate);
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


    
}
