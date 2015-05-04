package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Subcontractor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * Controller der Subunternehmen-Seite
 * @author Kevin Stadelmann, Alexander Hauck
 * @since 16.04.2015
 * @version 1.1
 *
 */

public class SubcontractorController {
	
	ObservableList<Company> data = FXCollections.observableArrayList(); //Beinhaltet alle Subunternehmen bei der Initialisation
	ObservableList<Company> filteredData = FXCollections.observableArrayList(); //Contains filtered Data (search-function...)
	
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
	private void editSubcontractor(MouseEvent t) throws IOException {
        
        //Wenn Doppelklick auf Person
        if(t.getClickCount() == 2) {
        		
	        	//Angeklickte Firma laden
				Company subcontractorToEdit = (Subcontractor) subcontractorTableView.getSelectionModel().getSelectedItem();
	
				//FXMLLoader erstelen
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
        
        }
	}
	
	
	/**
	 * Initialisiert den Subunternehmen-View. Holt automatisch alle Daten aus der Datenbank und befüllt den TableView damit.
	 */
	public void initialize() {
		
		//Subunternehmen aus Datenbank laden
		Client client = new ResteasyClientBuilder().build();
	    WebTarget target = client.target("http://localhost:8080/webservice");
	    ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
	    
	    SubcontractorProxy subcontractorProxy = rtarget.proxy(SubcontractorProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Subcontractor> subcontractorList = null;
		try {
			subcontractorList = mapper.readValue(subcontractorProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//Subunternhemen der ObservableList zuweisen
		Subcontractor subcontractorFromDB = new Subcontractor();
		for(int i=0; i<subcontractorList.size(); i++) {
			subcontractorFromDB = subcontractorList.get(i);
			data.add(subcontractorFromDB);
			filteredData.add(subcontractorFromDB);
			subcontractorFromDB = null;
		}
		
		
		//Spalten-Values definieren (müssen den Parameter des Company-Objekts entsprechen)
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
				// TODO Auto-generated method stub
				updateFilteredData();				
			}
			
		});
	}
	
	//Observable-List mit den gefilterten Daten aktualisieren
	public void updateFilteredData() {
		filteredData.clear();

		for(Company p : data) {
			if(matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reaplyTableSortOrder();
	}
	
	//Überprüfen, ob Suchbegriff mit Daten übereinstimmt
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
	
	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Company, ?>> sortOrder = new ArrayList<>(subcontractorTableView.getSortOrder());
		subcontractorTableView.getSortOrder().clear();
		subcontractorTableView.getSortOrder().addAll(sortOrder);
	}
	
	
}
