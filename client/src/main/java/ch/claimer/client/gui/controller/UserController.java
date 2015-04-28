package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Role;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Alexander Hauck
 * @since 20.04.2015
 * @version 1.0
 *
 */

public class UserController implements Initializable {
	
	ObservableList<Person> data =
			FXCollections.observableArrayList(
			);
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableView<Person> userTableView;
	
	@FXML
	private TableColumn<Person, String> colLastname;
	
	@FXML
	private TableColumn<Person, String> colName;
	
	@FXML
	private TableColumn<Person, String> colEmail;
	
	@FXML
	private TableColumn<Person, String> colFunction;
	
	@FXML
	private TableColumn<Person, String> colId;
	
	@FXML
	private void editUser(MouseEvent t) throws IOException {
		
		//Wenn Doppelklick auf Person
		if(t.getClickCount() == 2) {
			
			//Angeklickte Person laden
			Person personID = userTableView.getSelectionModel().getSelectedItem();

			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../view/UserAddView.fxml")
				);
			
			//Inhalt laden
			Pane myPane = loader.load();

			//UserAddController holen
			UserAddController controller = loader.<UserAddController>getController();
			
			//Controlsler starten
			controller.initData(personID);			
			
			//Neuen View laden
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
			
		
		}
	}
	
	//Zum User AddView wechseln (UserAddView.xml)
	@FXML
	private void loadUserAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	//Platzhalter-Daten generieren
	Role r1 = new Role();
	r1.setId(1);
	r1.setName("Projektleiter");
	
	List<Role> loginRoles = new ArrayList<Role>();
	loginRoles.add(r1);
	
	Login l1 = new Login();
	l1.setId(1);
	l1.setPassword("12345");
	l1.setUsername("ahauck");
	l1.setRoles(loginRoles);
	
	GCEmployee p1 = new GCEmployee();
	p1.setId(1);
	p1.setEmail("alexander.hauck@stud.hslu.ch");
	p1.setFirstname("Alexander");
	p1.setLastname("Hauck");
	
	GCEmployee p2 = new GCEmployee();
	p2.setEmail("momcilo.bekcic@stud.hslu.ch");
	p2.setFirstname("Momcilo");
	p2.setLastname("Bekcic");
	p2.setLogin(l1);
	
	GCEmployee p3 = new GCEmployee();
	p3.setEmail("stephan.beeler@stud.hslu.ch");
	p3.setFirstname("Stephan");
	p3.setLastname("Beeler");
	
	GCEmployee p4 = new GCEmployee();
	p4.setEmail("fabio.baviera@stud.hslu.ch");
	p4.setFirstname("Fabio");
	p4.setLastname("Baviera");

	GCEmployee p5 = new GCEmployee();
	p5.setEmail("michael.loetscher@stud.hslu.ch");
	p5.setFirstname("Michael");
	p5.setLastname("Lötscher");

	GCEmployee p6 = new GCEmployee();
	p6.setEmail("raoul.ackermann@stud.hslu.ch");
	p6.setFirstname("Raoul");
	p6.setLastname("Ackermann");

	GCEmployee p7 = new GCEmployee();
	p7.setEmail("kevin.stadelmann@stud.hslu.ch");
	p7.setFirstname("Kevin");
	p7.setLastname("Stadelmann");
	
	//Daten zu Observable-List hinzufügen
	data.addAll(p1, p2, p3, p4, p5, p6, p7);
	
	//Spalten-Values definieren (müssen den Parameter des Personen-Objekts entsprechen)
	colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
	colName.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
	colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
	colFunction.setCellValueFactory(new PropertyValueFactory<Person, String>("login"));
	colId.setCellValueFactory(new PropertyValueFactory<Person, String>("id"));
	//colLastname.setSortType(dascending);

	//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
	userTableView.setItems(data);
		
		
	}
	
}


