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
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Project;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */

public class HomeController implements Initializable {

	Client client;
	WebTarget target;
	ResteasyWebTarget rtarget;
	ObjectMapper mapper;

	//Beinhaltet alle Projekte bei der Initialisation
	ObservableList<Issue> data = FXCollections.observableArrayList(); 
	//Contains filtered Data (search-function...)
	ObservableList<Issue> dataCopy = FXCollections.observableArrayList(); 
	List<Issue> issuesToShow = null;

	private Integer IssueId;
	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Issue> homeTableView;

	@FXML
	private TableColumn<Issue, String> colProject;

	@FXML
	private TableColumn<Issue, String> colMangel;

	@FXML
	private TableColumn<Issue, String> colStatus;

	@FXML
	private TableColumn<Issue, String> colDeadline;

	@FXML
	private TextField txt_search;

	@FXML
	private Label lbl_title;

	/**
	 * Öffnet die Detailansicht für einen User, um diesen zu bearbeiten.
	 * @param t
	 * @throws IOException
	 */
	@FXML
	private void editIssue(MouseEvent t) throws IOException {

		//Wenn Doppelklick auf Mangel
		if(t.getClickCount() == 2) {

			//Angeklickte Mangel laden
			Issue issueId = (Issue) homeTableView.getSelectionModel().getSelectedItem();

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

	/**
	 * Webservice-Verbindung herstellen. Wird automatisch von der initiate-Funktion aufgerufen.
	 */
	private void initiateWebserviceConnection() {
		rtarget = ResteasyClientUtil.getTarget();
		mapper = new ObjectMapper();
	}

	/**
	 * Lädt alle Mängel aus der Datenbank
	 */
	private void getIssue() {

		Issue issue = new Issue();
		IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);

		try {
			issuesToShow = mapper.readValue(issueProxy.getAll(), new TypeReference<List<Issue>>(){});

			for(int i = 0; i < issuesToShow.size(); i++) {
				issue = issuesToShow.get(i);
				data.add(issue);
				dataCopy.add(issue);
				issue = null;

			}
		} catch (IOException e1) {

			e1.printStackTrace();
		}

		issuesToShow = null;

	}

	/**
	 * Initialisiert den TableView automatisch mit den nötigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {
		initiateWebserviceConnection();
		getIssue();


		// Spalten-Values definieren (müssen den Parameter des Personen-Objekts entsprechen)
		colProject.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getProject().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
		colMangel.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
		colStatus.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Issue, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Issue, String> data) {
				try {
					return new SimpleStringProperty(data.getValue().getState().getName());
				} catch(NullPointerException e) {
					return null;
				}
			}
		  });
		colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("end"));
		

		// Observable-List, welche die Daten beinhaltet, an die Tabelle
		// übergeben
		homeTableView.setItems(data);


		// Observable-List, welche die Daten beinhaltet, an die Tabelle
		// übergeben
		homeTableView.setItems(data);

		// Listener für Änderungen im Suchenfeld
		txt_search.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {

				updateDataCopy();
			}

		});

	}

	// Observable-List mit den gefilterten Daten aktualisieren
	public void updateDataCopy() {
		data.clear();

		for (Issue i : dataCopy) {
			if (matchesFilter(i)) {
				data.add(i);
			}
		}

		reaplyTableSortOrder();
	}


	// Überprüfen, ob Suchbegriff mit Daten übereinstimmt
	private boolean matchesFilter(Issue i) {
		String filterString = txt_search.getText();

		if(filterString == null || filterString.isEmpty()) {
			//No filter --> add all
			return true;
		}

		String lowerCaseFilterString = filterString.toLowerCase();

		if(i.getProject().getName().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		if(i.getDescription().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
			return true;
		}

		return false;
	}


	private void reaplyTableSortOrder() {
		ArrayList<TableColumn<Issue, ?>> sortOrder = new ArrayList<>(
		homeTableView.getSortOrder());
		homeTableView.getSortOrder().clear();
		homeTableView.getSortOrder().addAll(sortOrder);
	}



}




