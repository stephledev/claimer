package ch.claimer.client.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */
public class ProjectAddController {

	//Maincontent, hierhin werden die verschiedenen Views geladen
			@FXML
			private Pane mainContent;

			//Zur ProjectMangle-Ansicht wechseln (mainView.xml)
			@FXML
			private void loadProjectMangleView(ActionEvent event) throws IOException {
				Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectMangleView.fxml"));
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
				
			}
			
			//Zur ProjectMain-Ansicht wechseln (mainView.xml)
			@FXML
			private void loadProjectMainView(ActionEvent event) throws IOException {
				Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectsMainView.fxml"));
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
				
			}
			
		}