package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.pmw.tinylog.Logger;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.RoleProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Role;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Supervisor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller für das Hinzufügen und Bearbeiten von Benutzern.
 * @author Alexander
 * @since 1.0
 * @version 2.0
 *
 */
public class UserAddController implements Initializable{
		
	private Integer personId = null;
	private Integer loginID = null;
	private String personType = null;
	private Boolean toDelete = false;
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private TextField txtFirstname;
	
	@FXML
	private TextField txtLastname;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TextField txtUsername;
	
	@FXML
	private PasswordField pfPassword;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private ComboBox<String> dropdownFunction;
	
	@FXML
	private Button btnSave;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Label lblFunction;

	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {		

		lblFunction.setVisible(false);
		
		//Dropdown Values initialisieren
		dropdownFunction.getItems().addAll( 
				"Sachbearbeiter GU Admin",
				"Sachbearbeiter GU",
				"Bauleiter"
		);
		
		//Delete-Button deaktivieren
		btnDelete.setVisible(false);
	}	

	/**
	 * Detailansicht mit allen Daten der angeklickten Person füllen
	 * @param personToEdit - Person welche bearbeitet werden soll.
	 */
	public void initData(Person personToEdit) {
		lblTitel.setText("Benutzer bearbeiten");
		btnDelete.setVisible(true);
		lblFunction.setVisible(true);
		dropdownFunction.setVisible(false);
		
		personId = personToEdit.getId();
		personType = personToEdit.getClass().getSimpleName(); //Typ des Objekts auslesen

		if(personToEdit.getFirstname() != null) { 
			txtFirstname.setText(personToEdit.getFirstname());	
		}
		
		if(personToEdit.getLastname() != null) {
			txtLastname.setText(personToEdit.getLastname());
		}
		
		if(personToEdit.getEmail() != null) {
		txtEmail.setText(personToEdit.getEmail());
		}
		
		if(personToEdit.getPhone() != null) {
			txtPhone.setText(personToEdit.getPhone());
		}
		
		if(personToEdit.getLogin() != null) {
			loginID = personToEdit.getLogin().getId();
			if(personToEdit.getLogin().getUsername() != null) {
				txtUsername.setText(personToEdit.getLogin().getUsername());
			}
			
			if(personToEdit.getLogin().getPassword() != null) {
				pfPassword.setText(personToEdit.getLogin().getUsername());
			}
		}
		
		if(personToEdit.getLogin().getRole().getName() != null) {			
			String personType = personToEdit.getLogin().getRole().getName();
			
			switch(personType) {
				case("superadmin"): personType = "Sachbearbeiter GU Admin";
				break;
				case("admin"): personType = "Sachbearbeiter GU";
				break;
				case("editor-intern"): personType = "Bauleiter";
				break;
				case("power"): personType= "Sachbearbeiter"; //Sachbearbeiter SU
				break;
				case("edit-extern"): personType = "Ansprechperson";
				break;
			}
			dropdownFunction.setValue(personType);
			lblFunction.setText(personType);
		}
	}
	
