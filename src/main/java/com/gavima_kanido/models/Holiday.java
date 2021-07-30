package com.gavima_kanido.models;

import java.sql.Date;
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
        this.totalDays = Duration.between(startDate, endDate).toDays();
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


    
}
