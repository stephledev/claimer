package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 1.0
 *
 */

public class UserController {
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableView userTableView;
	
	/*
	private ObservableList<String> row = FXCollections.observableArrayList();
	userTableView.getItems().add(row);
	*/

	@FXML
	private void loadUserMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
	//Zum User Add/Change View wechseln (UserAddView.xml)
	@FXML
	private void loadUserAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
	@FXML
	private void addUser(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button.");
		// ToDo: Read Data from Textfields, check them and save into Database
	}
	
	@FXML
	private void uploadImage(ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        Stage stage = new Stage();
		System.out.println("Klick auf Button.");
		// ToDo: Upload-Fenster öffnen, Bild überprüfen, bild speichern
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	 desktop.open(file);
        }
	}
	
}


