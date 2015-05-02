package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class PrincipalAddController implements Initializable {

	@FXML
	private Pane containerPersonAdd;
	
	@FXML
	private Pane containerCompanyAdd;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../view/UserAddView.fxml")
			);
		
		//Neuen View laden
		Pane myPane = null;
		try {
			myPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//Neuen View einfügen
		containerPersonAdd.getChildren().clear();
		containerPersonAdd.getChildren().setAll(myPane);
		
		
		
		FXMLLoader loader2 = new FXMLLoader(
				getClass().getResource("../view/SubcontractorAddView.fxml")
			);
		
		//Neuen View laden
		Pane myPane2 = null;
		try {
			myPane2 = loader2.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		//Neuen View einfügen
		containerCompanyAdd.getChildren().clear();
		containerCompanyAdd.getChildren().setAll(myPane2);
		
	}

	
	
}
