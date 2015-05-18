package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.pmw.tinylog.Logger;

import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.shared.models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller für das RootLayout. Stellt die Navigation zur Verfügung.
 * @author Alexander Hauck
 * @since 10.04.2015
 * @version 1.3
 */

public class RootLayoutController implements Initializable {
	
	ObservableList<Person> data = FXCollections.observableArrayList();
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    
    ObjectMapper mapper;
    List<Person> personsToShow = null;
	public static Person personToTransmit;
	
	@FXML
	private SplitMenuButton splitMenuButton;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private Label lblName;
	
	@FXML
	private MenuItem menuLogout;
	
	@FXML
	private MenuItem menuClose;
	
	@FXML
	private Button naviHome;
	
	@FXML
	private Button naviProjects;
	
	@FXML
	private Button naviSubcontractors;
	
	@FXML
	private Button naviPrincipals;
	
	@FXML
	private Button naviUsers;
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;
	
	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Namen setzen
		lblName.setText(AuthenticationUtil.getPerson().getFirstname() + " " + AuthenticationUtil.getPerson().getLastname());
		
		//Mainnavi an die Rolle anpassen
		Integer roleValue = AuthenticationUtil.getLogin().getRole().getValue();
		
		if(roleValue < 25) {
			naviUsers.setVisible(false); //User-Menupunkt für alle Nicht-Superadmins ausblenden
		}
		
		if(roleValue < 20) {
			naviPrincipals.setVisible(false); //Bauherren-Navipunkt für power- und editor-users ausblenden.
		}
		
		if(roleValue < 15) {
			naviSubcontractors.setVisible(false); // Subunternehmen-Menupunkt für alle editor-ussuers ausblenden.
		}		
	}
	
	/**
	 * Wechselt zur Projektübersicht
	 */
	@FXML
	private void loadProjectsMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/ProjectsMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"ProjectsMainView.fxml\" kannn nicht geladen werden.");
		}
	}
	
	/**
	 * Wechselt zur Subunternehmen-Übersicht
	 */
	@FXML
	private void loadSubcontractorMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/SubcontractorMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"SubcontractorMainView.fxml\" kannn nicht geladen werden.");
		}
	}
	
	/**
	 * Wechselt zur Bauherren-Übersicht
	 */
	@FXML
	private void loadPrincipalMainView(){
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/PrincipalMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"PrincipalMainView.fxml\" kannn nicht geladen werden.");
		}
	}
	
	/**
	 * Wechselt zur User-Übersicht
	 */
	@FXML
	private void loadUserMainView(){
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/UserMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"UserMainView.fxml\" kannn nicht geladen werden.");
		}
	}
	
	/**
	 * Schliesst die gesamte Applikation
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void closeClaimer(ActionEvent event) throws IOException {
		System.exit(0);
	}
	
	/**
	 * Meldet den aktuellen Benutzer vom System ab und öffnet das Login-Fenster.
	 */
	@FXML
	private void logout(){
		
		try {
			
			Stage stage1 = (Stage) lblTitel.getScene().getWindow();
		    stage1.close();
			
			Stage stage = new Stage();
			stage.setTitle("Claimer");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Login.fxml"));
			Pane myPane = loader.load();

			Scene scene = new Scene(myPane);
			scene.getStylesheets().add(getClass().getResource("/claimer_styles.css").toExternalForm()); // CSS-File wird geladen
			stage.setScene(scene);
		    
		    //Open new Stage
			stage.show();
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"Login.fxml\" kannn nicht geladen werden.");
		}
	}
}
