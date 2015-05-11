package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CategoryProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.PrincipalProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.StateProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.proxy.TypeProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Principal;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.SCEmployee;
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

	// Views werden ins mainContent-Pane geladen
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
	private ComboBox<String> combo_Status;
	
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
	
	// liest Textfelder aus und speichert Daten des Projektes in der DB
	// Dropdown-Felder füllen
	private Project getTextfieldProperties(Project p1) {

		p1.setName(txt_projectName.getText());
		p1.setStreet(txt_street.getText());
		p1.setZip(txt_Zip.getText());
		p1.setPlace(txt_place.getText());
		if(projectId != null) {
			p1.setId(projectId);
		}	
		
		//Supervisor aus DB holen 
		SupervisorProxy supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);		
		List<Supervisor> supervisorList = null;

		try {
			supervisorList = mapper.readValue(supervisorProxy.getAll(), new TypeReference<List<Type>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Typ dem Dropdown hinzufügen
		for(Supervisor supervisor: supervisorList) {
			if(supervisor.getLastname().equals(combo_supervisor.getValue()))
				p1.setSupervisor(supervisor);	
		}

		// Status aus DB holen 
		StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);			    
	    List<State> stateList = null;
	    
		try {
			stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
			}
		
		//Status dem Dropdown hinzufügen
		for(State state: stateList) {
			if(state.getName().equals(combo_Status.getValue()))
				p1.setState(state);	
			}

		//Typ aus DB holen 
		TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);		
		List<Type> typeList = null;

		try {
			typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
			}

		//Typ dem Dropdown hinzufügen
		for(Type type: typeList) {
			if(type.getName().equals(combo_type.getValue()))
				p1.setType(type);	
			}
		
		//Kategorie aus DB holen 
		CategoryProxy categoryProxy = ResteasyClientUtil.getTarget().proxy(CategoryProxy.class);		
		List<Category> categoryList = null;

		try {
			categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
			}

		//Kategorie dem Dropdown hinzufügen
		for(Category category: categoryList) {
			if(category.getName().equals(combo_Category.getValue()))
				p1.setCategory(category);
			}
		
		//KUNDE aus DB holen
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);		
		List<Principal> principalList = null;

		try {
			principalList = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
		
//TODO Kunden im Dropdown anzeigen - .setPrincipals(principal) nicht möglich
		//Rollen dem Dropdown hinzufügen
		for(Principal principal: principalList) {
			if(principal.getLastname().equals(combo_principal.getValue()))
				p1.setPrincipals(principalList);
		}
		
		return p1;
	}

		
		
	public void initData(Project project) {
		initiateWebserviceConnection();
		
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
		if(project.getSupervisor() != null) { 
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
		if(project.getState() != null) { 
		combo_Status.setValue(project.getState().getName());	
		}
		if(project.getCategory() != null) { 
		combo_Category.setValue(project.getCategory().getName());	
		}
//		TODO
//		if(project.getPrincipals() != null) { 
//		combo_principal.setValue(project.getPrincipals();
//		}	
		
		//Mitarbeiter der Firma aus der DB laden
		IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Issue> issueList = null;

		try {

			issueList = mapper.readValue(issueProxy.getByProject(projectId), new TypeReference<List<Issue>>(){});
			for(Issue i : issueList) {
				data.add(i);
			}

		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	    fillTableView();

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
		setDropdownPrincipal();
		setDropdownCategory();
		setDropdownState();
		setDropdownType();
	    fillTableView();

	}
	
	private void fillTableView(){
		//Tabelle initialisieren
		colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
		colSubcontractor.setCellValueFactory(new PropertyValueFactory<Issue, String>("subcontractor"));
		colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("solved"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("state"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		mangleTableView.setItems(data);
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
			e1.printStackTrace();
		}

		//Bauleiter dem Dropdown hinzufügen
		for(Supervisor supervisor: supervisorList) {
			combo_supervisor.getItems().add(supervisor.getLastname());
		}
	}

	public void setDropdownState()  {
		
		StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);		
	    ObjectMapper mapper = new ObjectMapper();	    
	    List<State> stateList = null;
	    
		try {
			stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Status dem Dropdown hinzufügen
		for(State state: stateList) {
			combo_Status.getItems().add(state.getName());
		}
	}
	
	public void setDropdownType()  {
	
		TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);		
		ObjectMapper mapper = new ObjectMapper();	    
		List<Type> typeList = null;

		try {
			typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Typ dem Dropdown hinzufügen
		for(Type type: typeList) {
			combo_type.getItems().add(type.getName());
		}
	}

	public void setDropdownCategory()  {

		CategoryProxy categoryProxy = ResteasyClientUtil.getTarget().proxy(CategoryProxy.class);		
		ObjectMapper mapper = new ObjectMapper();	    
		List<Category> categoryList = null;

		try {
			categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Kategorie dem Dropdown hinzufügen
		for(Category category: categoryList) {
			combo_Category.getItems().add(category.getName());
		}
	}
	public void setDropdownPrincipal()  {

		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);		
		ObjectMapper mapper = new ObjectMapper();	    
		List<Principal> principalList = null;

		try {
			principalList = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Rollen dem Dropdown hinzufügen
		for(Principal principal: principalList) {
			combo_principal.getItems().add(principal.getLastname());
		}
	}
	
//	/**
//	 * Lädt alle Mängel aus der Datenbank
//	 */
//	private void getIssue(Integer a) {
//		
//	    Issue issue = new Issue();
//	    IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);
//	    
//	    try {
//	    	issuesToShow = mapper.readValue(issueProxy.getByProject(a), new TypeReference<List<Issue>>(){});
//
//			
//			for(int i = 0; i < issuesToShow.size(); i++) {
//					issue = issuesToShow.get(i);
//			    	data.add(issue);
//			    	issue = null;
//			    	
//			}
//		} catch (IOException e1) {
//			
//			e1.printStackTrace();
//		}
//		
//	    issuesToShow = null;   
//	}
	
	public void initWithMessage(String string) {
		lbl_title.setText(string);

	}
	
	/**
	 * Öffnet die Detailansicht für einen User, um diesen zu bearbeiten.
	 * @param t
	 * @throws IOException
	 */
	
	@FXML
	private void editProject(MouseEvent t) throws IOException {
		
		//Wenn Doppelklick auf Projekt
		if(t.getClickCount() == 2) {
			
			//Angeklickte Projekt laden
			Issue issueId = (Issue) mangleTableView.getSelectionModel().getSelectedItem();

			//FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectMangleView.fxml"));
			
			//Neuen View laden
			Pane myPane = loader.load();

			//UserAddController holen
			ProjectMangleController controller = loader.<ProjectMangleController>getController();
			
			//Controller starten
			controller.initData(issueId);			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		}
	}
	
}