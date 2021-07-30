package com.gavima_kanido.controller;

import com.gavima_kanido.handler.HolidayBookingRequestsHandler;
import com.gavima_kanido.models.User;

public class HolidayBookingRequestsController {

    private User user;
    private HolidayBookingRequestsHandler holidayBookingRequestsHandler = new HolidayBookingRequestsHandler();

    public HolidayBookingRequestsController (User user) {
        this.user = user;

    }
    
}
