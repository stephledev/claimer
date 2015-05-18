package ch.claimer.client.gui.controller;

import javafx.fxml.FXML;
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

public class DeleteConfirmationController {

	@FXML
	private Button bttn_agree;
	
	@FXML
	private Button bttn_cancel;

	/**
	 * Bestätigt die Eingabe.
	 */
	@FXML
	private void agree(){
		
	}
	
	/**
	 * Schliesst das Fenster.
	 */
	@FXML
	private void cancel(){
		Stage stage = (Stage) bttn_cancel.getScene().getWindow();
	    stage.close();
	}
}




