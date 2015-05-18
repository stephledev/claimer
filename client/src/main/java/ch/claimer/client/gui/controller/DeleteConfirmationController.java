package ch.claimer.client.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;



/**
 * Controller für die Löschbestätigung
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

	/**
	 * Bestätigt die Eingabe
	 */
	@FXML
	private void agree() {
		
	}

	/**
	 * Abbrechen. Schliesst das Fenster.
	 */
	@FXML
	private void cancel(){
		Stage stage = (Stage) bttn_cancel.getScene().getWindow();
	    stage.close();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	

	
	



}




