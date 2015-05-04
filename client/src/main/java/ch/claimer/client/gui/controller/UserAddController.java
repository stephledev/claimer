package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Subcontractor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserAddController implements Initializable{
		
	private Integer personId = null;
	
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
	private void loadUserMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (NullPointerException npe) {
			System.out.println("Fehler: View konnte nicht geladen werden");
			// ToDo Eintrag in Log-Datei
			npe.printStackTrace();
		}	catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	private void saveUser(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button.");
		
		//Überprüfen, ob alle Pflichtfelder gesetzt wurden
		
		//Personen-Objekt erstellen und alle Infos einfügen
		//Person person = new Person();
		
		if(personId != null) {
			//Weiterleiten zum Updaten
			System.out.println("Update");
		} else {
			System.out.println("Insert");
		}
		
		// ToDo: Read Data from Textfields, check them and save into Database
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
		
		
		//Dropdown für "Funktion" initialisieren
		/*
		Client client = new ResteasyClientBuilder().build();
	    WebTarget target = client.target("http://localhost:8080/webservice");
	    ResteasyWebTarget rtarget = (ResteasyWebTarget)target;
	    
	    SubcontractorProxy subcontractorProxy = rtarget.proxy(SubcontractorProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Subcontractor> subcontractorList = null;
		try {
			subcontractorList = mapper.readValue(subcontractorProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		setDropdownValues();
		
	}
	
	public void setDropdownValues()  {
		
		dropdownFunction.getItems().addAll(
				"Superadmin", 
				"Admin",
				"Power",
				"Editor-Intern",
				"Editor-Extern"
			);
		
	}
	
	
	/**
	 * Detailansicht mit allen Daten der angeklickten Person füllen
	 * @param personToEdit
	 */
	public void initData(Person personToEdit) {
		lblTitel.setText("Benutzer bearbeiten");
		
		personId = personToEdit.getId();
		
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
			if(personToEdit.getLogin().getUsername() != null) {
				txtUsername.setText(personToEdit.getLogin().getUsername());
			}
			
			if(personToEdit.getLogin().getPassword() != null) {
				pfPassword.setText(personToEdit.getLogin().getUsername());
			}
		}
		
		//Dropdown ausgewähltes setzen
		if(personToEdit.getLogin().getRole().getName() != null) {
			
			
			dropdownFunction.setValue("Power");
		}
		
		
	
	}
	
}
