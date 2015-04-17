package ch.claimer.client.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * @author Alexander Hauck
 * @since 10.04.2015
 * @version 1.1
 *
 */

public class RootLayoutController {

	//Maincontent, hierhin werden die verschiedenen View geladen
	@FXML
	private Pane mainContent;

	//Zur Home-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadHomeView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/home/homeView.fxml"));
		mainContent.getChildren().setAll(myPane);
		
	}
	
	//Zur Projekte-Hauptansicht (projectsMainView.xml) wechseln
	@FXML
	private void loadProjectsMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/projects/ProjectsMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}
	
	//Zur Bauleiter-Hauptansicht wechseln (supervisorMainView.xml)
	@FXML
	private void loadSupervisorMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/supervisor/SupervisorMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}
	
	//Zur Subunternehmen-Hauptansicht wechseln (supervisorMainView.xml)
		@FXML
		private void loadSubcontractorMainView(ActionEvent event) throws IOException {
			Pane myPane = FXMLLoader.load(getClass().getResource("../../view/subcontractor/SubcontractorMainView.fxml"));
			mainContent.getChildren().setAll(myPane);
		}
	
	//Zur Kunden-Hauptansicht wechseln  (principalMainView.xml)
	@FXML
	private void loadPrincipalMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/principal/PrincipalMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}
	
	//Zur Benutzer-Hauptansicht wechseln  (userMainView.xml)
	@FXML
	private void loadUserMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/user/UserMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}

}
