package ch.claimer.client.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */

public class ProjectMangleController {
	
	// Maincontent, hierhin werden die verschiedenen Views geladen
		@FXML
		private Pane mainContent;
		
		@FXML
		private Label lbl_title;
		
		@FXML
		private TextField txt_mangleName;

		@FXML
		private TextField txt_projectName;
		
		@FXML
		private TextField txt_objectName;

		@FXML
		private TextField txt_area;

		@FXML
		private TextField txt_mangleDescription;

		@FXML
		private TextField txt_contactPerson;

		@FXML
		private TextField txt_principalPhone;

		@FXML
		private ComboBox<?> combo_principalName;
		
		@FXML
		private ComboBox<?> combo_status;
		
		@FXML
		private ComboBox<?> combo_subcontractor;
		
		@FXML
		private Button bttn_saveMangle;
		
		@FXML
		private Button bttn_quitMangle;
		
		@FXML
		private Button bttn_quitComment;
		
		@FXML
		private Button bttn_addComment;
		
		@FXML
		private Button bttn_addPhoto;
		
		@FXML
		private ImageView img_1;
		
		@FXML
		private ImageView img_2;
		
		@FXML
		private ImageView img_3;
		
		@FXML
		private ImageView img_4;
		
		
		
		
	// Zur ProjectAdd-Ansicht wechseln (mainView.xml)
		@FXML
		private void backToProjectAddView(ActionEvent event) throws IOException {
			Pane myPane = FXMLLoader.load(getClass().getResource(
					"../view/ProjectAddView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);

		}


}
