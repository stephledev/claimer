package ch.claimer.client.gui.controller;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.claimer.client.gui.*;
import ch.claimer.client.proxy.LoginProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Login;
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

/**
 * @author Michael Lötscher
 * @author Stephan Beeler
 * @since 21.04.2015
 * @version 1.2
 *
 */

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
	
	@FXML
	private void login(ActionEvent event) {
		
		//Eingegebenen Benutzernamen und Passwort auslesen
		String password = psw.getText();
		String username = txt_benutzer.getText();
		
		//Überprüfen, ob Felder nicht leer
		if(password.length() == 0 || username.length() == 0) {
			lbl_warnung.setText("Bitte alle Felder ausfüllen!");
			return;
		}
			
		//Logins aus Datenbank laden
		LoginProxy loginProxy = ResteasyClientUtil.getTarget().proxy(LoginProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    
	    Login login = new Login();
	    login.setUsername(username);
	    login.setPassword(password);
	    
		try {
			login = mapper.readValue(loginProxy.check(login), new TypeReference<Login>(){});
			if(login.getUsername() == null) {
				lbl_warnung.setText("Login nicht korrekt!");
				return;
			}
			AuthenticationUtil.setLogin(login);
			go(event);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	// verweist auf Stage aus der Klasse App.java
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	// Methode ruft die "Home"-Seite auf
	public void go(ActionEvent event) throws IOException {
		
		//Bildschirmauflösung auslesen
		Integer screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		
		Stage stage = new Stage();
		stage.setTitle("Mängelmanager");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("../view/RootLayout.fxml"));
	
		Scene scene = new Scene(myPane);
		scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
		if(screenWidth > 1500) {
			scene.getStylesheets().add(getClass().getResource("../big_font.css").toExternalForm()); /// CSS-File für grosse Bildschirme
		}
		stage.setMaximized(true);
		stage.setScene(scene);
		
		//Close previous Stage:
		Stage prevStage = (Stage) button_anmelden.getScene().getWindow();
	    prevStage.close();
	    
	    //Open new Stage
		stage.show();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
