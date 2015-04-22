package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.claimer.client.gui.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController extends Main implements Initializable {

	Stage prevStage;

	@FXML
	private Label lbl_mangelmanager;

	@FXML
	private Label lbl_text1;

	@FXML
	private Label lbl_warnung;

	@FXML
	private Label lbl_benutzer;

	@FXML
	private Label lbl_passwort;

	@FXML
	private TextField txt_benutzer;

	@FXML
	private PasswordField psw;

	@FXML
	private Button button_anmelden;
	

	// Klick auf "Anmelden Button ruft folgende Methode auf...
	// If Passwort und Benutzer richtig --> Weiterleiten zu "Home"
	// If Passwort und Benutzer falsch --> Warnung(Text) erscheint
	@FXML
	private void anmelden(ActionEvent event) {
		
			
		// Passwort oder Benutzer richtig
		if (psw.toString() != "" || txt_benutzer.toString() != "") {

			try {
				go(event);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("fail");
				e.printStackTrace();
			}
		}
		// Passwort oder Benutzer falsch
		else {
			lbl_warnung.setText("Passwort oder Benutzername ist falsch!");
		}
	}
	
	
	// verweist auf Stage aus der Klasse App.java
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	// Methode ruft die "Home"-Seite auf
	public void go(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		stage.setTitle("Home");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("../view/RootLayout.fxml"));
	
		Scene scene = new Scene(myPane);
		scene.getStylesheets().add(
				getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
		stage.setScene(scene);
		prevStage.close();
		stage.show();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
