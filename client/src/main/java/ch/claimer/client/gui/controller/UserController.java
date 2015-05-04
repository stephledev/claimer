package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Supervisor;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;



/**
 * User-Übersicht erstellen (als TableView)
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 2.0
 *
 */

public class UserController implements Initializable {
	
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    
    ObjectMapper mapper;
    List<Person> personsToShow = null;

	ObservableList<Person> data = FXCollections.observableArrayList();
	ObservableList<Person> dataCopy = FXCollections.observableArrayList();
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableView<Person> userTableView;
	
	@FXML
	private TableColumn<Person, String> colLastname;
	
	@FXML
	private TableColumn<Person, String> colName;
	
	@FXML
	private TableColumn<Person, String> colEmail;
	
	@FXML
	private TableColumn<Person, String> colFunction;
	
	@FXML
	private TableColumn<Person, String> colUsername;
	
	@FXML
	private TextField txtSearch;
	
	/**
	 * Öffnet die Detailansicht für einen User, um diesen zu bearbeiten.
	 * @param t
	 * @throws IOException
	 */
	@FXML
	private void editUser(MouseEvent t) throws IOException {
		
		//Wenn Doppelklick auf Person
		if(t.getClickCount() == 2) {
			
			//Angeklickte Person laden
			Person personID = (Person) userTableView.getSelectionModel().getSelectedItem();

			//FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../view/UserAddView.fxml")
				);
			
			//Neuen View laden
			Pane myPane = loader.load();

			//UserAddController holen
			UserAddController controller = loader.<UserAddController>getController();
			
			//Controller starten
			controller.initData(personID);			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);

		}
	}
	
	/**
	 * Zum UserAddView wechseln. Bei Klick auf Button "Neuen Benutzer erstellen"
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void loadUserAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);		
	}

	/**
	 * Initialisiert den TableView automatisch mit den nötigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	    initiateWebserviceConnection();
	    
	    getGCEmployee();
	    
	    getSCEmployee();
	   
	    getSupervisors();
	    
		//Spalten-Values definieren (müssen den Parameter des Personen-Objekts entsprechen)
		colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		colName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
	
		
		colFunction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getLogin().getRole().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
		
		colUsername.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getLogin().getUsername());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
		
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		userTableView.setItems(data);
			
		//Listener für Änderungen im Suchenfeld
		txtSearch.textProperty().addListener(new ChangeListener<String>() {
	
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				updateFilteredData();				
			}
			
		});
		
	}
	
	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
		client = new ResteasyClientBuilder().build();
	    target = client.target("http://localhost:8080/webservice");
	    rtarget = (ResteasyWebTarget)target;
	    mapper = new ObjectMapper();
	}

	/**
	 * Lädt alle gcEmployee aus der Datenbank
	 */
	private void getGCEmployee() {

	    Person gcEmployee = new GCEmployee();
	    GCEmployeeProxy gceProxy = rtarget.proxy(GCEmployeeProxy.class);
	    
	    try {
			personsToShow = mapper.readValue(gceProxy.getGcemployeeAll(), new TypeReference<List<GCEmployee>>(){});
			
			for(int i = 0; i < personsToShow.size(); i++) {
			    	
			    	gcEmployee = personsToShow.get(i);
			    	data.add(gcEmployee);
			    	dataCopy.add(gcEmployee);
			    	gcEmployee = null;
			    	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		personsToShow = null;
	    
	}

	/**
	 * Lädt alle SCEmployees aus der Datebank
	 */
	private void getSCEmployee() {
	    Person scEmployee = new SCEmployee();
	    SCEmployeeProxy sceProxy = rtarget.proxy(SCEmployeeProxy.class);
	    
	    try {
	    	personsToShow = mapper.readValue(sceProxy.getAll(), new TypeReference<List<SCEmployee>>(){});
	    	
	    	for(int i = 0; i < personsToShow.size(); i++) {
	    		scEmployee = personsToShow.get(i);
	    		data.add(scEmployee);
	    		dataCopy.add(scEmployee);
	    		scEmployee = null;
	    	}
	    	
	    } catch (IOException e1) {
	    	e1.printStackTrace();
	    }
	    
	    personsToShow = null;
	}
	
	
	/**
	 * Lädt alle Supervisors aus der Datenbank
	 */
	private void getSupervisors() {

		Person supervisor = new Supervisor();
	    SupervisorProxy svProxy = rtarget.proxy(SupervisorProxy.class);
	    
	    try {
	    	personsToShow = mapper.readValue(svProxy.getSupervisorAll(), new TypeReference<List<Supervisor>>(){});
	    	
	    	for(int i = 0; i < personsToShow.size(); i++) {
	    		supervisor = personsToShow.get(i);
	    		data.add(supervisor);
	    		dataCopy.add(supervisor);
	    		supervisor = null;
	    	}
	    } catch (IOException e1) {
	    	e1.printStackTrace();
	    }
	    
	    personsToShow = null;

	}
	
	//Observable-List mit den gefilterten Daten aktualisieren
		public void updateFilteredData() {
			data.clear();

			for(Person p : dataCopy) {
				if(matchesFilter(p)) {
					data.add(p);
				}
			}
			
			reaplyTableSortOrder();
		}
		
		//Überprüfen, ob Suchbegriff mit Daten übereinstimmt
		private boolean matchesFilter(Person p) {
			String filterString = txtSearch.getText();

			if(filterString == null || filterString.isEmpty()) {
				//No filter --> add all
				return true;
			}
			
			String lowerCaseFilterString = filterString.toLowerCase();
			
			if(p.getFirstname().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				return true;
			}
			
			if(p.getLastname().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
				return true;
			}
			
			return false;
			
		}
		
		private void reaplyTableSortOrder() {
			ArrayList<TableColumn<Person, ?>> sortOrder = new ArrayList<>(userTableView.getSortOrder());
			userTableView.getSortOrder().clear();
			userTableView.getSortOrder().addAll(sortOrder);
		}

	
}


