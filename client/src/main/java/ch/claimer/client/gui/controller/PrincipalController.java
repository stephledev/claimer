package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.claimer.client.proxy.PrincipalProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Principal;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

/**
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 1.0
 *
 */

public class PrincipalController implements Initializable {

	ObservableList<Principal> data = FXCollections.observableArrayList(); //Beinhaltet alle Bauherren bei der Initialisation
	ObservableList<Principal> filteredData = FXCollections.observableArrayList(); //Enth�lt gefilterte Daten (suchen-funktion)
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private TableView<Principal> principalTableView;
	
	@FXML
	private TableColumn<Principal, String> colFirstname;
	
	@FXML
	private TableColumn<Principal, String> colLastname;
	
	@FXML
	private TableColumn<Principal, String> colCompany;
	
	@FXML
	private TableColumn<Principal, String> colZip;
	
	@FXML
	private TableColumn<Principal, String> colPlace;
	
	@FXML
	private TableColumn<Company, String> colPhone;

	
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	/**
	 * Zum PrincipalAddView wechseln
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void loadPrincipalAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/PrincipalAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	/**
	 * Fenster aufrufen, um einen Bauherr zu bearbeiten
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void editPrincipal(ActionEvent event) throws IOException {
		// TODO
	}
	
	
	
	/** 
	 * TableView mit Bauherren-Daten bef�llen beim Aufruf.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bauherren aus Datenbank laden
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Principal> principalList = null;
		try {
			principalList = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Bauherren der ObservableList zuweisen
		for(Principal principal : principalList) {
			data.add(principal);
			filteredData.add(principal);
		}
		
		
		//Spalten-Values definieren (m�ssen den Parameter des Principal-Objekts entsprechen)
		colFirstname.setCellValueFactory(new PropertyValueFactory<Principal, String>("firstname"));
		colLastname.setCellValueFactory(new PropertyValueFactory<Principal, String>("lastname"));
		colCompany.setCellValueFactory(new PropertyValueFactory<Principal, String>("company"));
		colZip.setCellValueFactory(new PropertyValueFactory<Principal, String>("zip"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Principal, String>("place"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle �bergeben
		principalTableView.setItems(filteredData);
		
		//Listener f�r �nderungen im Suchenfeld
		txtSearch.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//updateFilteredData();				
			}
			
		});
		
	}
	
}
