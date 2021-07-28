package com.gavima_kanido.controller;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.gavima_kanido.handler.StageHandler;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoggedOutController {

    private Stage myStage;

    public void setStage(Stage stage) {
         myStage = stage;
    }

    public void logOut() throws IOException {
        StageHandler.setUser(null);
        StageHandler.changeToLogIn(myStage , getClass());
    }

    @FXML
    private AnchorPane ap;



    

}
