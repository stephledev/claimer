package ch.claimer.client.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
			
			@FXML
			private Label lbl_title;
			
			@FXML
			private Button bttn_saveProject;
			
			@FXML
			private Button bttn_quitProject;
			
			@FXML
			private Button bttn_addMangle;
			
			@FXML
			private TextField txt_projectName;
			
			@FXML
			private TextField txt_street;
			
			@FXML
			private TextField txt_plz;
			
			@FXML
			private TextField txt_place;
			
			@FXML
			private TextField txt_principalPhone;
			
			@FXML
			private ComboBox<?> combo_supervisor;
			
			@FXML
			private ComboBox<?> combo_principal;
			
			@FXML
			private DatePicker date_start;
			
			@FXML
			private DatePicker date_end;
			

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