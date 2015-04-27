package ch.claimer.client.gui.controller;

import java.io.IOException;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.Role;
import ch.claimer.shared.models.Supervisor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ProjectsMainController {

	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Project> projectTableView;

	@FXML
	private TableColumn<Project, String> colProject;

	@FXML
	private TableColumn<Project, String> colPrincipal;

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

	// Zur ProjectAdd-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource(
				"../view/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);

	}

	public void initialize() {
		ObservableList<Project> data = FXCollections.observableArrayList();

		// Projekt 1 als Platzhalter
		Project p1 = new Project();
		p1.setId(1);
		p1.setName("Hochhaus1");
		// p1.setStart(new GregorianCalendar(1,1,2001));
		// p1.setEnd(new GregorianCalendar(31,12,2001))
		p1.setStreet("Musterweg 1");
		p1.setZip("6000");
		p1.setPlace("Luzern");
		// p1.setSupervisor();
		// p1.setCategory("Neubau");
		// p1.setType("");
		// p1.setState(state);
		// p1.setContacts();

		// Projekt 2 als Platzhalter
		Project p2 = new Project();
		p2.setId(2);
		p2.setName("Haus1");
// p1.setStart(new GregorianCalendar(1,1,2001));
// p1.setEnd(new GregorianCalendar(31,12,2001))
		p2.setStreet("Musterweg 1");
		p2.setZip("6000");
		p2.setPlace("Luzern");
// p2.setSupervisor();
// p2.setCategory("Neubau");
// p2.setType("");
// p2.setState(state);
// p2.setContacts();

		// Daten zu Observable-List hinzufügen
		data.addAll(p1,p2);

		// Spalten-Values definieren (müssen den Parameter des Personen-Objekts
		// entsprechen)
		colProject
				.setCellValueFactory(new PropertyValueFactory<Project, String>(
						"name"));
		colSupervisor
				.setCellValueFactory(new PropertyValueFactory<Project, String>(
						"supervisor"));
		colStart.setCellValueFactory(new PropertyValueFactory<Project, String>(
				"start"));
		colEnd.setCellValueFactory(new PropertyValueFactory<Project, String>(
				"end"));
		// colProject.setSortTyp(descending);

		// Observable-List, welche die Daten beinhaltet, an die Tabelle
		// übergeben
		projectTableView.setItems(data);

	}

}
