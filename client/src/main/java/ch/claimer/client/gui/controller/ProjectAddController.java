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

import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
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
	private ComboBox<Supervisor> combo_supervisor;

	@FXML
	private ComboBox<State> combo_status;
	
	@FXML
	private ComboBox<Type> combo_type;

	@FXML
	private ComboBox<Person> combo_principal;
	
	@FXML
	private ComboBox<Category> combo_Category;

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
		Pane myPane = FXMLLoader.load(getClass().getResource(
				"../view/ProjectsMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}

	// "Speicher"-Button: Speichert das Projekt
	@FXML
	private void saveProject(ActionEvent event) throws IOException {

		Project p1 = new Project();

		p1.setName(txt_projectName.getText());
//		p1.setStart(date_start.getValue());
//		p1.setEnd(date_end.getValue());
		p1.setSupervisor(combo_supervisor.getValue());
		p1.setStreet(txt_street.getText());
		p1.setZip(txt_Zip.getText());
		p1.setPlace(txt_place.getText());
		p1.setState(combo_status.getValue());
		p1.setCategory(combo_Category.getValue());
//		p1.setContacts(combo_principal.getValue());
		p1.setType(combo_type.getValue());

		//Neues Projekt erstellen oder bestehendes updaten
		if(projectId != null) {
			// TODO
			System.out.println("Update Project with id " + projectId);
		} else {
			// TODO
			System.out.println("Create new Project");	
		}

		//Subcontractor Mitarbeiter auslesen und Updaten
		ObservableList<Issue> pList = mangleTableView.getItems();

		for(Issue i : pList) {
			// TODO
			System.out.println("Update Project with ID" + i.getId());
		}
		
		showMainViewWithMessage();

	}

		
	public void initData(Project project) {
		
		lbl_title.setText("Benutzer bearbeiten");
		projectId = project.getId();
	
		txt_projectId.setText(project.toString());
		txt_projectName.setText(project.getName());	
//		date_start.setStart(project.getStart());
//		date_end.setEnd(project.getEnd());
		combo_supervisor.setValue(project.getSupervisor());
		txt_street.setText(project.getStreet());	
		txt_Zip.setText(project.getZip());	
		txt_place.setText(project.getPlace());	
		combo_status.setValue(project.getState());	
		combo_Category.setValue(project.getCategory());	
//		combo_principal.setValue(project.getPrincipals());	
		combo_type.setValue(project.getType());
		
		//Mängel des Projekts aus der DB laden
		IssueProxy IssueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		ObjectMapper mapper = new ObjectMapper();
		List<Issue> issueList = null;

		Issue issue = new Issue();

		try {

			issueList = mapper.readValue(IssueProxy.getById(projectId), new TypeReference<List<Issue>>(){});

			for(int i = 0; i < issueList.size(); i++) {

				// TODO Überprüfen, ob die Person überhaupt zum Subunternehmen gehört
				issue = issueList.get(i);
				data.add(issue);
				issue = null;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		issueList = null;

		//Tabelle initialisieren
		colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
		colSubcontractor.setCellValueFactory(new PropertyValueFactory<Issue, String>("subcontractor"));
		colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("solved"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("state"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		mangleTableView.setItems(data);

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
		getIssue();

		// Spalten-Values definieren (müssen den Parameter des Mangel-Objekts entsprechen)
		colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
		colSubcontractor.setCellValueFactory(new PropertyValueFactory<Issue, String>("subcontractor"));
		colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("end"));
		colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("state"));

		mangleTableView.setItems(data);
	}
	
	/**
	 * Lädt alle Mängel aus der Datenbank
	 */
	private void getIssue() {

	    Issue issue = new Issue();
	    IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);
	    
	    try {
	    	issuesToShow = mapper.readValue(issueProxy.getByProject(projectId), new TypeReference<List<Issue>>(){});
			
			for(int i = 0; i < issuesToShow.size(); i++) {
			    	
					issue = issuesToShow.get(i);
			    	data.add(issue);
			    	
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    issuesToShow = null;
	    
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
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//PrincipalController holen
			UserController controller = loader.<UserController>getController();
			
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

}