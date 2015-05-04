package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.SCEmployee;

/**
 * Controller für das Hinzufügen und Ändern von Subunternehmen
 * @author Alexander Hauck
 * @since 30.04.2015
 * @version 1.0
 */

public class SubcontractorAddController {

	ObservableList<Person> data = FXCollections.observableArrayList();
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtAdress;
	
	@FXML
	private TextField txtZip;
	
	@FXML
	private TextField txtPlace;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TableView<Person> sceTableView;
	
	@FXML
	private TableColumn<Person, String> colLastname;
	
	@FXML
	private TableColumn<Person, String> colFirstname;
	
	@FXML
	private TableColumn<Person, String> colEmail;
	
	@FXML
	private TableColumn<Person, String> colPhone;
	
	/**
	 * Initialisiert den View mit den Daten des angeklickten Unternehmens
	 * @param subcontractor
	 */
	public void initData(Company subcontractor) {
		Integer subcontractorID = subcontractor.getId();
	
		lblTitel.setText("Subunternehmen bearbeiten");
		
		txtName.setText(subcontractor.getName());
		txtAdress.setText(subcontractor.getStreet());
		txtZip.setText(subcontractor.getZip());
		txtPlace.setText(subcontractor.getPlace());
		txtPhone.setText(subcontractor.getPhone());
		txtEmail.setText(subcontractor.getEmail());
	    
		
		//Mitarbeiter der Firma aus der DB laden
		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Person> personList = null;
	    
	    Person scEmployee = new SCEmployee();
	    
	    try {
	    	
			personList = mapper.readValue(sceProxy.getBySubcontractor(subcontractorID), new TypeReference<List<SCEmployee>>(){});
			
			for(int i = 0; i < personList.size(); i++) {
				
					// TODO Überprüfen, ob die Person überhaupt zum Subunternehmen gehört
					scEmployee = personList.get(i);
			    	data.add(scEmployee);
			    	scEmployee = null;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    personList = null;
		
		//Tabelle initialisieren
		colFirstname.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		sceTableView.setItems(data);
					

	}
	
	@FXML
	private void loadSubcontractorMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (NullPointerException npe) {
			System.out.println("Fehler: View konnte nicht geladen werden");
			// ToDo Eintrag in Log-Datei
			npe.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
