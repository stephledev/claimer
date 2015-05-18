package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.pmw.tinylog.Logger;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Supervisor;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.util.Callback;



/**
 * Controller f�r den UserMainView
 * @author Alexander Hauck
 * @since 1.0
 * @version 2.0
 *
 */

public class UserController implements Initializable {
	
	private ObservableList<Person> data = FXCollections.observableArrayList();
	private ObservableList<Person> filteredData = FXCollections.observableArrayList();
	
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
	
	@FXML
	private Label lblMessage;

	
	/**
	 * Initialisiert den TableView mit den Benutzer-Daten aus der Datenbank.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		getGCEmployee();
	    	   
	    getSupervisors();
	    
	    //Spalten-Values definieren
		colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		colName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
	
		
		colFunction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> data) {
				try {
					String roleName = data.getValue().getLogin().getRole().getName();
					switch(roleName) {
						case("superadmin"): roleName = "Sachbearbeiter GU Admin";
						break;
						case("admin"): roleName = "Sachbearbeiter GU";
						break;
						case("editor-intern"): roleName = "Bauleiter";
						break;
					}
					return new SimpleStringProperty(roleName);
				} catch(NullPointerException e) {
					Logger.error("Problem beim Bef�llen der User-Tabelle, Funktions-Spalte.");
					return null;
				}
			}
		  });
		
		colUsername.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getLogin().getUsername());
				} catch(NullPointerException e) {
					Logger.error("Problem beim Bef�llen der User-Tabelle, Username-Spalte.");
					return null;
				}
			}
		  });
				
		//Observable-List, welche die Daten beinhaltet, an die Tabelle �bergeben
		userTableView.setItems(filteredData);
			
		//Listener f�r �nderungen im Suchenfeld
		txtSearch.textProperty().addListener(new ChangeListener<String>() {
	
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updateFilteredData();				
			}
			
		});
		
	}
	
	/**
	 * Initialisiert die Hauptansicht und gibt die �bergebene Meldung im GUI aus.
	 * @param string - Mitteilung die angezeigt werden soll.
	 */
	public void initWithMessage(String string) {
		lblMessage.setText(string);
		
	}
	
	/**
	 * �ffnen einen neuen View, in dem ein neuer Benutzer erfasst werden kann.
	 */
	@FXML
	private void loadUserAddView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/UserAddView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch(IOException | NullPointerException e) {
			Logger.error("View \"UserAddView.fxml\" kann nicht geladen werden.");
		}
				
	}
	
	/**
	 * �ffnet einen neuen View, in dem der angeklickte Benutzer bearbeitet werden kann.
	 * @param t - MouseEvent = Klick auf Benutzer
	 */
	@FXML
	private void editUser(MouseEvent t) {
		
		//Wenn Doppelklick auf Person
		if(t.getClickCount() == 2) {
			try {
				//Angeklickte Person laden
				Person personID = (Person) userTableView.getSelectionModel().getSelectedItem();
	
				//FXMLLoader erstelen
				FXMLLoader loader = new FXMLLoader(
						getClass().getResource("/UserAddView.fxml")
					);
				
				//Neuen View laden
				Pane myPane = loader.load();
	
				//UserAddController holen
				UserAddController controller = loader.<UserAddController>getController();
				
				//Controller starten
				controller.initData(personID);			
				
				//Neuen View einf�gen
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
			} catch(IOException | NullPointerException e) {
				Logger.error("View \"UserAddView.fxml\" kann nicht geladen werden.");
			}

		}
	}
		
	/**
	 * L�dt alle gcEmployee aus der Datenbank
	 */
	private void getGCEmployee() {

		try {
	    	GCEmployeeProxy gceProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		    ObjectMapper mapper = new ObjectMapper();
		    List<GCEmployee> personList = null;
		    personList = mapper.readValue(gceProxy.getAll(), new TypeReference<List<GCEmployee>>(){});
		    
		    for(GCEmployee gce : personList) {
		    	data.add(gce);
		    	filteredData.add(gce);
		    }
	    }
	    catch (IOException e1) {
	    	Logger.error("GCEmployees k�nnen nicht aus der Datenbank geladen werden.");
	    }
	    
	}	
	
	/**
	 * L�dt alle Supervisors aus der Datenbank.
	 */
	private void getSupervisors() {
	    try {
	    	SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		    ObjectMapper mapper = new ObjectMapper();
		    List<Supervisor> personList = null;
		    personList = mapper.readValue(svProxy.getAll(), new TypeReference<List<Supervisor>>(){});
		    
		    for(Supervisor sv : personList) {
		    	data.add(sv);
		    	filteredData.add(sv);
		    }

	    } catch (IOException e1) {
	    	Logger.error("Bauleiter k�nnen nicht aus der Datenbank geladen werden.");
	    }
	}
	
	/**
	 * Aktualisiert die angezeigten Benutzer. Geh�rt zur "Suchen.." - Funktion.
	 */
	public void updateFilteredData() {
		filteredData.clear();

		for(Person p : data) {
			if(matchesFilter(p)) {
				filteredData.add(p);
			}
		}
		
		reaplyTableSortOrder();
	}
	
	/**
	 * �berpr�ft, ob ein Bauherr dem "Suchen..." - Kriterium entspricht. Geh�rt zur "Suchen..." - Funktion
	 * @param p - Bauherr der �berpr�ft werden wird.
	 * @return
	 */
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
	
	/**
	 * Aktualisiert den TableView. Geh�rt zur "Suchen.."-Funktion.
	 */
	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Person, ?>> sortOrder = new ArrayList<>(userTableView.getSortOrder());
		userTableView.getSortOrder().clear();
		userTableView.getSortOrder().addAll(sortOrder);
	}
}