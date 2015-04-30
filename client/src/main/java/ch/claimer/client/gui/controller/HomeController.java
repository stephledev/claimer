package ch.claimer.client.gui.controller;

import java.util.GregorianCalendar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */

public class HomeController {
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableView<Person> homeTableView;
	
	@FXML
	private TableColumn<Person, String> colProject;
	
	@FXML
	private TableColumn<Person, String> colMangel;
	
	@FXML
	private TableColumn<Person, String> colStatus;
	
	@FXML
	private TableColumn<Person, String> colDeadline;
	
	@FXML
	private TextField txt_search;
	
	@FXML
	private Label lbl_title;
	
	
	public void initialize() {
		ObservableList<Project> data = FXCollections.observableArrayList();

		// Projekt 1 als Platzhalter
		Project p1 = new Project();
		p1.setId(1);
		p1.setName("Hochhaus1");
		p1.setStart(new GregorianCalendar(1,1,2001));
		p1.setEnd(new GregorianCalendar(31,12,2001));
		p1.setStreet("Musterweg 1");
		p1.setZip("6000");
		p1.setPlace("Luzern");
// 		p1.setSupervisor();
// 		p1.setCategory("Neubau");
// 		p1.setType("");
		p1.setState(null);
// 		p1.setContacts();

		// Projekt 2 als Platzhalter
		Project p2 = new Project();
		p2.setId(2);
		p2.setName("Haus1");
		p1.setStart(new GregorianCalendar(1,1,2001));
		p1.setEnd(new GregorianCalendar(31,12,2001));	
		p2.setStreet("Musterweg 1");
		p2.setZip("6000");
		p2.setPlace("Luzern");
//		 p2.setSupervisor();
// 		p2.setCategory("Neubau");
// 		p2.setType("");
		p2.setState(null);
// 		p2.setContacts();

		// Daten zu Observable-List hinzufügen
		data.addAll(p1,p2);

		// Spalten-Values definieren (müssen den Parameter des Personen-Objekts
		// entsprechen)
//		colProject.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
////		colMangel.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
//		colStatus.setCellValueFactory(new PropertyValueFactory<Project, String>("state"));
//		colDeadline.setCellValueFactory(new PropertyValueFactory<Project, String>("end"));
//		// colProject.setSortTyp(descending);
//
//		// Observable-List, welche die Daten beinhaltet, an die Tabelle
//		// übergeben
//		homeTableView.setItems(data);

	}

}


