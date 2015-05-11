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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserAddController implements Initializable{
		
	private Integer personId = null;
	private Integer loginID = null;
	private String personType = null;
	
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
	
	@FXML
	private void deleteUser() {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().
		    children(new Text("Hi"), new Button("Ok.")).
		    alignment(Pos.CENTER).padding(new Insets(5)).build()));
		dialogStage.show();	}
	
	@FXML
	private void saveUser(ActionEvent event) throws IOException {
			
		// Typ des Personenobjekts bestimmen und passende funktion aufrufen
		personType = dropdownFunction.getValue();
		switch(personType) {
			case "superadmin": saveGCEmployee();
				break;
			case "admin": //TODO 
				break; 
			case "power": saveSCEmployee();
				break;
			case "editor-intern": saveSupervisor();
				break;
			case "editor-extern": saveContact();
				break;
		}
	}
	

	private void saveContact() {
		Contact person = new Contact();
		
		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		person = (Contact) getTextfieldProperties(person);
		
		ContactProxy cProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);
		if(personId != null) {
			cProxy.update(person);
		} else {
			cProxy.create(person);
		}
		
		showMainViewWithMessage();
	}

	private void saveSupervisor() {
		Supervisor person = new Supervisor();
		
		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		person = (Supervisor) getTextfieldProperties(person);
		
		SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		if(personId != null) {
			svProxy.update(person);
		} else {
			svProxy.create(person);
		}
		
		showMainViewWithMessage();
	}

	private void saveSCEmployee() {
		
		// TODO BUG somewhere in here
		
		SCEmployee person = new SCEmployee();
		
		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		person = (SCEmployee) getTextfieldProperties(person);
		
		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
		if(personId != null) {
			sceProxy.update(person);
		} else {
			sceProxy.create(person);
		}
		
		showMainViewWithMessage();
	}

	private void saveGCEmployee() {

		GCEmployee person = new GCEmployee();
		
		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		person = (GCEmployee) getTextfieldProperties(person);
		
		GCEmployeeProxy gceProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		if(personId != null) {
			gceProxy.update(person);
		} else {
			gceProxy.create(person);
		}
		
		showMainViewWithMessage();
	}


	/**
	 * UserMainView laden inklusive Statusmeldung.
	 */
	private void showMainViewWithMessage() {

		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//PrincipalController holen
			UserController controller = loader.<UserController>getController();
			
			//Controller starten
			controller.initWithMessage("Änderungen erfolgreich vorgenommen.");			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		setDropdownValues();
	}
	

	/**
	 * Werte für das "Funktionen"-Dropdown setzen
	 */
	public void setDropdownValues()  {
		
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
	}
	
	
	/**
	 * Detailansicht mit allen Daten der angeklickten Person füllen
	 * @param personToEdit
	 */
	public void initData(Person personToEdit) {
		lblTitel.setText("Benutzer bearbeiten");
			
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
		}
	}
	
	
	private Person getTextfieldProperties(Person person) {
		// Alle Felder auslesen und dem Personen-Objekt zuweisen
		person.setFirstname(txtFirstname.getText());
		person.setLastname(txtLastname.getText());
		person.setEmail(txtEmail.getText());
		person.setPhone(txtPhone.getText());
		person.setActive(true);
		if(personId != null) {
			person.setId(personId);
		}
		
		// Neues Login erstellen und Feldinhalte zuweisen
		Login login = new Login();
		login.setPassword(pfPassword.getText());
		login.setUsername(txtUsername.getText());
		if(loginID != null) {
			login.setId(loginID);
		}
		
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
		
		
		 //Login der Person zuweisen
		person.setLogin(login);
		return person;
	}

	public void initSCEAdd() {
		
		dropdownFunction.getItems().clear();
		dropdownFunction.getItems().add("power");
		dropdownFunction.setValue("power");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SCEmployee sce = new SCEmployee();
				sce = (SCEmployee) getTextfieldProperties(sce);

				SubcontractorAddController.data2.add(sce);
				SubcontractorAddController.data2.clear();
				Stage stage = (Stage) btnSave.getScene().getWindow();
			    stage.close();
				
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
		
		initData(scEmployeeToEdit);
		
		dropdownFunction.getItems().clear();
		dropdownFunction.getItems().add("power");
		dropdownFunction.setValue("power");

		btnSave.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SCEmployee sce = new SCEmployee();
				sce = (SCEmployee) getTextfieldProperties(sce);

				SubcontractorAddController.data2.add(sce);
				SubcontractorAddController.data2.clear();
				Stage stage = (Stage) btnSave.getScene().getWindow();
			    stage.close();
				
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
