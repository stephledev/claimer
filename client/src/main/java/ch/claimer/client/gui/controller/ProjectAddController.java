package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.proxy.RoleProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.Role;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.shared.models.Type;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class ProjectAddController implements Initializable {
	
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    ObjectMapper mapper;
    
    List<Issue> issuesToShow = null;
    List<Project> projectsToShow = null;
	ObservableList<Issue> data = FXCollections.observableArrayList();

	private  Integer projectId = null;

	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	@FXML
	private Label lbl_title;

	@FXML
	private Button bttn_saveProject;

	@FXML
	private Button bttn_quitProject;

	@FXML
	private Button bttn_addMangle;

	@FXML
	private TextField txt_projectName;

	@FXML
	private TextField txt_projectId;
	
	@FXML
	private TextField txt_street;

	@FXML
	private TextField txt_Zip;

	@FXML
	private TextField txt_place;

	@FXML
	private TextField txt_principalPhone;

	@FXML
	private ComboBox<String> combo_supervisor;

	@FXML
	private ComboBox<String> combo_status;
	
	@FXML
	private ComboBox<String> combo_type;

	@FXML
	private ComboBox<String> combo_principal;
	
	@FXML
	private ComboBox<String> combo_Category;

	@FXML
	private DatePicker date_start;

	@FXML
	private DatePicker date_end;

	@FXML
	private TableView<Issue> mangleTableView;

	@FXML
	private TableColumn<Issue, String> colMangle;

	@FXML
	private TableColumn<Issue, String> colSubcontractor;

	@FXML
	private TableColumn<Issue, String> colDeadline;

	@FXML
	private TableColumn<Issue, String> colStatus;


	// "Mangel hinzufügen"-Button: zur ProjectMangle-Ansicht wechseln
	@FXML
	private void loadProjectMangleView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectMangleView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}

	// "Abbrechen"-Button: zur ProjectMain-Ansicht wechseln 
	@FXML
	private void loadProjectMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectsMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}

	// "Speicher"-Button: Speichert das Projekt
	@FXML
	private void saveProject() {

		Project project = new Project();
		
		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		project = (Project) getTextfieldProperties(project);
		
		ProjectProxy projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
		if(projectId != null) {
			projectProxy.update(project);
		} else {
			projectProxy.create(project);
		}
		
		showMainViewWithMessage();
		
	}
	
	private Project getTextfieldProperties(Project p1) {

		p1.setName(txt_projectName.getText());
//		p1.setStart(date_start.getValue());
//		p1.setEnd(date_end.getValue());
//		p1.setSupervisor(combo_supervisor.getValue());
		p1.setStreet(txt_street.getText());
		p1.setZip(txt_Zip.getText());
		p1.setPlace(txt_place.getText());
//		p1.setState(combo_status.getValue());
//		p1.setCategory(combo_Category.getValue());
//		p1.setContacts(combo_principal.getValue());
//		p1.setType(combo_type.getValue());
		if(projectId != null) {
			p1.setId(projectId);
		}	
			return p1;

	}

		
		
	public void initData(Project project) {
		
		initiateWebserviceConnection();

		Issue issue = new Issue();
	    IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);
	    
//	    try {
//	    	issuesToShow = mapper.readValue(issueProxy.getByProject(projectId), new TypeReference<List<Issue>>(){});
//			
//			for(int i = 0; i < issuesToShow.size(); i++) {
//					issue = issuesToShow.get(i);
//					data.add(issue);
//			    	issue = null;  	
//			}
//			
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}	    
//
//		//Tabelle initialisieren
//		colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
//		colSubcontractor.setCellValueFactory(new PropertyValueFactory<Issue, String>("subcontractor"));
//		colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("solved"));
//		colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("state"));
//
//		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
//		mangleTableView.setItems(data);

		
		lbl_title.setText("Benutzer bearbeiten");
		projectId = project.getId();

		txt_projectId.setText(project.toString());
	
		if(project.getName() != null) { 
		txt_projectName.setText(project.getName());	
		}
//		if(project.getStart() != null) { 
//		date_start.setStart(project.getStart());
//		}
//		if(project.getEnd() != null) { 
//		date_end.setEnd(project.getEnd());
//		}
		if(project.getSupervisor().getLastname() != null) { 
		combo_supervisor.setValue(project.getSupervisor().getLastname());
		}
		if(project.getStreet() != null) { 
		txt_street.setText(project.getStreet());
		}
		if(project.getZip() != null) { 
		txt_Zip.setText(project.getZip());
		}
		if(project.getPlace() != null) { 
		txt_place.setText(project.getPlace());	
		}
//		if(project.getState().getName() != null) { 
//		combo_status.setValue(project.getState().getName());	
//		}
//		if(project.getCategory().getName() != null) { 
//		combo_Category.setValue(project.getCategory().getName());	
//		}
//		if(project.getPrincipals() != null) { 
//		combo_principal.setValue(project.getPrincipals());
//		}
//		if(project.getType().getName() != null) { 
//		combo_type.setValue(project.getType().getName());
//		}
		
		//Mängel des Projekts aus der DB laden
		
		
}

	
	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
	    rtarget = ResteasyClientUtil.getTarget();
	    mapper = new ObjectMapper();
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_projectId.setEditable(false);
		
		initiateWebserviceConnection();
		setDropdownSupervisor();
	}
	
	@FXML
	private void editIssue(MouseEvent t) throws IOException {

		// Wenn Doppelklick auf Mangel
		if (t.getClickCount() == 2) {

			// Angeklickte Mangel laden
			Issue issueID = (Issue) mangleTableView.getSelectionModel()
					.getSelectedItem();

			// FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"../view/ProjectMangleView.fxml"));

			// Neuen View laden
			Pane myPane = loader.load();

			// UserAddController holen
			ProjectMangleController controller = loader
					.<ProjectMangleController> getController();

			// Controller starten
			controller.initData(issueID);

			// Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		}
	}
	
	private void showMainViewWithMessage() {

		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectsMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//ProjectMainController holen
			ProjectsMainController controller = loader.<ProjectsMainController>getController();
			
			//Controller starten
			controller.initWithMessage("Änderungen erfolgreich vorgenommen.");			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Werte für das "Funktionen"-Dropdown setzen
	 */
	public void setDropdownSupervisor()  {
		
		SupervisorProxy supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);		
	    ObjectMapper mapper = new ObjectMapper();	    
	    List<Supervisor> supervisorList = null;
	    
		try {
			supervisorList = mapper.readValue(supervisorProxy.getAll(), new TypeReference<List<Supervisor>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Rollen dem Dropdown hinzufügen
		for(Supervisor supervisor: supervisorList) {
			combo_supervisor.getItems().add(supervisor.getLastname());
		}
	}

}