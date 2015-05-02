package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 * Controller für das Hinzufügen und Ändern von Bauherren
 * @author Alexander Hauck
 * @since 02.05.2015
 * @version 1.0
 *
 */

public class PrincipalAddController implements Initializable {

	@FXML
	private Pane mainContent;
	
	
	@FXML
	private void loadPrincipalMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/PrincipalMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (NullPointerException npe) {
			System.out.println("Fehler: View konnte nicht geladen werden");
			// ToDo Eintrag in Log-Datei
			npe.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	
	
}
