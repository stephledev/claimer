package main.java.ch.claimer.client.gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * @author Alexander Hauck
 * @since 16.04.2015
 * @version 1.0
 *
 */

public class SubcontractorController {
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	//Zur Home-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadSubcontracotrAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
}
