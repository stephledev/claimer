package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.util.Callback;

/**
 * @author Michael L�tscher
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
	ObservableList<Project> dataCopy = FXCollections.observableArrayList(); 
	
	
	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Project> projectTableView;

	@FXML
	private TableColumn<Project, String> colProject;
	
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
	

	// Zur ProjectAddView wechseln 
	@FXML
	private void loadProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);		
	}
	
	
	/**
	 * �ffnet die Detailansicht f�r einen User, um diesen zu bearbeiten.
	 * @param t
	 * @throws IOException
	 */
	@FXML
	private void editProject(MouseEvent t) throws IOException {
		
		//Wenn Doppelklick auf Projekt
		if(t.getClickCount() == 2) {
			
			//Angeklickte Projekt laden
			Project projectID = (Project) projectTableView.getSelectionModel().getSelectedItem();

			//FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectAddView.fxml"));
			
			//Neuen View laden
			Pane myPane = loader.load();

			//UserAddController holen
			ProjectAddController controller = loader.<ProjectAddController>getController();
			
			//Controller starten
			controller.initData(projectID);			
			
			//Neuen View einf�gen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		}
	}
	
	
	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
	    rtarget = ResteasyClientUtil.getTarget();
	    mapper = new ObjectMapper();
	}
	
	
	/**
	 * L�dt alle Projekte aus der Datenbank
	 */
	private void getProject() {

	    Project project = new Project();
	    ProjectProxy projectProxy = rtarget.proxy(ProjectProxy.class);
	    
	    try {
	    	projectsToShow = mapper.readValue(projectProxy.getAll(), new TypeReference<List<Project>>(){});
			
			for(int i = 0; i < projectsToShow.size(); i++) {
					project = projectsToShow.get(i);
			    	data.add(project);
			    	dataCopy.add(project);
			    	project = null;
			    	
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	    projectsToShow = null;
	    
	}
	


	/**
	 * Initialisiert den TableView automatisch mit den n�tigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		initiateWebserviceConnection();
		getProject();
		
		
		
		// Spalten-Values definieren (m�ssen den Parameter des Company-Objekts entsprechen)
		colProject.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
		colSupervisor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getSupervisor().getLastname());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });

		colStart.setCellValueFactory(new PropertyValueFactory<Project, String>("end"));
		colEnd.setCellValueFactory(new PropertyValueFactory<Project, String>("end"));

		// Observable-List, welche die Daten beinhaltet, an die Tabelle
		// �bergeben
		projectTableView.setItems(data);

		// Listener f�r �nderungen im Suchenfeld
		txt_search.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {

				updateDataCopy();
			}

		});
	}
	
	
	// Observable-List mit den gefilterten Daten aktualisieren
	public void updateDataCopy() {
		data.clear();

		for (Project p : dataCopy) {
			if (matchesFilter(p)) {
				data.add(p);
			}
		}

		reaplyTableSortOrder();
	}
	

	// �berpr�fen, ob Suchbegriff mit Daten �bereinstimmt
	private boolean matchesFilter(Project p) {
		String filterString = txt_search.getText();

		if(filterString == null || filterString.isEmpty()) {
			//No filter --> add all
			return true;
		}
		
		String lowerCaseFilterString = filterString.toLowerCase();
		
		if(p.getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}
		
		if(p.getSupervisor().getLastname().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
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
	
	public void initWithMessage(String string) {
		lbl_title.setText(string);
		
	}

}
