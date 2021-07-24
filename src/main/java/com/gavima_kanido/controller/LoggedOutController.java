package com.gavima_kanido.controller;

import com.gavima_kanido.models.User;

public class LoggedOutController {
    
    private User user;

    public LoggedOutController(User stageUser) {
        this.user = stageUser;
    }

}
