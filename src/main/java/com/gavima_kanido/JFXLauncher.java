package com.gavima_kanido;

import com.gavima_kanido.controller.LoginController;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class JFXLauncher extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Login.fxml"));     
        loader.setController(new LoginController());    
        Parent root = loader.load();
        stage.setMaximized(false);
        stage.setResizable(false);

        //Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

     

    }

    public static void main(String[] args) {
        launch();
    }

}