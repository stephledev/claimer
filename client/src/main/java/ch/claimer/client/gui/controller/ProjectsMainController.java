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

import ch.claimer.client.proxy.ProjectProxy;
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
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
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
	
	@FXML
	private Label lblMessage;
	

	/**
	 * Initialisiert den TableView automatisch mit den nötigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {

	    //Projekte aus DB laden
	    ResteasyWebTarget rtarget = ResteasyClientUtil.getTarget();
	    ObjectMapper mapper = new ObjectMapper();	    	
	    ProjectProxy projectProxy = rtarget.proxy(ProjectProxy.class);
	    
	    try {
	    	List<Project> projectsToShow = mapper.readValue(projectProxy.getAll(), new TypeReference<List<Project>>(){});
			
	    	for(Project p: projectsToShow) {
	    		data.add(p);
	    		filteredData.add(p);
	    	}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		// Spalten-Values definieren (müssen den Parameter des Company-Objekts entsprechen)
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

		colStart.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Project, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Project, String> data) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
					String a = format.format(data.getValue().getStart().getTime());
					return new SimpleStringProperty(a);
				} catch(NullPointerException e) {
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
	 * Initialisiert den View und gibt die übergebene Meldung im GUI aus.
	 * @param string
	 */
	public void initWithMessage(String string) {
		lblMessage.setText(string);
		
	}

	
	/**
	 * Öffnet einen neuen View, um ein neues Projekt hinzuzufügen.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void loadProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);		
	}
	
	
	/**
	 * Öffnet einen neuen View, in das angeklickte Projekt zu bearbeiten.
	 * @param t
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
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/ProjectAddView.fxml"));
				myPane = loader.load();
				ProjectAddController controller = loader.<ProjectAddController>getController(); //UserAddController holen
				controller.initData(projectID); //Controller starten	
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane); //Neuen View einfügen
			} catch (IOException e) {
				// TODO LOG Entry
				e.printStackTrace();
			}
		}
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

}
