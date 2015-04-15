package gui.controller.layout;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LayoutController {


	//Navigation Items
	@FXML
	private Button btnHome;

	@FXML
	private Button btnProjects;
	
	@FXML
	private Button btnSupervisor;
	

	@FXML
	private Pane mainContent;

	//Zur Home-Ansicht wechseln (MainView.xml)
	@FXML
	private void loadHomeView(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button für HomeView");
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/home/HomeView.fxml"));
		mainContent.getChildren().setAll(myPane);
		
	}
	
	//Zur Projekte-Hauptansicht (ProjectsMainView.xml) wechseln
	@FXML
	private void loadProjectsMainView(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button für ProjectsMainView");
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/projects/ProjectsMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}
	
	@FXML
	private void loadSupervisorMainView(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button für ProjectsMainView");
		Pane myPane = FXMLLoader.load(getClass().getResource("../../view/supervisor/SupervisorMainView.fxml"));
		mainContent.getChildren().setAll(myPane);
	}

}
