package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CategoryProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.proxy.StateProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.proxy.TypeProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.shared.models.Type;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */
public class ProjectAddController implements Initializable {
	
    private ResteasyWebTarget rtarget;
    private ObjectMapper mapper;
    private List<Issue> issuesToShow = null;
	private ObservableList<Issue> data = FXCollections.observableArrayList();
	public static ObservableList<Issue> dataTransfer = FXCollections.observableArrayList();
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
	private DatePicker dateStart;

	@FXML
	private DatePicker dateEnd;

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
	
	@FXML
	private Label lblProjectID;

	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lbl_title.setText("Neues Projekt erfassen");
		initiateWebserviceConnection();
		setDropdownSupervisor();
		//TODO	
//		setDropdownPrincipal();
		setDropdownCategory();
		setDropdownState();
		setDropdownType();
		
		//Listener,um Änderungen zu überprüfen.
		dataTransfer.addListener(new ListChangeListener<Issue>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Issue> c) {
				System.out.println("changed");
				if(dataTransfer.size() > 0) {
						
					//data.remove(persontoEdit);	//den aktualisierten aus der Liste entfernen		
					//TableView neu Laden
					data.addAll(dataTransfer);
					fillTableView();
				}
				
			}
		 
		 });

	}
	
	
	/**
	 * Initialisiert den View mit den Daten des zu bearbeitenden Projekts.
	 * @param project
	 */
	public void initData(Project project) {
		
		lbl_title.setText("Projekt bearbeiten");
		
		projectId = project.getId();
		lblProjectID.setText(projectId.toString());

		if(project.getName() != null) { 
			txt_projectName.setText(project.getName());	
		}
		
		if(project.getStart() != null) { 
					
			long timestart = project.getStart().getTime().getTime();
			long days = Math.round( (double)timestart / (24. * 60.*60.*1000.));
			Date date = new Date();
			long timenow = date.getTime();
			long daysnow = Math.round( (double)timenow / (24. * 60.*60.*1000.));
			long diff = days - daysnow;
		
			dateStart.setValue(LocalDate.now().plusDays(diff));
		}
			
		if(project.getEnd() != null) { 
			long timeend = project.getEnd().getTime().getTime();
			long days = Math.round( (double)timeend / (24. * 60.*60.*1000.));
			Date date = new Date();
			long timenow = date.getTime();
			long daysnow = Math.round( (double)timenow / (24. * 60.*60.*1000.));
			long diff = days - daysnow;
			
			dateEnd.setValue(LocalDate.now().plusDays(diff));
		}
		
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
		
		if(project.getType().getName() != null) {
			combo_type.setValue(project.getType().getName());
		}

		//TODO Bauherren anzeigen.
		//	if(project.getPrincipals() != null) { 
		//	combo_principal.setValue(project.getPrincipals().get(0).getLastname());
		//	}	
		
		//Mängel aus der Datenbank laden.
		try {
			IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
			List<Issue> issueList = mapper.readValue(issueProxy.getByProject(projectId), new TypeReference<List<Issue>>(){});
			
			for(Issue i : issueList) {
				data.add(i);
			}
	
		} catch (IOException e1) {
			// TODO LOGGING-Eintrag
			e1.printStackTrace();
		}
		
		fillTableView();
	
	}
	
	private void fillTableView() {
		//Mängel-Tabelle initialisieren
		colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
		colSubcontractor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getSubcontractor().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		 });
		
		colDeadline.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
		public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String a = format.format(data.getValue().getSolved().getTime());
					return new SimpleStringProperty(a);
				} catch(NullPointerException e) {
					return null;
				}
			}
		 });
		
		colStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getState().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
	
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		mangleTableView.setItems(data);
	}
	
	// "Mangel hinzufügen"-Button: zur ProjectMangle-Ansicht wechseln
	@FXML
	private void loadProjectMangleView(ActionEvent event) throws IOException {
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Mangel erfassen");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectMangleView.fxml"));
			Pane myPane = loader.load();
			ProjectMangleController controller = loader.<ProjectMangleController>getController();
			
			//Controller starten
			controller.initMangleAdd();

			Scene scene = new Scene(myPane);
			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
			stage.setScene(scene);
		    
		    //Open new Stage
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// "Abbrechen"-Button: zur ProjectMain-Ansicht wechseln 
	@FXML
	private void loadProjectMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectsMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}

	/**
	 * Speichert ein Projekt.
	 */
	@FXML
	private void saveProject() {

		Project project = new Project();

		//Textfeldproperties auslesen und zuweisen
		project = (Project) getTextfieldProperties(project);

		ProjectProxy projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
		if(projectId != null) {
			project.setId(projectId);
			projectProxy.update(project);
		} else {
			projectProxy.create(project);
		}
		
		//Mängel speichern.
		List<Issue> issueList = mangleTableView.getItems();
		IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		for(Issue issue : issueList) {
			issue.setProject(project);
			issueProxy.create(issue);
		}

		showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
	}
	
	// liest Textfelder aus und speichert Daten des Projektes in der DB
	// Dropdown-Felder füllen
	private Project getTextfieldProperties(Project p1) {
		
		
		p1.setName(txt_projectName.getText());
		p1.setStreet(txt_street.getText());
		p1.setZip(txt_Zip.getText());
		p1.setPlace(txt_place.getText());
		
		//Startdatum generieren
		int dayStart = dateStart.getValue().getDayOfMonth();
	    int monthStart = dateStart.getValue().getMonthValue();
	    int yearStart =  dateStart.getValue().getYear();

	    GregorianCalendar calendarStart = new GregorianCalendar();
	    calendarStart.set(yearStart, monthStart - 1, dayStart);
		p1.setStart(calendarStart);
		
		//Enddatum generieren
		int dayEnd = dateEnd.getValue().getDayOfMonth();
	    int monthEnd = dateEnd.getValue().getMonthValue();
	    int yearEnd =  dateEnd.getValue().getYear();

	    GregorianCalendar calendarEnd = new GregorianCalendar();
	    calendarEnd.set(yearEnd, monthEnd - 1, dayEnd);
		p1.setEnd(calendarEnd);
		
		// Status hinzufügen.
		try {
			StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);			    
		    List<State> stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
			
			for(State state: stateList) {
				if(state.getName().equals(combo_Status.getValue()))
					p1.setState(state);	
				}
		} catch (IOException e1) {
			e1.printStackTrace();
			}
			
		//Typ hinzufügen
		try {
			
			TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);		
			 List<Type> typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
			 for(Type type: typeList) {
					if(type.getName().equals(combo_type.getValue()))
						p1.setType(type);	
					}
		} catch (IOException e1) {
			e1.printStackTrace();
			}
		
		//Kategorie hinzufügen
		try {
			CategoryProxy categoryProxy = ResteasyClientUtil.getTarget().proxy(CategoryProxy.class);		
			List<Category> categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
			for(Category category: categoryList) {
				if(category.getName().equals(combo_Category.getValue()))
					p1.setCategory(category);
				}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		/*
		//Kunde hinzufügen
		try {
			PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);		
			List<Principal> principalList = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
			for(Principal principal: principalList) {
				if(principal.getLastname().equals(combo_principal.getValue())) {
					p1.setPrincipals(principalList);
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		
		// Bauherr hinzufügen
		try {
			SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
			List<Supervisor> supervisorList = mapper.readValue(svProxy.getAll(), new TypeReference<List<Supervisor>>() {});
			for(Supervisor sv : supervisorList) {
				if(sv.getLastname().equals(combo_supervisor.getValue())) {
					p1.setSupervisor(sv);
				}
			}
		} catch(IOException e1) {
			e1.printStackTrace();
		}

		return p1;
	}

		


	
	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
	    rtarget = ResteasyClientUtil.getTarget();
	    mapper = new ObjectMapper();
	}
	
	
	
	@FXML
	private void editIssue(MouseEvent t) throws IOException {

		// Wenn Doppelklick auf Mangel
		if (t.getClickCount() == 2) {

			// Angeklickte Mangel laden
			Issue issueID = (Issue) mangleTableView.getSelectionModel()
					.getSelectedItem();

			// FXMLLoader erstellen
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
	
	private void showMainViewWithMessage(String message) {

		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectsMainView.fxml"));

			//Neuen View laden
			Pane myPane;
			myPane = loader.load();

			//ProjectMainController holen
			ProjectsMainController controller = loader.<ProjectsMainController>getController();

			//Controller starten
			controller.initWithMessage(message);			

			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//TODO Auskommentieren
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
	
	/**
	 * Liest alle Objekt-Typen aus der DB und befüllt das Dropdown damit.
	 */
	public void setDropdownType()  {
		
		try {
			TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);
			ObjectMapper mapper = new ObjectMapper();	  
			List<Type> typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
			
			//Typ dem Dropdown hinzufügen
			for(Type type: typeList) {
				combo_type.getItems().add(type.getName());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
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
//	public void setDropdownPrincipal()  {
//
//		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);		
//		ObjectMapper mapper = new ObjectMapper();	    
//		List<Principal> principalList = null;
//
//		try {
//			principalList = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
		//TODO Fehler-Meldung
//		//Rollen dem Dropdown hinzufügen
//		for(Principal principal: principalList) {
//			combo_principal.getItems().add(principal.getLastname());
//		}
//	}
	
	/**
	 * Lädt alle Mängel aus der Datenbank
	 */
	private void getIssue(Integer a) {
		
	    Issue issue = new Issue();
	    IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);
	    
	    try {
	    	issuesToShow = mapper.readValue(issueProxy.getByProject(a), new TypeReference<List<Issue>>(){});

			
			for(int i = 0; i < issuesToShow.size(); i++) {
					issue = issuesToShow.get(i);
			    	data.add(issue);
			    	issue = null;
			    	
			}
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	    issuesToShow = null;   
	}
	
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