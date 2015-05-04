package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 1.0
 *
 */

public class PrincipalController implements Initializable {

	@FXML
	private TextField txtSearch;
	
	@FXML
	private TableView<Principal> subcontractorTableView;
	
	@FXML
	private TableColumn<Principal, String> colName;
	
	@FXML
	private TableColumn<Principal, String> colZip;
	
	@FXML
	private TableColumn<Principal, String> colPlace;
	
	@FXML
	private TableColumn<Company, String> colPhone;
	
	@FXML
	private TableColumn<Company, String> colPlz;
	
	@FXML
	private TableColumn<Company, String> colEmail;
	
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	//Zum Kunden Add/Change View wechseln (PrincipalAddView.xml)
	@FXML
	private void loadPrincipalAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/PrincipalAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	/** 
	 * TableView mit Bauherren-Daten befüllen beim Aufruf.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
}
