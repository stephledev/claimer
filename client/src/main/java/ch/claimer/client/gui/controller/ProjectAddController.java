package ch.claimer.client.gui.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CategoryProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.proxy.StateProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.proxy.TypeProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.LogEntry;
import ch.claimer.shared.models.Principal;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Kontroller für die ProjektVerwalten-Übersicht 
 * 
 * @author Michael Lötscher, Alexander Hauck
 * @since 1.0
 * @version 2.0
 *
 */
public class ProjectAddController implements Initializable {

    ResteasyWebTarget rtarget = ResteasyClientUtil.getTarget();
	SupervisorProxy supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);		
    private ObjectMapper mapper = new ObjectMapper();
	private ObservableList<Issue> data = FXCollections.observableArrayList();
	public static ObservableList<Issue> dataTransfer = FXCollections.observableArrayList();
	private ObservableList<Issue> issuesToDeleteList = FXCollections.observableArrayList();
	private ObservableList<LogEntry> logEntryList = FXCollections.observableArrayList();
	private ObservableList<Principal> principalList = FXCollections.observableArrayList();
	public static ObservableList<Principal> principalContainerList = FXCollections.observableArrayList();
	private  Integer projectId = null;
	private Project projectContainer = null;
	private Issue issueToEdit = null;
	private List<Issue> issueList = null;
	private Integer roleValue = AuthenticationUtil.getLogin().getRole().getValue();

	// Views werden ins mainContent-Pane geladen
	@FXML
	private Pane mainContent;

	@FXML
	private Label lbl_title;
	
	@FXML
	private Label lbl_issueExport;

	@FXML
	private Button btnSaveProject;

	@FXML
	private Button bttn_export;
	
	@FXML
	private Button bttn_quitProject;

	@FXML
	private Button btnAddIssue;

	@FXML
	private TextField txtProjectName;
	
	@FXML
	private TextField txtStreet;

	@FXML
	private TextField txtZip;

	@FXML
	private TextField txtPlace;

	@FXML
	private TextField txt_principalPhone;

	@FXML
	private ComboBox<String> dropdownSupervisor;

	@FXML
	private ComboBox<String> dropdownState;
	
	@FXML
	private ComboBox<String> dropdownType;

	@FXML
	private ComboBox<String> combo_principal;
	
	@FXML
	private ComboBox<String> dropdownCategory;

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
	private TableColumn<Issue, String> colContact;

	@FXML
	private TableColumn<Issue, String> colDeadline;

	@FXML
	private TableColumn<Issue, String> colState;
	
	@FXML
	private TableColumn<Issue, String> colDeleteButton;
	
	@FXML
	private Label lblProjectID;
	
	@FXML
	private TableView<LogEntry> logTableView;
	
	@FXML
	private TableColumn<LogEntry, String> colLogDate;
	
	@FXML
	private TableColumn<LogEntry, String> colLogDescription;
	
	@FXML
	private TableView<Principal> principalTableView;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalName;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalFirstname;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalCompany;

	@FXML
	private TableColumn<Principal, String> colPrincipalStreet;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalZip;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalPlace;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalPhone;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalEmail;
	
	@FXML
	private TableColumn<Principal, String> colPrincipalDeleteButton;
	
	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		lbl_title.setText("Neues Projekt erfassen");
		
		if(roleValue >= 20) {
		
			setDropdownSupervisor();
			setDropdownCategory();
			setDropdownState();
			setDropdownType();
		} else {
			
			//Bauleiter-Berechtigungen
			txtProjectName.setEditable(false);
			dropdownCategory.setEditable(false);
			dropdownType.setEditable(false);
			dateStart.setEditable(false);
			dateEnd.setEditable(false);
			txtStreet.setEditable(false);
			txtZip.setEditable(false);
			txtPlace.setEditable(false);
			dropdownState.setEditable(false);
			dropdownSupervisor.setEditable(false);
			btnAddIssue.setVisible(false);
			colDeleteButton.setVisible(false);
			
		
		}
		
		//Listener,um Änderungen zu überprüfen.
		dataTransfer.addListener(new ListChangeListener<Issue>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Issue> c) {
				if(dataTransfer.size() > 0) {
					
					data.remove(issueToEdit);	//den aktualisierten aus der Liste entfernen	
					issueToEdit = null;
					data.addAll(dataTransfer);

					fillIssueTableView();
				}	
			}
		 });
		
		
		//Listener, um zu überprüfen, ob ein neuer Bauherr hinzugefügt wurde.
		principalContainerList.addListener(new ListChangeListener<Principal>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Principal> c) {
			
				if(principalContainerList.size() > 0) {
					System.out.println("änderung");
					principalList.addAll(principalContainerList);
					//principalContainerList.clear();
					fillPrincipalTableView();
				}	
			}
		 });
	}
	
	
	/**
	 * Initialisiert den View mit den Daten des zu bearbeitenden Projekts sowie den dazugehörigen Mängeln.
	 * @param project - Project welches geladen wird
	 */
	public void initData(Project project) {
		
		lbl_title.setText("Projekt \"" + project.getName() +"\" bearbeiten");
		
		projectId = project.getId();
		projectContainer = project;
		lblProjectID.setText(projectId.toString());

		if(project.getName() != null) { 
			txtProjectName.setText(project.getName());	
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
			dropdownSupervisor.setValue(project.getSupervisor().getLastname() + ", " + project.getSupervisor().getFirstname());
		}
		if(project.getStreet() != null) { 
			txtStreet.setText(project.getStreet());
		}
		if(project.getZip() != null) { 
			txtZip.setText(project.getZip());
		}
		if(project.getPlace() != null) { 
			txtPlace.setText(project.getPlace());	
		}
		if(project.getState() != null) { 
			dropdownState.setValue(project.getState().getName());	
		}
		if(project.getCategory() != null) { 
			dropdownCategory.setValue(project.getCategory().getName());	
		}
		
		if(project.getType().getName() != null) {
			dropdownType.setValue(project.getType().getName());
		}

		for(Principal principal : project.getPrincipals()) {
			principalList.add(principal);
		}
		
		//Mängel aus der Datenbank laden.
		if(roleValue == 5) {
			//Mängeldaten für die Ansprechperson laden
			try {
				IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
				issueList = mapper.readValue(issueProxy.getByContact(AuthenticationUtil.getPerson().getId()), new TypeReference<List<Issue>>(){});
				
				for(Issue i : issueList) {
					data.add(i);
				}
		
			} catch (IOException e1) {
				// TODO LOGGING-Eintrag
				e1.printStackTrace();
			}
			
		} else {
			try {
				IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
				issueList = mapper.readValue(issueProxy.getByProject(projectId), new TypeReference<List<Issue>>(){});
				
				for(Issue i : issueList) {
					data.add(i);
				}
		
			} catch (IOException e1) {
				// TODO LOGGING-Eintrag
				e1.printStackTrace();
			}
		}
		fillIssueTableView();
		fillLogTableView();
		fillPrincipalTableView();
	
	}
	
	/**
	 * Füllt die Tabelle mit den Mängel mit Daten
	 */
	private void fillIssueTableView() {
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
		
		colContact.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
					try {
						return new SimpleStringProperty(data.getValue().getContact().getFirstname() + " " + data.getValue().getContact().getLastname());
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
		
		colState.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getState().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
		
		colDeleteButton.setCellFactory(new Callback<TableColumn<Issue, String>, TableCell<Issue, String>>() {
		      @Override
		      public TableCell<Issue, String> call(TableColumn<Issue, String> param) {
		             final TableCell<Issue, String> cell = new TableCell<Issue, String>() {
	                      @Override
	                      public void updateItem(String value, boolean empty) {
	                    	  if(!empty) {
	                            super.updateItem(value, empty);

	                            final VBox vbox = new VBox(0);
	                            Image image = new Image(getClass().getResourceAsStream("../../../../../delete.png"));
	                            Button button = new Button("", new ImageView(image));
	                            button.getStyleClass().add("deleteButton");
	                            final TableCell<Issue, String> c = this;
	                            button.setOnAction(new EventHandler<ActionEvent>() {
	                            	@Override
	                            	public void handle(ActionEvent event) {
	                            		
	                            		try {
                                  			Stage stage = new Stage();
                                  			stage.setTitle("Mangel löschen");
                                  			
                                  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
                                  			Pane myPane = loader.load();

                                  			Scene scene = new Scene(myPane);
                                  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
                                  			stage.setScene(scene);
                                  		    
                                  		    //Open new Stage
                                  			stage.show();
	                            		} catch (IOException e) {
                                  			// TODO Auto-generated catch block
                                  			e.printStackTrace();
                                  		}

	                            		@SuppressWarnings("unchecked")
	                            		TableRow<Issue> tableRow = c.getTableRow();
	                            		Issue issue= (Issue)tableRow.getTableView().getItems().get(tableRow.getIndex());
	                            		if((Integer)issue.getId() != null) {
	                            			issuesToDeleteList.add(issue);
	                            		}
	                            		data.remove(issue);
	                            		fillIssueTableView();
	                            	}
	                            });
	                      vbox.getChildren().add(button);
	                      setGraphic(vbox);
	                    	  }
		               }
		        };
		        return cell;
		    }
		});
	
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		mangleTableView.setItems(data);
	}
	
	/**
	 * Befüllt die Bauherren-Tabelle mit Daten
	 */
	private void fillPrincipalTableView() {
		
		colPrincipalFirstname.setCellValueFactory(new PropertyValueFactory<Principal, String>("firstname"));
		colPrincipalName.setCellValueFactory(new PropertyValueFactory<Principal, String>("lastname"));
		colPrincipalCompany.setCellValueFactory(new PropertyValueFactory<Principal, String>("company"));
		colPrincipalStreet.setCellValueFactory(new PropertyValueFactory<Principal, String>("street"));
		colPrincipalZip.setCellValueFactory(new PropertyValueFactory<Principal, String>("zip"));
		colPrincipalPlace.setCellValueFactory(new PropertyValueFactory<Principal, String>("place"));
		colPrincipalPhone.setCellValueFactory(new PropertyValueFactory<Principal, String>("phone"));
		colPrincipalEmail.setCellValueFactory(new PropertyValueFactory<Principal, String>("email"));
		colPrincipalDeleteButton.setCellFactory(new Callback<TableColumn<Principal, String>, TableCell<Principal, String>>() {
		      @Override
		      public TableCell<Principal, String> call(TableColumn<Principal, String> param) {
		             final TableCell<Principal, String> cell = new TableCell<Principal, String>() {
	                      @Override
	                      public void updateItem(String value, boolean empty) {
	                    	  if(!empty) {
	                            super.updateItem(value, empty);

	                            final VBox vbox = new VBox(0);
	                            Image image = new Image(getClass().getResourceAsStream("../../../../../delete.png"));
	                            Button button = new Button("", new ImageView(image));
	                            button.getStyleClass().add("deleteButton");
	                            final TableCell<Principal, String> c = this;
	                            button.setOnAction(new EventHandler<ActionEvent>() {
	                                  @Override
	                                  public void handle(ActionEvent event) {
	                                          @SuppressWarnings("unchecked")
	                                          TableRow<Principal> tableRow = c.getTableRow();
	                                          Principal principal= (Principal)tableRow.getTableView().getItems().get(tableRow.getIndex());
	                                          
	                                          // TODO Confirmation Window
	                                          
	                                          try {
	                                  			Stage stage = new Stage();
	                                  			stage.setTitle("Kunde löschen");
	                                  			
	                                  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
	                                  			Pane myPane = loader.load();

	                                  			Scene scene = new Scene(myPane);
	                                  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
	                                  			stage.setScene(scene);
	                                  		    
	                                  		    //Open new Stage
	                                  			stage.show();
	                                  		} catch (IOException e) {
	                                  			// TODO Auto-generated catch block
	                                  			e.printStackTrace();
	                                  		}
	                                        	  
	                                          principalList.remove(principal);
	                                          fillPrincipalTableView();
	                                  }
	                            });
	                      vbox.getChildren().add(button);
	                      setGraphic(vbox);
	                    	  }
		               }
		        };
		        return cell;
		    }
		});
		
		principalTableView.setItems(principalList);
		
	}
	
	/**
	 * Füllt die Tabelle mit den Protokollen mit Daten
	 */
	private void fillLogTableView() {
		
		for(LogEntry logEntry : projectContainer.getLogEntries()) {
			logEntryList.add(logEntry);
		}
		
		colLogDate.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<LogEntry, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<LogEntry, String> data) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String a = format.format(data.getValue().getDate().getTime());
					return new SimpleStringProperty(a);
				} catch(NullPointerException e) {
					return null;
				}
			}
		});
		
		colLogDescription.setCellValueFactory(new PropertyValueFactory<LogEntry, String>("description"));
		
		logTableView.setItems(logEntryList);
		
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
		if(roleValue >= 20) {
			Project project = new Project();
	
			//Textfeldproperties auslesen und zuweisen
			project = (Project) getTextfieldProperties(project);
			
			if(project != null) {
				logEntryHandler(project);
				project.setLogEntries(logEntryList);
				project.setPrincipals(principalList);
				
				//Contacts dem Projekt hinzufügen
				for(Issue issue: issueList) {
					if(!project.getContacts().contains(issue.getContact()))
						{
						project.getContacts().add(issue.getContact());
					}
				}
				ProjectProxy projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
				if(projectId != null) {
					project.setId(projectId);
					projectProxy.update(project);
				} else {
					projectProxy.create(project);
				}
				
				saveIssues();
			
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} else {
			saveIssues();
			logEntryHandler(projectContainer);
			showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
		}
	}
		
	private void saveIssues() {
		//Mängel speichern.
		List<Issue> issueList = mangleTableView.getItems();
		IssueProxy issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		for(Issue issue : issueList) {
			issue.setProject(projectContainer);
			if((Integer)issue.getId() != null) {
				issueProxy.update(issue);
			} else {
				issueProxy.create(issue);
			}
		}
		
		//Mängel aus Temporärer "Löschen"-Liste vom Projekt entfernen
		for(Issue issueToDelete : issuesToDeleteList) {
			issueToDelete.setProject(null);
			issueProxy.update(issueToDelete);
		}
	}
	
	/**
	 * Überprüft Änderungen am Projekt und protokolliert diese.
	 * @param project - Das zu protokollierende Projekt.
	 */
	private void logEntryHandler(Project project) {
		if(projectContainer != null) {
			if(!project.getState().getName().equals(projectContainer.getState().getName())) {
				LogEntry logEntry = new LogEntry();
				logEntry.setDate(new GregorianCalendar());
				logEntry.setDescription("Der Status wurde von \"" + projectContainer.getState().getName() + "\" auf \"" + project.getState().getName() + "\" geändert.");
				logEntryList.add(logEntry);
			}
			
			if((!project.getSupervisor().getFirstname().equals(projectContainer.getSupervisor().getFirstname())) && 
					(!project.getSupervisor().getLastname().equals(projectContainer.getSupervisor().getLastname()))) {
				LogEntry logEntry = new LogEntry();
				logEntry.setDate(new GregorianCalendar());
				logEntry.setDescription("Neuer Bauleiter: " + project.getSupervisor().getFirstname() + " " + project.getSupervisor().getLastname() );
				logEntryList.add(logEntry);
			}
			
			if(!project.getName().equals(projectContainer.getName())) {
				LogEntry logEntry = new LogEntry();
				logEntry.setDate(new GregorianCalendar());
				logEntry.setDescription("Der Name des Projekts wurde auf \"" + project.getName() + "\" geändert.");
				logEntryList.add(logEntry);
			}
			
		} else {
			LogEntry logEntry = new LogEntry();
			logEntry.setDate(new GregorianCalendar());
			logEntry.setDescription("Erfassung des Projekts \"" + project.getName() + "\" mit dem Bauleiter " + project.getSupervisor().getFirstname() + " " + project.getSupervisor().getLastname());
			logEntryList.add(logEntry);
		}
		
		
	}
	
	/**
	 * Die Methode überprüft, ob beim übergeben String die Mindest- und Maximumlänge stimmt.
	 * 
	 * @param text - Text der übergeben wird.
	 * @param minLength - Minimumlänge die überprüft werden soll.
	 * @param maxLength - Maximumlänge die überprüft werden soll.
	 * @return true oder false
	 */
	private Boolean checkLength(String text, int minLength, int maxLength) {

		if(text.length() < minLength || text.length() > maxLength) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * Liest alle Textfelder aus und validiert diese.
	 * @return p1 - Gibt das Projekt mit den ausgelesenen Textfeldern zurück.
	 * 
	 */

	private Project getTextfieldProperties(Project p1) {
		
		Boolean validationError = false;
		
		String projectName = txtProjectName.getText();
		if(checkLength(projectName, 1, 255)) {
			validationError = true;
			txtProjectName.getStyleClass().add("txtError");
		} else {
			p1.setName(projectName);
		}
		
		String street = txtStreet.getText();
		if(checkLength(street, 1, 255)) {
			validationError = true;
			txtStreet.getStyleClass().add("txtError");
		} else {
			p1.setStreet(street);
		}
		
		String zip = txtZip.getText();
		if(checkLength(zip, 4, 5)) {
			validationError = true;
			txtZip.getStyleClass().add("txtError");
		} else {
			p1.setZip(zip);
		}
		
		String place = txtPlace.getText();
		if(checkLength(place, 1, 255)) {
			validationError = true;
			txtPlace.getStyleClass().add("txtError");
		} else {
			p1.setPlace(place);
		}
		
		
		//Startdatum generieren
		if(dateStart.getValue() != null) {
			int dayStart = dateStart.getValue().getDayOfMonth();
		    int monthStart = dateStart.getValue().getMonthValue();
		    int yearStart =  dateStart.getValue().getYear();
	
		    GregorianCalendar calendarStart = new GregorianCalendar();
		    calendarStart.set(yearStart, monthStart - 1, dayStart);
			p1.setStart(calendarStart);
		} else {
			validationError = true;
			dateStart.getStyleClass().add("txtError");
		}
		
		//Enddatum generieren
		if(dateEnd.getValue() != null) {
			int dayEnd = dateEnd.getValue().getDayOfMonth();
		    int monthEnd = dateEnd.getValue().getMonthValue();
		    int yearEnd =  dateEnd.getValue().getYear();
	
		    GregorianCalendar calendarEnd = new GregorianCalendar();
		    calendarEnd.set(yearEnd, monthEnd - 1, dayEnd);
			p1.setEnd(calendarEnd);
		} else {
			validationError = true;
			dateEnd.getStyleClass().add("txtError");
		}
		
		// Status hinzufügen.
		if(dropdownState.getValue() != null) {
			try {
				StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);			    
			    List<State> stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
				
				for(State state: stateList) {
					if(state.getName().equals(dropdownState.getValue()))
						p1.setState(state);	
					}
			} catch (IOException e1) {
				e1.printStackTrace();
				}
		} else {
			validationError = true;
			dropdownState.getStyleClass().add("txtError");
			
		}
			
		//Typ hinzufügen
		if(dropdownType.getValue() != null) {
			try {
				
				TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);		
				 List<Type> typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
				 for(Type type: typeList) {
						if(type.getName().equals(dropdownType.getValue()))
							p1.setType(type);	
						}
			} catch (IOException e1) {
				e1.printStackTrace();
				}
		} else {
			validationError = true;
			dropdownType.getStyleClass().add("txtError");
		}
		
		//Kategorie hinzufügen
		if(dropdownCategory.getValue() != null) {
			try {
				CategoryProxy categoryProxy = ResteasyClientUtil.getTarget().proxy(CategoryProxy.class);		
				List<Category> categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
				for(Category category: categoryList) {
					if(category.getName().equals(dropdownCategory.getValue()))
						p1.setCategory(category);
					}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			validationError = true;
			dropdownCategory.getStyleClass().add("txtError");
		}
		
		// Bauleiter hinzufügen
		if(dropdownSupervisor.getValue() != null) {
			
			String supervisorName = dropdownSupervisor.getValue();
			String[] parts = supervisorName.split(",");
			String lastname = parts[0];
			String firstname = parts[1].substring(1);
			
			try {
				SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
				List<Supervisor> supervisorList = mapper.readValue(svProxy.getAll(), new TypeReference<List<Supervisor>>() {});
				for(Supervisor sv : supervisorList) {
					if(sv.getLastname().equals(lastname) && sv.getFirstname().equals(firstname)) {
						p1.setSupervisor(sv);
					}
				}
			} catch(IOException e1) {
				e1.printStackTrace();
			}
		} else {
			validationError = true;
			dropdownSupervisor.getStyleClass().add("txtError");
		}
		
		//Rückgabe des Projekts, sofern kein Validations-Fehler vorhanden.
		if(validationError == false) {
			return p1;
		} else {
			return null;
		}
		
	}
	
	/**
	 * Öffent ein neues Fenster, um einen neuen Bauherr hinzuzufügen.
	 */
	@FXML
	private void addPrincipalToTableView() {
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Bauherr zu Projekt hinzufügen");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PrincipalMainView.fxml"));
			Pane myPane = loader.load();
			
			PrincipalController controller = loader.<PrincipalController>getController();
			
			//Controller starten
			controller.loadPrincipalForProject(principalTableView.getItems());

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
	
	/**
	 * Öffnet ein neues Fenster, um einen Mangel zu erfassen.
	 * @param event - Klick auf "Mangel erfassen"-Button
	 * @throws IOException
	 */
	@FXML
	private void loadIssueView(ActionEvent event) throws IOException {
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Mangel erfassen");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/IssueView.fxml"));
			Pane myPane = loader.load();

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
	
	/**
	 * Doppelklick in der Tabelle auf einen Mangel öffnet die Mangel-Ansicht
	 * 
	 * @param t - MouseEvent = Klick auf den Mangel
	 * @throws IOException
	 */
	@FXML
	private void editIssue(MouseEvent t) throws IOException {

		// Wenn Doppelklick auf Mangel
		if (t.getClickCount() == 2) {

			try {
				
				issueToEdit = (Issue)mangleTableView.getSelectionModel().getSelectedItem();
				
				Stage stage = new Stage();
				stage.setTitle("Mangel bearbeiten");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/IssueView.fxml"));
				Pane myPane = loader.load();
				IssueController controller = loader.<IssueController>getController();
				
				//Controller starten
				controller.initData(issueToEdit);

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
	}
	
	/**
	 * Methode um Mitteilung bei der Projekt-Übersicht anzuzeigen
	 * 
	 * @param message - Mitteilung welche angezeigt werden soll.
	 */
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

	/**
	 * Werte für das "Bauleiter"-Dropdown setzen
	 */
	public void setDropdownSupervisor()  {
	
		try {
			SupervisorProxy supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
			List<Supervisor> supervisorList = mapper.readValue(supervisorProxy.getAll(), new TypeReference<List<Supervisor>>(){});
			for(Supervisor supervisor: supervisorList) {
				dropdownSupervisor.getItems().add(supervisor.getLastname() + ", " + supervisor.getFirstname());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	/**
	 * Werte für das "Status"-Dropdown setzen
	 */
	public void setDropdownState()  {
		
		StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);		
	    List<State> stateList = null;
	    
		try {
			stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Status dem Dropdown hinzufügen
		for(State state: stateList) {
			dropdownState.getItems().add(state.getName());
		}
	}
	
	/**
	 * Werte für das "Typ"-Dropdown setzen
	 */
	public void setDropdownType()  {
		
		try {
			TypeProxy typeProxy = ResteasyClientUtil.getTarget().proxy(TypeProxy.class);
			List<Type> typeList = mapper.readValue(typeProxy.getAll(), new TypeReference<List<Type>>(){});
			
			//Typ dem Dropdown hinzufügen
			for(Type type: typeList) {
				dropdownType.getItems().add(type.getName());
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
	}

	/**
	 * Werte für das "Kategorie"-Dropdown setzen
	 */
	public void setDropdownCategory()  {

		CategoryProxy categoryProxy = ResteasyClientUtil.getTarget().proxy(CategoryProxy.class);		
		List<Category> categoryList = null;

		try {
			categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Kategorie dem Dropdown hinzufügen
		for(Category category: categoryList) {
			dropdownCategory.getItems().add(category.getName());
		}
	}

	/**
	 * Alle Mängel eines Projektes werden auf dem Laufwerk C in einer .scv Datei gepeichert
	 * 
	 * @throws Exception
	 */
	@FXML
	public void export() throws Exception {
		Stage stage = new Stage();
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Mängelliste speichern");
        ExtensionFilter extFilter = new ExtensionFilter("CSV-Datei (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);
        
		Writer writer = new BufferedWriter(new FileWriter(file));

		List<Issue> issuesToExport = mangleTableView.getItems();
		
		String titelZeile = "Projektname:" + ";"  + "Mangel Id:" + ";" + "Mangelbeschrieb:" + ";" + "Erfasst am:" + ";" + "Zu erledigen bis:" + ";" + "Status:" + "\n";
		try {
            writer.write(titelZeile);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
		
		for(Issue issue : issuesToExport) {
			 String issueDescription = issue.getProject().getName() + ";"  + issue.getId() + ";" + issue.getDescription()+ ";" + issue.getCreated().getTime().toString()+ ";" + issue.getSolved().getTime().toString() + ";" + issue.getState().getName()+ "\n";
			if (file != null) {
		        try {
		            writer.write(issueDescription);
		            writer.flush();
		        } catch (IOException ex) {
		            System.out.println(ex.getMessage());
		        }
		    }
		}
		writer.close();
		
		lbl_issueExport.setText("Die Mängelliste wurde gespeichert");
	} 

}