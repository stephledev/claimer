package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;






import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller der Subunternehmen-Seite
 * @author Alexander Hauck
 * @since 1.0
 * @version 1.1
 *
 */

public class SubcontractorController {
	
	ObservableList<Company> data = FXCollections.observableArrayList(); //Beinhaltet alle Subunternehmen bei der Initialisation
	ObservableList<Company> filteredData = FXCollections.observableArrayList(); //Beinhaltet alle Subunternehmen, die dem Suchkriterium entsprechen.
	Integer roleValue = AuthenticationUtil.getLogin().getRole().getValue();
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TextField txtSearch;
	
	@FXML
	private TableView<Company> subcontractorTableView;
	
	@FXML
	private TableColumn<Company, String> colName;
	
	@FXML
	private TableColumn<Company, String> colZip;
	
	@FXML
	private TableColumn<Company, String> colPlace;
	
	@FXML
	private TableColumn<Company, String> colPhone;
	
	@FXML
	private TableColumn<Company, String> colPlz;
	
	@FXML
	private TableColumn<Company, String> colEmail;
	
	@FXML
	private Label lblMessage;
	

	/**
	 * Initialisiert den Subunternehmen-View. Befüllt den TableView mit Daten aus der Datenbank.
	 */
	public void initialize() {
		
		//Subunternehmen aus Datenbank laden
		SubcontractorProxy subcontractorProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Subcontractor> subcontractorList = null;
		
		if(roleValue == 15) {
			SCEmployee sce = new SCEmployee();
			sce = (SCEmployee)AuthenticationUtil.getPerson();
			System.out.println(sce.getSubcontractor().getId());

			try {
				Subcontractor sc = mapper.readValue(subcontractorProxy.getById(sce.getSubcontractor().getId()), new TypeReference<Subcontractor>(){});
				data.add(sc);
				filteredData.add(sc);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			
			
		} else {
			
			try {
				subcontractorList = mapper.readValue(subcontractorProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
				
				//Subunternhemen der ObservableList zuweisen
				for(Subcontractor sc : subcontractorList) {
					data.add(sc);
					filteredData.add(sc);
				}
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		
		
		
		
		//Spalten-Values definieren
		colName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Company, String>("place"));
		colZip.setCellValueFactory(new PropertyValueFactory<Company, String>("zip"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		subcontractorTableView.setItems(filteredData);
		
		//Listener für Änderungen im Suchenfeld
		txtSearch.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilteredData();				
			}
			
		});
	}
	
	/**
	 * Initialisiert den View mit einer Meldung, die im GUI ausgegeben wird.
	 * @param string - Mitteilung die angezeigt wird.
	 */
	public void initWithMessage(String string) {
		lblMessage.setText(string);
	}
	
	/**
	 * Lädt einen neuen View, in dem ein neues Subunternehmen hinzugefügt werden kann.
	 * 
	 */
	@FXML
	private void loadSubcontractorAddView() {

		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);	
		} catch (IOException e) {
			// TODO ERROR-LOGGING
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Lädt einen neuen View, in dem das angeklickte Subunternehmen bearbeitet werden kann.
	 * @param t - MouseEvent = Klick auf Subunternehmen
	 * @throws IOException
	 */
	@FXML
	private void editSubcontractor(MouseEvent t) {
        
        //Wenn Doppelklick auf Person
        if(t.getClickCount() == 2) {
        		
	        	//Angeklickte Firma laden
				Company subcontractorToEdit = (Subcontractor) subcontractorTableView.getSelectionModel().getSelectedItem();
	
				try {
					//FXMLLoader erstellen
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SubcontractorAddView.fxml"));
					
					//Neuen View laden
					Pane myPane = loader.load();
					
					//UserAddController holen
					SubcontractorAddController controller = loader.<SubcontractorAddController>getController();
					
					//Controller starten
					controller.initData(subcontractorToEdit);			
					
					//Neuen View einfügen
					mainContent.getChildren().clear();
					mainContent.getChildren().setAll(myPane);
					
					
				} catch (IOException e) {
					// TODO ERROR-LOGGIN
					e.printStackTrace();
				}
        }
	}
	
	/**
	 * Aktualisiert die angezeigten Bauherren. Gehört zur "Suchen.." - Funktion.
	 */
	public void updateFilteredData() {
		filteredData.clear();

		for(Company p : data) {
			if(matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reaplyTableSortOrder();
	}
	
	/**
	 * Überprüft, ob ein Subunternehmen dem "Suchen..." - Kriterium entspricht. Gehört zur "Suchen..." - Funktion
	 * @param p - Subunternehmen welches auf Kriterum überprüft wird.
	 * @return
	 */
	private boolean matchesFilter(Company p) {
		String filterString = txtSearch.getText();

		if(filterString == null || filterString.isEmpty()) {
			//No filter --> add all
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if(p.getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Aktualisiert den TableView. Gehört zur "Suchen.."-Funktion.
	 */
	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Company, ?>> sortOrder = new ArrayList<>(subcontractorTableView.getSortOrder());
		subcontractorTableView.getSortOrder().clear();
		subcontractorTableView.getSortOrder().addAll(sortOrder);
	}	
}
