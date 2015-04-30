package ch.claimer.client.gui.controller;

import java.io.IOException;

import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Subcontractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Kevin Stadelmann
 * @since 16.04.2015
 * @version 1.0
 *
 */

public class SubcontractorController {
	
	ObservableList<Company> data =
			FXCollections.observableArrayList(
			);
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private TableView<Company> subcontractorTableView;
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableColumn<Company, String> colName;
	
	// @FXML
	//private TableColumn<Company, String> colTaetigkeit;
	
	@FXML
	private TableColumn<Company, String> colPlace;
	
	@FXML
	private TableColumn<Company, String> colPhone;
	
	@FXML
	private TableColumn<Company, String> colPlz;
	
	@FXML
	private TableColumn<Company, String> colEmail;
	

	//Zur Home-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadSubcontracotrAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/CompanyAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
	//Zum User Add/Change View wechseln (UserAddView.xml)
	@FXML
	private void loadSubcontractorAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);	
	}
	
	
	@FXML
	private void editUser(MouseEvent t) throws IOException {
        
        //Wenn Doppelklick auf Person
        if(t.getClickCount() == 2) {
               //Angeklickte Person laden
               Company subcontractor = subcontractorTableView.getSelectionModel().getSelectedItem();
            		   
               Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
  
               
               mainContent.getChildren().clear();
               mainContent.getChildren().setAll(myPane);     
        
        }
	}
	
	public void initialize() {
		
		
		// Daten für Tabelle definieren
		Company c1 = new Subcontractor();
		c1.setId(1);
		c1.setName("Kevin's AG");
		c1.setPhone("0800 666 666 666");
		c1.setPlace("Root");
		c1.setEmail("kevin.stadelmann@porntube.com");
		
		Company c2 = new Subcontractor();
		c2.setId(1);
		c2.setName("batista");
		c2.setPhone("123 666 666 666");
		c2.setPlace("Lucerna");
		c2.setEmail("bastiaaahatkeineemail!");
		
		// Daten zu ObservableList hinzufügen
		data.addAll(c1,c2);
		
		System.out.println(c1);
		
		//Spalten-Values definieren (müssen den Parameter des Company-Objekts entsprechen)
		colName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Company, String>("place"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		subcontractorTableView.setItems(data);
		
	}
	
}
