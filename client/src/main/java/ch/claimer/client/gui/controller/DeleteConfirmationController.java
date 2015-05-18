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
 * Kontroller f�r die L�schbest�tigung
 * 
 * @author Michael L�tscher
 * @since 1.0
 * @version 1.0
 *
 */

public class DeleteConfirmationController implements Initializable {

	@FXML
	private Button bttn_agree;
	
	@FXML
	private Button bttn_cancel;

	/**
	 * Speichert die Datei
	 * @param event - ActionEvent = Klick auf den Button
	 * @throws IOException
	 */
	@FXML
	private void agree(ActionEvent event) throws IOException{
	//TODO	
	}

	/**
	 * Beim Klick auf den "Abbrechen"-Button schliesst sich das Fenster wieder.
	 * @param event - ActionEvent = Klick auf den Button
	 * @throws IOException
	 */
	@FXML
	private void cancel(ActionEvent event) throws IOException{
		Stage stage = (Stage) bttn_cancel.getScene().getWindow();
	    stage.close();
	}
	
	
	

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	

	
	



}




