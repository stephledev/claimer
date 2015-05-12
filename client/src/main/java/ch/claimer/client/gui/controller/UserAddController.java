package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import ch.claimer.client.proxy.ContactProxy;
import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.RoleProxy;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.AuthenticationUtil;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserAddController implements Initializable{
		
	private Integer personId = null;
	private Integer loginID = null;
	private String personType = null;
	private Person personContainer = null;
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

	
	@FXML
	private void loadUserMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (NullPointerException npe) {
			System.out.println("Fehler: View konnte nicht geladen werden");
			// TODO Eintrag in Log-Datei
			npe.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	/**
	 * UserMainView laden inklusive Statusmeldung.
	 */
	private void showMainViewWithMessage(String message) {

		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserMainView.fxml"));
			
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void deleteUser() {
		toDelete = true;
		//TODO Confirmation Window
	
		personHandler();
	}
	
	@FXML
	private void saveUser(ActionEvent event) throws IOException {
		
		personHandler();
	}
	
	/**
	 * Entscheidet anhand des Dropdowns automatisch, welche Person aktualisiert werden soll.
	 */
	private void personHandler() {
		// Typ des Personenobjekts bestimmen und passende funktion aufrufen
		personType = dropdownFunction.getValue();
	
		if(personType.equals("superadmin")) {
			GCEmployee gce = new GCEmployee();
			gce = (GCEmployee)validateInputs(gce);
			if(gce != null) {
				saveGCEmployee(gce);
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} else if(personType.equals("admin")) {
			//TODO
		} else if(personType.equals("power")) {
			SCEmployee sce = new SCEmployee();
			sce = (SCEmployee) validateInputs(sce);
			if(sce != null) {
				saveSCEmployee(sce);
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} else if(personType.equals("editor-intern")) {
			Supervisor sv = new Supervisor();
			sv = (Supervisor) validateInputs(sv);
			if(sv != null) {
				saveSupervisor(sv);
				showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
			}
		} else if(personType.equals("editor-extern")) {
			Contact contact = new Contact();
			contact = (Contact) validateInputs(contact);
			if(contact != null) {
				saveContact(contact);
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
			 
			 for(Role role : roleList) {
				 if(role.getName().equals(dropdownFunction.getValue())) {
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
	
	private void saveContact(Person person) {
		Contact contact = new Contact();
		contact = (Contact)person;
		
		if(toDelete) {
			contact.setActive(false);
		}
		
		ContactProxy cProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);
		if(personId != null) {
			cProxy.update(contact);
		} else {
			cProxy.create(contact);
		}
	}

	private void saveSupervisor(Person person) {
		Supervisor supervisor = new Supervisor();
		supervisor = (Supervisor) person;
		
		if(toDelete) {
			supervisor.setActive(false);
		}
		
		SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		if(personId != null) {
			svProxy.update(supervisor);
		} else {
			svProxy.create(supervisor);
		}
	}

	private void saveSCEmployee(Person person) {

		SCEmployee scEmployee = new SCEmployee();
		scEmployee = (SCEmployee) person;
		
		if(toDelete) {
			scEmployee.setActive(false);
		}
		
		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
		if(personId != null) {
			sceProxy.update(scEmployee);
		} else {
			sceProxy.create(scEmployee);
		}
	}

	private void saveGCEmployee(Person person) {

		GCEmployee gcEmployee = new GCEmployee();
		gcEmployee = (GCEmployee)person;

		if(toDelete) {
			gcEmployee.setActive(false);
		}

		GCEmployeeProxy gceProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		if(personId != null) {
			gceProxy.update(gcEmployee);
		} else {
			gceProxy.create(gcEmployee);
		}
	}


	@FXML
	private void uploadImage(ActionEvent event) throws IOException {
        final FileChooser fileChooser = new FileChooser();
        Desktop desktop = Desktop.getDesktop();
        Stage stage = new Stage();
		System.out.println("Klick auf Button.");
		// ToDo: Upload-Fenster öffnen, Bild überprüfen, bild speichern
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
        	 desktop.open(file);
        }
	} 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {		

		lblFunction.setVisible(false);
		
		//Dropdown Values initialisieren
		RoleProxy roleProxy = ResteasyClientUtil.getTarget().proxy(RoleProxy.class);		
	    ObjectMapper mapper = new ObjectMapper();	    
	    List<Role> roleList = null;
	    
		try {
			 roleList = mapper.readValue(roleProxy.getAll(), new TypeReference<List<Role>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//Rollen dem Dropdown hinzufügen
		for(Role role: roleList) {
			dropdownFunction.getItems().add(role.getName());
		}	
		
		//Delete-Button aktivieren
		btnDelete.setVisible(false);
	}	
	
	/**
	 * Detailansicht mit allen Daten der angeklickten Person füllen
	 * @param personToEdit
	 */
	public void initData(Person personToEdit) {
		personContainer = personToEdit;
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
			dropdownFunction.setValue(personToEdit.getLogin().getRole().getName());
			//dropdownFunction.set
			lblFunction.setText(personToEdit.getLogin().getRole().getName());
		}
	}

	
	private boolean checkLength(String text, int minLength, int maxLength) {
		if((text.length() > maxLength) || (text.length() < minLength)) {
			return true;
		} else {
			return false;
		}
	}


	/**
	 * Spezielle Behandlung von Subunternehmen-Mitarbeitern, die über den "Subcontractor"-View hinzugefügt werden.
	 */
	public void initSCEAdd() {
		
		dropdownFunction.getItems().clear();
		dropdownFunction.getItems().add("power");
		dropdownFunction.setValue("power");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				SCEmployee sce = new SCEmployee();
				sce = (SCEmployee) validateInputs(sce);
				if(sce != null) {
					SubcontractorAddController.data2.add(sce);
					SubcontractorAddController.data2.clear();
					Stage stage = (Stage) btnSave.getScene().getWindow();
				    stage.close();
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
	
	public void initSCEEdit(SCEmployee scEmployeeToEdit) {
		
		btnDelete.setVisible(true);
		initData(scEmployeeToEdit);
		
		dropdownFunction.getItems().clear();
		dropdownFunction.getItems().add("power");
		dropdownFunction.setValue("power");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SCEmployee sce = new SCEmployee();
				sce = (SCEmployee) validateInputs(sce);
				
				if(sce != null) {
					SubcontractorAddController.data2.add(sce);
					SubcontractorAddController.data2.clear();
					Stage stage = (Stage) btnSave.getScene().getWindow();
				    stage.close();
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
	
}
