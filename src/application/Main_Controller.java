package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main_Controller {

    @FXML
    private Button btnTimeTracker;

    @FXML
    private Button btnHoliday;

    @FXML
    private Button Projects;

    @FXML
    private Button Teams;
    
    @FXML
    private TextField tfUsername;
    
    @FXML
    void mHoliday(ActionEvent event) {
    	
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Holiday.fxml"));
	        Parent root;
	        Holiday_Controller holiday_controller = loader.getController();      
			root = loader.load();
			Stage stage = new Stage();
		    stage.setScene(new Scene(root));
		    stage.setTitle("Second Window");
		    stage.show();
		} 
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
	}

    @FXML
    void mProjects(ActionEvent event) {
    	
    	try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Holiday.fxml"));
	        Parent root;
	        Projects_Controller projects_controller = loader.getController();      
			root = loader.load();
			Stage stage = new Stage();
		    stage.setScene(new Scene(root));
		    stage.setTitle("Second Window");
		    stage.show();
		} 
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void mTeams(ActionEvent event) {
    	
    	try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Holiday.fxml"));
	        Parent root;
	        Teams_Controller teams_controller = loader.getController();      
			root = loader.load();
			Stage stage = new Stage();
		    stage.setScene(new Scene(root));
		    stage.setTitle("Second Window");
		    stage.show();
		} 
		
		catch(IOException e) {
			
			e.printStackTrace();
		}
    }

    @FXML
    void mTimeTracker(ActionEvent event) {
    	
      	try {
			
  			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Holiday.fxml"));
	        Parent root;
	        TimeTracker_Controller timetracker_controller = loader.getController();      
			root = loader.load();
			Stage stage = new Stage();
		    stage.setScene(new Scene(root));
		    stage.setTitle("Second Window");
		    stage.show();
    	} 
    		
    	catch(IOException e) {
    			
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void initialize() {

    }
}
