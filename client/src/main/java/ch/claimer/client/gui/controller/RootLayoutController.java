package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.pmw.tinylog.Logger;

import ch.claimer.client.util.AuthenticationUtil;
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
 * @version 1.1
 *
 */

public class RootLayoutController implements Initializable {

	
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
	
	@FXML
	private Pane mainContent;

	/**
	 * Initialisiert den View
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
		
		if(roleValue == 15) {
			naviProjects.setVisible(false);
		}
	}
	
	/**
	 * Wechselt zur Projekte-Hauptansicht
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
	 * Wechselt zur Subunternehmen-Hauptansicht
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
	 * Wechselt zur Bauherren-Hauptansicht
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
	 * Wechselt zur User-Hauptansicht
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
	 * Schliesst das GUI
	 */
	@FXML
	private void closeClaimer() {
		System.exit(0);
	}
	
	/**
	 * Logout aus der Applikation und öffnen des Login-Fensters
	 */
	@FXML
	private void logout() {
		
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
