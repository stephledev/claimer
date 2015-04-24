package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserAddController {

	@FXML
	private Pane mainContent;
	
	@FXML
	private void loadUserMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserMainView.fxml"));
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
	
	@FXML
	private void addUser(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button.");
		// ToDo: Read Data from Textfields, check them and save into Database
	}
	
	@FXML
	private void uploadImage(ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        Stage stage = new Stage();
		System.out.println("Klick auf Button.");
		// ToDo: Upload-Fenster öffnen, Bild überprüfen, bild speichern
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	 desktop.open(file);
        }
	}
	
}
