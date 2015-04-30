package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.gui.*;
import ch.claimer.client.proxy.LoginProxy;
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
 * @since 21.04.2015
 * @version 1.1
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
	private void anmelden(ActionEvent event) {
	
		
		/*
		//Eingegebenen Benutzernamen und Passwort auslesen
		String passwort = psw.getText();
		String username = txt_benutzer.getText();
		
		
		//Überprüfen, ob Felder nicht leer
		if(passwort.length() != 0 && username.length() != 0) {
			
			//Logins aus Datenbank laden
			Client client = new ResteasyClientBuilder().build();
		    WebTarget target = client.target("http://localhost:8080/webservice");
		    ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
		    
		    LoginProxy loginProxy = rtarget.proxy(LoginProxy.class);
		    ObjectMapper mapper = new ObjectMapper();
		    List<Login> loginList = null;
			try {
				loginList = mapper.readValue(loginProxy.getAll(), new TypeReference<List<Login>>(){});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			Login login = new Login();
			
			//Erhaltene List iterieren
		    for(int i = 0; i < loginList.size(); i++) {
		    	
		    	login = loginList.get(i);
		    	
		    	if(login.getUsername().equals(username)) {
		    		
					try {
						go(event);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println("fail");
						e.printStackTrace();
					}
		    		
		    	} else {
		    		System.out.println("Login nicht korrekt");
		    		psw.setText("");
		    	}
		    	
		    	login = null;
		    		
		    }
			
		} else {
			lbl_warnung.setText("Bitte alle Felder ausfüllen!");
		}
	}
	*/
	
	try {
		go(event);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("fail");
		e.printStackTrace();
	}
	}
		
	// verweist auf Stage aus der Klasse App.java
	public void setPrevStage(Stage stage) {
		this.prevStage = stage;
	}

	// Methode ruft die "Home"-Seite auf
	public void go(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		stage.setTitle("Mängelmanager");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("../view/RootLayout.fxml"));
	
		Scene scene = new Scene(myPane);
		scene.getStylesheets().add(
				getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
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
