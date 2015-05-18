package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;



/**
 * Kontroller für die Löschbestätigung
 * 
 * @author Michael Lötscher
 * @since 1.0
 * @version 1.1
 *
 */

public class DeleteConfirmationController implements Initializable {

	@FXML
	private Button bttn_agree;
	
	@FXML
	private Button bttn_cancel;

	@FXML
	private void agree(ActionEvent event) throws IOException{
		
	}

	@FXML
	private void cancel(ActionEvent event) throws IOException{
		Stage stage = (Stage) bttn_cancel.getScene().getWindow();
	    stage.close();
	}
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	

	
	



}




