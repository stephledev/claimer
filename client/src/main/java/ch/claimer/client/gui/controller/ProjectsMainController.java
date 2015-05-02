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
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */
public class ProjectsMainController implements Initializable{
	
	
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    
    ObjectMapper mapper;
    List<Project> projectsToShow = null;

	
	//Beinhaltet alle Projekte bei der Initialisation
	ObservableList<Project> data = FXCollections.observableArrayList(); 
	//Contains filtered Data (search-function...)
	ObservableList<Project> filteredData = FXCollections.observableArrayList(); 
	
	
	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Project> projectTableView;

	@FXML
	private TableColumn<Project, String> colProject;
	
	@FXML
	private TableColumn<Project, String> colPrincipal;

	@FXML
	private TableColumn<Project, String> colSupervisor;

	@FXML
	private TableColumn<Project, String> colStart;

	@FXML
	private TableColumn<Project, String> colEnd;

	@FXML
	private Button bttn_addProject;

	@FXML
	private TextField txt_search;

	@FXML
	private Label lbl_title;
	

	// Zur ProjectAdd-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource(
				"../view/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
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
			Project projectID = (Project) projectTableView.getSelectionModel().getSelectedItem();

			//FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../view/ProjectAddView.fxml")
				);
			
			//Neuen View laden
			Pane myPane = loader.load();

			//UserAddController holen
			ProjectAddController controller = loader.<ProjectAddController>getController();
			
			//Controller starten
			controller.initData(projectID);			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);

		}
	}
	
	/**
	 * Lädt alle Projekte aus der Datenbank
	 */
	private void getProject() {

	    Project project = new Project();
	    ProjectProxy projectProxy = rtarget.proxy(ProjectProxy.class);
	    
	    try {
	    	projectsToShow = mapper.readValue(projectProxy.getAll(), new TypeReference<List<Project>>(){});
			
			for(int i = 0; i < projectsToShow.size(); i++) {
			    	
					project = projectsToShow.get(i);
			    	data.add(project);
			    	filteredData.add(project);
			    	project = null;
			    	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    projectsToShow = null;
	    
	}
	


	/**
	 * Initialisiert den TableView automatisch mit den nötigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		getProject();
		
		
		
		// Spalten-Values definieren (müssen den Parameter des Company-Objekts
		// entsprechen)
		colProject
				.setCellValueFactory(new PropertyValueFactory<Project, String>(
						"name"));

		// TODO Kunde muss sichtbar sein
		// colPrincipal.setCellValueFactory(new PropertyValueFactory<Project,
		// String>("principal"));
		colSupervisor
				.setCellValueFactory(new PropertyValueFactory<Project, String>(
						"supervisor"));
		colStart.setCellValueFactory(new PropertyValueFactory<Project, String>(
				"start"));
		colEnd.setCellValueFactory(new PropertyValueFactory<Project, String>(
				"end"));

		// Observable-List, welche die Daten beinhaltet, an die Tabelle
		// übergeben
		projectTableView.setItems(filteredData);

		// Listener für Änderungen im Suchenfeld
		txt_search.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
				updateFilteredData();
			}

		});
	}
	
	// Observable-List mit den gefilterten Daten aktualisieren
	public void updateFilteredData() {
		filteredData.clear();

		for (Project p : data) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}

		reaplyTableSortOrder();
	}

	// Überprüfen, ob Suchbegriff mit Daten übereinstimmt
	private boolean matchesFilter(Project p) {
		String filterString = txt_search.getText();

		if (filterString == null || filterString.isEmpty()) {
			// No filter --> add all
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if (p.getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false;

	}

	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Project, ?>> sortOrder = new ArrayList<>(
				projectTableView.getSortOrder());
		projectTableView.getSortOrder().clear();
		projectTableView.getSortOrder().addAll(sortOrder);
	}
		
	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
		client = new ResteasyClientBuilder().build();
		target = client.target("http://localhost:8080/webservice");
		rtarget = (ResteasyWebTarget) target;
		mapper = new ObjectMapper();
	}
		


	

}
