package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserAddController implements Initializable{
	
	private Context context;
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private TextField txtEmail;
	
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
	private void addUser(ActionEvent event) throws IOException {
		System.out.println("Klick auf Button.");
		
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
	
	//Wenn Person zum Ändern angeklickt wurde:
	public void getPersonFromDB(Integer personId) {
	
		//Personen-Objekt aus DB holen (get Person by ID xy)
		Person personToEdit = new GCEmployee();
		personToEdit.setId(personId);
		personToEdit.setEmail("max.muster@stud.hslu.ch");
		
		//Personen-Objekt weitergeben
		editThisPerson(personToEdit);
		
		
	}
	
	public void editThisPerson(Person person) {
		String email = person.getEmail();
		txtEmail.setText(email);
	}

	public void initData(Integer personID) {
		// TODO Auto-generated method stub
		System.out.println("hehe");
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	
}