	/**
	 * Lädt den Benutzer Hauptview.
	 */
	@FXML
	private void loadUserMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/UserMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"UserMainView.fxml\" kann nicht geladen werden.");
		}
		
	}
	

	/**
	 * Lädt den Benutzer Hauptview und gibt eine Meldung aus.
	 * @param String - Mitteilung die angezeigt werden soll.
	 */
	private void showMainViewWithMessage(String message) {

		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//PrincipalController holen
			UserController controller = loader.<UserController>getController();
			
			//Controller starten
			controller.initWithMessage(message);			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"UserMainView.fxml\" kann nicht geladen werden.");
		}
	}
	
	/**
	 * Löscht einen Benutzer.
	 */
	@FXML
	private void deleteUser() {
		toDelete = true;
		//TODO Confirmation Window
		try {
  			Stage stage = new Stage();
  			stage.setTitle("Benutzer löschen");
  			
  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
  			Pane myPane = loader.load();

  			Scene scene = new Scene(myPane);
  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
  			stage.setScene(scene);
  		    
  		    //Open new Stage
  			stage.show();
		} catch (IOException e) {
			Logger.error("View \"DeleteConfirmation.fxml\" kannn nicht geladen werden.");
  		}
		personHandler();
	}
	
	/**
	 * Speichert einen User.
	 * @param event - ActionEvent = Klick auf Button
	 * @throws IOException
	 */
	@FXML
	private void saveUser(ActionEvent event) throws IOException {
		
		personHandler();
	}
	
	/**
	 * Entscheidet anhand des Dropdowns, um was für eine Person es sich handelt und speichert diese.
	 */
	private void personHandler() {
		// Typ des Personenobjekts bestimmen und passende funktion aufrufen
		personType = dropdownFunction.getValue();
	
		if(personType.equals("Sachbearbeiter GU") || personType.equals("Sachbearbeiter GU Admin")) {
			GCEmployee gce = new GCEmployee();
			gce = (GCEmployee)validateInputs(gce);
			if(gce != null) {
				saveGCEmployee(gce);
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} else if(personType.equals("Bauleiter")) {
			Supervisor sv = new Supervisor();
			sv = (Supervisor) validateInputs(sv);
			if(sv != null) {
				saveSupervisor(sv);
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} 
	}

	/**
	 * Liest alle Inputs aus, validiert diese und gibt das Objekt an die entsprechende Speicher-Funktion weiter.
	 */
	private Person validateInputs(Person person) {
		// Alle Felder auslesen, Validieren und dem Personen-Objekt zuweisen
				
		Boolean validationError = false;
		
		String firstname = txtFirstname.getText();
		if(checkLength(firstname, 1, 255)) {
			validationError = true;
			txtFirstname.getStyleClass().add("txtError");
		} else {
			person.setFirstname(firstname);
		}
		
		String lastname = txtLastname.getText();
		if(checkLength(lastname, 1, 255)) {
			validationError = true;
			txtLastname.getStyleClass().add("txtError");
		} else {
			person.setLastname(lastname);
		}
		
		String email = txtEmail.getText();
		if(checkLength(email, 0, 255)) {
			validationError = true;
			txtEmail.getStyleClass().add("txtError");
		} else {
			person.setEmail(email);
		}
		
		String phone = txtPhone.getText();
		if(checkLength(phone, 0, 255)) {
			validationError = true;
			txtPhone.getStyleClass().add("txtError");
		} else {
			person.setPhone(phone);
		}
		

		if(personId != null) {
			person.setId(personId);
		}
		
		// Neues Login erstellen und Feldinhalte zuweisen
		Login login = new Login();
		
		String password = pfPassword.getText();
		if(checkLength(password, 1, 255)) {
			validationError = true;
			pfPassword.getStyleClass().add("txtError");
		} else {
			login.setPassword(password);
		}
		
		String userName = txtUsername.getText();
		if(checkLength(userName, 1, 255)) {
			validationError = true;
			txtUsername.getStyleClass().add("txtError");
		} else {
			login.setUsername(userName);
		}
		
		if(loginID != null) {
			login.setId(loginID);
		}
	
		 //Login der Person zuweisen
		person.setLogin(login);
		
		
		if(dropdownFunction.getValue() == null) {
			validationError = true;
		} else {
			//Rollen aus DB holen und dem Login zuweisen
			RoleProxy roleProxy = ResteasyClientUtil.getTarget().proxy(RoleProxy.class);		
		    ObjectMapper mapper = new ObjectMapper();	    
		    List<Role> roleList = null;
	
			 try {
					roleList = mapper.readValue(roleProxy.getAll(), new TypeReference<List<Role>>(){});
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			 
			 //Rolle bestimmen und zuweisen
			 String dropdownValue = dropdownFunction.getValue();
			 switch(dropdownValue) {
			 	case "Sachbearbeiter": dropdownValue = "power";
			 	break;
			 	case "Ansprechperson": dropdownValue = "editor-extern";
			 	break;
			 	case "Sachbearbeiter GU Admin": dropdownValue = "superadmin";
			 	break;
			 	case "Sachbearbeiter GU": dropdownValue = "admin";
			 	break;
			 	case "Bauleiter": dropdownValue = "editor-intern";
			 	break;
			 	
			 }
			 
			 for(Role role : roleList) {
				 if(role.getName().equals(dropdownValue)) {
					 login.setRole(role);
				 }
			}
		}

		if(validationError == false) {
			return person;
		} else {
			return null;
		}
			
	}

	/**
	 * Speichert oder aktualisiert einen Bauleiter.
	 * @param person - Bauleiter der gespeichert wird.
	 */
	private void saveSupervisor(Person person) {
		Supervisor supervisor = new Supervisor();
		supervisor = (Supervisor) person;
		
		if(toDelete) {
			supervisor.setActive(false);
		} else {
			supervisor.setActive(true);
		}
		
		SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		if(personId != null) {
			svProxy.update(supervisor);
		} else {
			svProxy.create(supervisor);
		}
	}

	/**
	 * Speichert oder aktualisiert einen Generalunternehmen-Mitarbeiter.
	 * @param person - Generalunternehmen-Mitarbeiter der gespeichert werden soll.
	 */
	private void saveGCEmployee(Person person) {

		GCEmployee gcEmployee = new GCEmployee();
		gcEmployee = (GCEmployee)person;

		if(toDelete) {
			gcEmployee.setActive(false);
		} else {
			gcEmployee.setActive(true);
		}

		GCEmployeeProxy gceProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		if(personId != null) {
			gceProxy.update(gcEmployee);
		} else {
			gceProxy.create(gcEmployee);
		}
	}


	/**
	 * Initialisiert den View, um einen Subunternehmen-Mitarbeiter zu erfassen.
	 */
	public void initScStaffAdd() {
		
		mainContent.setPadding(new Insets(20));
		dropdownFunction.getItems().clear();
		dropdownFunction.getItems().add("Sachbearbeiter");
		dropdownFunction.getItems().add("Ansprechperson");
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				personType = dropdownFunction.getValue();
				
				if(personType.equals("Sachbearbeiter")) {
					SCEmployee sce = new SCEmployee();
					sce = (SCEmployee) validateInputs(sce);
					if(sce != null) {
						SubcontractorAddController.data2.add(sce);
						SubcontractorAddController.data2.clear();
						Stage stage = (Stage) btnSave.getScene().getWindow();
					    stage.close();
					}
				} else if(personType.equals("Ansprechperson")) {
					Contact contact = new Contact();
					contact = (Contact) validateInputs(contact);
					if(contact != null) {
						SubcontractorAddController.data2.add(contact);
						SubcontractorAddController.data2.clear();
						Stage stage = (Stage) btnSave.getScene().getWindow();
					    stage.close();
					}
				}
				
			}
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btnSave.getScene().getWindow();
			    stage.close();
			}
		});		
	}
	
	/**
	 * Initialisiert den View, um einen Subunternehmen-Mitarbeiter zu bearbeiten.
	 * @param personToEdit - Subunternehmen-Mitarbeiter der bearbeitet werden soll.
	 */
	public void initscStaffEdit(Person personToEdit) {
		
		mainContent.setPadding(new Insets(20));
		initData(personToEdit);
		dropdownFunction.setVisible(false);
		lblFunction.setVisible(true);
		
		String roleName = personToEdit.getLogin().getRole().getName();
		switch(roleName) {
			case("power"): {
				lblFunction.setText("Sachbearbeiter");
				dropdownFunction.setValue(roleName);
			}
			break;
			case("editor-extern"): {
				lblFunction.setText("Ansprechperson");
				dropdownFunction.setValue(roleName);
			}
			break;
		}

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				personType = dropdownFunction.getValue();
				if(personType.equals("power")) {
					SCEmployee sce = new SCEmployee();
					sce = (SCEmployee) validateInputs(sce);
					if(sce != null) {
						SubcontractorAddController.data2.add(sce);
						SubcontractorAddController.data2.clear();
						Stage stage = (Stage) btnSave.getScene().getWindow();
					    stage.close();
					}
				} else if(personType.equals("editor-extern")) {
					Contact contact = new Contact();
					contact = (Contact) validateInputs(contact);
					if(contact != null) {
						SubcontractorAddController.data2.add(contact);
						SubcontractorAddController.data2.clear();
						Stage stage = (Stage) btnSave.getScene().getWindow();
					    stage.close();
					}
				}
			}
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage stage = (Stage) btnSave.getScene().getWindow();
			    stage.close();
			}
		});	
		
		
	}
	
	/**
	 * Überprüft beim übergebenen Parameter, ob dieser die Mindest- und Maximallänge besitzt.
	 * @param text
	 * @param minLength
	 * @param maxLength
	 * @return
	 */	
	private boolean checkLength(String text, int minLength, int maxLength) {
		if((text.length() > maxLength) || (text.length() < minLength)) {
			return true;
		} else {
			return false;
		}
	}
	
}
