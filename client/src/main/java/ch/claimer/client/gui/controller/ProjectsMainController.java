package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.pmw.tinylog.Logger;

import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
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
 * Kontroller für die Projekt-Übersicht
 * 
 * @author Michael Lötscher, Alexander Hauck
 * @since 1.0
 * @version 2.0
 *
 */
public class ProjectsMainController implements Initializable{

	ObservableList<Project> data = FXCollections.observableArrayList(); 
	ObservableList<Project> filteredData = FXCollections.observableArrayList(); 
		
	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Project> projectTableView;

	@FXML
	private TableColumn<Project, String> colProjectName;
	
	@FXML
	private TableColumn<Project, String> colSupervisor;

	@FXML
	private TableColumn<Project, String> colStart;

	@FXML
	private TableColumn<Project, String> colEnd;
	
	@FXML
	private TableColumn<Project, String> colState;
	
	@FXML
	private TableColumn<Project, String> colZIP;
	
	@FXML
	private TableColumn<Project, String> colPlace;

	@FXML
	private Button bttn_addProject;

	@FXML
	private TextField txt_search;

	@FXML
	private Label lbl_title;
	
	@FXML
	private Label lblMessage;
	

	/**
	 * Initialisiert den TableView mit den nötigen Daten, sobald die Ansicht aufgerufen wird.
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {

	    //Projekte aus DB laden
	    ResteasyWebTarget rtarget = ResteasyClientUtil.getTarget();
	    ObjectMapper mapper = new ObjectMapper();	    	
	    ProjectProxy projectProxy = rtarget.proxy(ProjectProxy.class);
	    
	    //Rolle des eingeloggten Benutzers auslesen und je nach dem die Projekte auslesen.
	    Integer roleValue = AuthenticationUtil.getLogin().getRole().getValue();
	    
	    if(roleValue >= 20) {
	    
		    try {
		    	List<Project> projectsToShow = mapper.readValue(projectProxy.getAll(), new TypeReference<List<Project>>(){});
				
		    	for(Project p: projectsToShow) {
		    		data.add(p);
		    		filteredData.add(p);
		    	}
	
			} catch (IOException e1) {
				Logger.error("Projekte können nicht aus der Datenbank geladen werden.");
			}
	    } else if(roleValue == 10) {
	    	
	    	bttn_addProject.setVisible(false);
	    	
	    	try {
		    	List<Project> projectsToShow = mapper.readValue(projectProxy.getBySupervisor(AuthenticationUtil.getPerson().getId()), new TypeReference<List<Project>>(){});
				
		    	for(Project p: projectsToShow) {
		    		data.add(p);
		    		filteredData.add(p);
		    	}
		
			} catch (IOException e1) {
				Logger.error("Projekte können nicht aus der Datenbank geladen werden.");
			}
	    	
	    } else if(roleValue == 5) {
	    	
	    	bttn_addProject.setVisible(false);
	    	
	    	try {
		    	List<Project> projectsToShow = mapper.readValue(projectProxy.getByContact(AuthenticationUtil.getPerson().getId()), new TypeReference<List<Project>>(){});
				
		    	for(Project p: projectsToShow) {
		    		data.add(p);
		    		filteredData.add(p);
		    }
	
			} catch (IOException e1) {
				Logger.error("Projekte können nicht aus der Datenbank geladen werden.");
			}
		}
		
		// Spalten-Values definieren (müssen den Parameter des Company-Objekts entsprechen)
	    colState.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getState().getName());
				} catch(NullPointerException e) {
					Logger.error("Problem beim Befüllen der Projekte-Tabelle, Status-Spalte.");
					return null;
				}
			}
		  });
		colProjectName.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
		colZIP.setCellValueFactory(new PropertyValueFactory<Project, String>("zip"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Project, String>("place"));
		colSupervisor.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getSupervisor().getFirstname() + " " + data.getValue().getSupervisor().getLastname());
				} catch(NullPointerException e) {
					Logger.error("Problem beim Befüllen der Projekte-Tabelle, Projektnamen-Spalte.");
					return null;
				}
			}
		  });

		colStart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String a = format.format(data.getValue().getStart().getTime());
					return new SimpleStringProperty(a);
				} catch(NullPointerException e) {
					Logger.error("Probleme beim Befüllen der Projekte-Tabelle, Startdatum-Spalte.");
					return null;
				}
			}
		  });
		colEnd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String a = format.format(data.getValue().getEnd().getTime());
					return new SimpleStringProperty(a);
				} catch(NullPointerException e) {
					Logger.error("Probleme beim Befüllen der Projekte-Tabelle, Enddatum-Spalte.");
					return null;
				}
			}
		  });

		projectTableView.setItems(filteredData);

		// Listener für Änderungen im Suchenfeld
		txt_search.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {

				updateFilteredData();
			}

		});
	}
	
	
	/**
	 * Initialisiert die Ansicht und gibt die übergebene Meldung im GUI aus.
	 * @param string - Mitteilung die angezeigt werden soll.
	 */
	public void initWithMessage(String string) {
		lblMessage.setText(string);
		
	}

	
	/**
	 * Öffnet einen neuen View, um ein neues Projekt hinzuzufügen.
	 * @param event - ActionEvent = Klick auf Button
	 * @throws IOException
	 */
	@FXML
	private void loadProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);		
	}
	
	
	/**
	 * Öffnet die ProjectAdd-Ansicht, um das angeklickte Projekt zu bearbeiten.
	 * @param t - MouseEvent = Klick auf das Projekt
	 * @throws IOException
	 */
	@FXML
	private void editProject(MouseEvent t){
		
		//Wenn Doppelklick auf Projekt
		if(t.getClickCount() == 2) {
			
			//Angeklickte Projekt laden
			Project projectID = (Project) projectTableView.getSelectionModel().getSelectedItem();
	
			//Neuen View laden
			Pane myPane;
			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProjectAddView.fxml"));
				myPane = loader.load();
				ProjectAddController controller = loader.<ProjectAddController>getController(); //UserAddController holen
				controller.initData(projectID); //Controller starten	
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane); //Neuen View einfügen
			} catch (IOException | NullPointerException e) {
				Logger.error("View \"ProjectAddView.fxml\" kann nicht geladen werden.");
			}
		}
	}	

	/**
	 * Liste wird mit den gefilterten Daten aktualisieren
	 * 
	 */
	public void updateFilteredData() {
		filteredData.clear();

		for (Project p : data) {
			if (matchesFilter(p)) {
				filteredData.add(p);
			}
		}

		reaplyTableSortOrder();
	}
	

	/**
	 * Überprüft, ob ein Projekt dem Kriterium entspricht. Gehört zur Such - Funktion
	 * @param p - Das Projekt das übergeben wird.
	 * @return true oder false
	 */
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

	/**
	 * Aktualisiert den TableView. Gehört zur "Suchen.."-Funktion.
	 * 
	 */
	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Project, ?>> sortOrder = new ArrayList<>(
		projectTableView.getSortOrder());
		projectTableView.getSortOrder().clear();
		projectTableView.getSortOrder().addAll(sortOrder);
	}

}
