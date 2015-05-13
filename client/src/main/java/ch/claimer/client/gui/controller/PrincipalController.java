package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.claimer.client.proxy.PrincipalProxy;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller für den Bauherren Hauptview.
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 2.1
 */

public class PrincipalController implements Initializable {

	ObservableList<Principal> data = FXCollections.observableArrayList(); //Beinhaltet alle Bauherren bei der Initialisation
	ObservableList<Principal> filteredData = FXCollections.observableArrayList(); //Beinhaltet eine Kopie aller Bauherren bei der Initialisation
	
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
	
	@FXML
	private Label lblMeldung;
	
	@FXML
	private Pane mainContent;

	/** 
	 * Wird automatisch aufgerufen. Füllt den TableView mit den Bauherren-Daten aus der Datenbank
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
			e1.printStackTrace();
		}
		
		//Bauherren der ObservableList zuweisen
		for(Principal principal : principalList) {
			data.add(principal);
			filteredData.add(principal);
		}
		
		//Spalten-Values definieren (müssen den Parameter des Principal-Objekts entsprechen)
		colFirstname.setCellValueFactory(new PropertyValueFactory<Principal, String>("firstname"));
		colLastname.setCellValueFactory(new PropertyValueFactory<Principal, String>("lastname"));
		colCompany.setCellValueFactory(new PropertyValueFactory<Principal, String>("company"));
		colZip.setCellValueFactory(new PropertyValueFactory<Principal, String>("zip"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Principal, String>("place"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		principalTableView.setItems(filteredData);
		
		//Listener für Änderungen im Suchenfeld
		txtSearch.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilteredData();				
			}
		});
		
	}
	
	/**
	 * Wechselt zum PrincipalAddView, um einen neuen Bauherr hinzuzufügen.
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
	 * Wechselt zum PrincipalAddView, um einen Bauherr zu bearbeiten
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void editPrincipal(MouseEvent t) throws IOException {
        
        //Wenn Doppelklick auf Person
        if(t.getClickCount() == 2) {
        		
	        	//Angeklickte Firma laden
				Principal principalToEdit = (Principal) principalTableView.getSelectionModel().getSelectedItem();
	
				//FXMLLoader erstellen
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PrincipalAddView.fxml"));
				
				//Neuen View laden
				Pane myPane = loader.load();
	
				//UserAddController holen
				PrincipalAddController controller = loader.<PrincipalAddController>getController();
				
				//Controller starten
				controller.initData(principalToEdit);			
				
				//Neuen View einfügen
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
        
        }
	}
	
	/**
	 * Initialisiert den PrincipalAddController mit einer Meldung, welche im GUI ausgegeben wird.
	 * @param message
	 */
	public void initWithMessage(String message) {
		lblMeldung.setText(message);
	}
	
	/**
	 * Aktualisiert die angezeigten Bauherren. Gehört zur "Suchen.." - Funktion.
	 */
	private void updateFilteredData() {
		filteredData.clear();

		for(Principal p : data) {
			if(matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reaplyTableSortOrder();
	}
			
	/**
	 * Überprüft, ob ein Bauherr dem "Suchen..." - Kriterium entspricht. Gehört zur "Suchen..." - Funktion
	 * @param p
	 * @return
	 */
	private boolean matchesFilter(Principal p) {
		String filterString = txtSearch.getText();

		if(filterString == null || filterString.isEmpty()) {
			//No filter --> add all
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		if((p.getFirstname() != null) && (p.getFirstname().toLowerCase().indexOf(lowerCaseFilterString) != -1)) {
			return true;
		}
		
		if((p.getLastname() != null) && (p.getLastname().toLowerCase().indexOf(lowerCaseFilterString) != -1)) {
			return true;
		}
		
		if((p.getCompany() != null) && (p.getCompany().toLowerCase().indexOf(lowerCaseFilterString) != -1)) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Aktualisiert den TableView. Gehört zur "Suchen.."-Funktion.
	 */
	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Principal, ?>> sortOrder = new ArrayList<>(principalTableView.getSortOrder());
		principalTableView.getSortOrder().clear();
		principalTableView.getSortOrder().addAll(sortOrder);
	}

}
