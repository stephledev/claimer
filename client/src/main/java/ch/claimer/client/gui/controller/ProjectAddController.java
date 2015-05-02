package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Supervisor;
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
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private  Integer id = null;

	
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
	private ComboBox<State> combo_type;

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

	// "Mangel hinzufügen"-Button: ur ProjectMain-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadProjectMangleView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectMangleView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);

	}

	// "Abbrechen"-Button: zur ProjectMangle-Ansicht wechseln (mainView.xml)
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
		

		if (projectId != null) {
			
			Client client = new ResteasyClientBuilder().build();
			WebTarget target = client.target("http://localhost:8080/webservice");
			ResteasyWebTarget rtarget = (ResteasyWebTarget) target;

			ProjectProxy projectProxy = rtarget.proxy(ProjectProxy.class);
			List<Project> projectList = null;
			try {
				projectsToShow = mapper.readValue(projectProxy.getAll(), new TypeReference<List<Project>>(){});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Mangel der ObservableList zuweisen
			Project projectFromDB = new Project();
			for (int i = 0; i < projectList.size(); i++) {
				projectFromDB = projectList.get(i);
				
				if(projectFromDB.getId() == id){
					
					updateData(projectFromDB);
				}
				
			}
			
		//TODO save as a new project
		} else {
			System.out.println("Insert");
		}

		
	}

	public void initData(Project projectToEdit) {
		lbl_title.setText("Benutzer bearbeiten");
		
		projectId = projectToEdit.getId();
		id = projectToEdit.getId();
		txt_projectId.setText(projectId.toString());
		
		if(projectToEdit.getName() != null) { 
			txt_projectName.setText(projectToEdit.getName());	
		}
		
		if(projectToEdit.getStart() != null) {
			date_start.setChronology(projectToEdit.getStart());
		}
		
		if(projectToEdit.getEnd() != null) {
			date_end.setChronology(projectToEdit.getEnd());
		}
		
		if(projectToEdit.getSupervisor() != null) {
			combo_supervisor.setSelectionModel(projectToEdit.getSupervisor());
		}
		
		if(projectToEdit.getStreet() != null) {
			txt_street.setText(projectToEdit.getStreet());	
		}
		if(projectToEdit.getZip() != null) {
			txt_Zip.setText(projectToEdit.getZip());	
		}
		
		if(projectToEdit.getPlace() != null) {
			txt_place.setText(projectToEdit.getPlace());	
		}
		
		if(projectToEdit.getState() != null) {
			combo_status.setSelectionModel(projectToEdit.getState());	
		}
		
		if(projectToEdit.getCategory() != null) {
			combo_Category.setSelectionModel(projectToEdit.getCategory());	
		}
		
		
		//Sollte der Kunde sein
		if(projectToEdit.getContacts() != null) {
			combo_principal.setSelectionModel((SingleSelectionModel<Person>) projectToEdit.getContacts());	
		}
		
		//Telefonnummer des Kunden
//		if(projectToEdit.getContacts() != null) {
//			txt_principalPhone.setSelectionModel((SingleSelectionModel<Person>) projectToEdit.getContacts());	
//		}
		
//		if(projectToEdit.getType() != null) {
//			combo_type.setSelectionModel(projectToEdit.getType());
//		}
		
	}
	
	
	public void updateData(Project projectToupdate) {
		
		projectId = projectToupdate.getId();

		if (projectToupdate.getName() != null) {
			projectToupdate.setName(txt_projectName.getText());
		}

		if (projectToupdate.getStart() != null) {
			projectToupdate.setStart(date_start.getChronology());
		}

		if (projectToupdate.getEnd() != null) {
			projectToupdate.setEnd(date_end.getChronology());
		}

		if (projectToupdate.getSupervisor() != null) {
			projectToupdate.setSupervisor(combo_supervisor.getSelectionModel());
		}

		if (projectToupdate.getStreet() != null) {
			projectToupdate.setStreet(txt_street.getText());
		}
		if (projectToupdate.getZip() != null) {
			projectToupdate.setZip(txt_Zip.getText());
		}

		if (projectToupdate.getPlace() != null) {
			projectToupdate.setPlace(txt_place.getText());
		}

		if (projectToupdate.getState() != null) {
			projectToupdate.setState(combo_status.getSelectionModel());
		}

		if (projectToupdate.getCategory() != null) {
			projectToupdate.setCategory(combo_Category.getSelectionModel());
		}

		// //Sollte der Kunde sein
		// if(projectToupdate.getContacts() != null) {
		// projectToupdate.setContacts(combo_principal.getSelectionModel());
		// }

		// Telefonnummer des Kunden
		// if(projectToupdate.getContacts() != null) {
		// projectToupdate.setContacts(txt_principalPhone.getSelectionModel());
		// }

		// if(projectToupdate.getType() != null) {
		// projectToupdate.setType(combo_type.getSelectionModel());
		// }

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
	    	issuesToShow = mapper.readValue(issueProxy.getByProject(), new TypeReference<List<Issue>>(){});
			
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

}