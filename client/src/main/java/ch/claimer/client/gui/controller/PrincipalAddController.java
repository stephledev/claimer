package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.pmw.tinylog.Logger;

import ch.claimer.client.proxy.PrincipalProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Principal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller für das Verwalten von Bauherren
 * 
 * @author Alexander Hauck
 * @since 10.04.2015
 * @version 2.0
 */

public class PrincipalAddController implements Initializable {
	
	private Integer principalID = null;
	private Principal principalContainer = null;
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TabPane tabPanePrincipal;
	
	@FXML
	private Tab tabPerson; 
	
	@FXML
	private Tab tabCompany;
	
	@FXML
	private TextField txtPersonLastname;
	
	@FXML
	private TextField txtPersonFirstname;
	
	@FXML
	private TextField txtPersonAdress;
	
	@FXML
	private TextField txtPersonZIP;
	
	@FXML
	private TextField txtPersonPlace;
	
	@FXML
	private TextField txtPersonEmail;
	
	@FXML
	private TextField txtPersonPhone;
	
	@FXML
	private TextField txtCompanyName;
	
	@FXML
	private TextField txtCompanyAdress;
	
	@FXML
	private TextField txtCompanyZIP;
	
	@FXML
	private TextField txtCompanyPlace;
	
	@FXML
	private TextField txtCompanyEmail;
	
	@FXML
	private TextField txtCompanyPhone;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Button btnDelete2;
	
	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Löschen-Buttons ausblenden
		btnDelete.setVisible(false);
		btnDelete2.setVisible(false);
	}
	
	/**
	 * Füllt den View mit den Daten von dem zu bearbeitenden Bauherr.
	 * @param principalToEdit Bauherr, der bearbeitet werden soll
	 */
	public void initData(Principal principalToEdit) {
		
		principalContainer = principalToEdit;
		principalID = principalToEdit.getId();
		
		//Texte im View anpassen
		lblTitel.setText("Bauherr bearbeiten");
		lblDescription.setText("");
		btnDelete.setVisible(true);
		btnDelete2.setVisible(true);
		
		if(principalToEdit.getCompany() != null) {
			
			tabPanePrincipal.getSelectionModel().select(tabCompany);
			tabPerson.setDisable(true);
			txtCompanyName.setText(principalToEdit.getCompany());
			txtCompanyAdress.setText(principalToEdit.getStreet());
			txtCompanyEmail.setText(principalToEdit.getEmail());
			txtCompanyPhone.setText(principalToEdit.getPhone());
			txtCompanyPlace.setText(principalToEdit.getPlace());
			txtCompanyZIP.setText(principalToEdit.getZip());
			
		} else {
			tabPanePrincipal.getSelectionModel().select(tabPerson);
			tabCompany.setDisable(true);
			txtPersonFirstname.setText(principalToEdit.getFirstname());
			txtPersonLastname.setText(principalToEdit.getLastname());
			txtPersonEmail.setText(principalToEdit.getEmail());
			txtPersonPhone.setText(principalToEdit.getPhone());
			txtPersonPlace.setText(principalToEdit.getPlace());
			txtPersonZIP.setText(principalToEdit.getZip());
			txtPersonAdress.setText(principalToEdit.getStreet());
		}
	}
	
	/**
	 * Kehrt zurück zum Bauherren Hauptview.
	 */
	@FXML
	private void loadPrincipalMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/PrincipalMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"PrincipalMainView.fxml\" kann nicht geladen werden.");
		}
		
	}
	
	/**
	 * Kehrt zurück zum Bauherren Hauptview und gibt dort eine Meldung aus.
	 * @param message Meldung, die ausgegeben werden soll
	 */
	private void loadPrincipalMainViewWithMessage(String message) {

		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrincipalMainView.fxml")); //FXMLLoader erstellen
			Pane myPane = loader.load(); //Neuen View laden
			PrincipalController controller = loader.<PrincipalController>getController(); //PrincipalController holen
			controller.initWithMessage(message); //Controller starten			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"PrincipalMainView.fxml\" kann nicht geladen werden.");
		}
	}

	/**
	 * Löscht einen Bauherr.
	 */
	@FXML
	public void deletePrincipal() {
		try {
  			Stage stage = new Stage();
  			stage.setTitle("Bauleiter löschen");
  			
  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
  			Pane myPane = loader.load();

  			Scene scene = new Scene(myPane);
  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
  			stage.setScene(scene);
  		    
  		    //Open new Stage
  			stage.show();
  			
  			principalContainer.setActive(false);
  			updatePrincipal(principalContainer);
  			loadPrincipalMainViewWithMessage("Bauherr erfolgreich gelöscht.");
  			
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"DeleteConfirmation.fxml\" kann nicht geladen werden.");
  		}	
	}
	
	/**
	 * Überprüft beim übergebenen Parameter, ob dieser die Mindest- und Maximallänge besitzt.
	 * @param text String, der überprüft werden soll
	 * @param minLength Mindestlänge
	 * @param maxLength Maximallänge
	 * @return True oder False
	 */
	private Boolean checkLength(String text, int minLength, int maxLength) {
		
		if((text.length() > maxLength) || (text.length() < minLength)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Speichert einen Bauherr als Person
	 */
	@FXML
	private void savePrincipalPerson() {
		
		Boolean hasError = false;
		
		//Eingegebene Daten auslesen und überprüfen
		Principal pr1 = new Principal();
		
		String lastname = txtPersonLastname.getText();
		if(checkLength(lastname, 1, 255)) {
			hasError = true;
			txtPersonLastname.getStyleClass().add("txtError");
		} else {
			pr1.setLastname(lastname);
		}
		
		String firstname = txtPersonFirstname.getText();
		if(checkLength(firstname, 1, 255)) {
			hasError = true;
			txtPersonFirstname.getStyleClass().add("txtError");
		} else {
			pr1.setFirstname(firstname);
		}
		
		String email = txtPersonEmail.getText();
		if(checkLength(email, 0, 255)) {
			hasError = true;
			txtPersonEmail.getStyleClass().add("txtError");
		} else {
			pr1.setEmail(email);
		}
		
		String phone = txtPersonPhone.getText();
		if(checkLength(phone, 0, 255)) {
			hasError = true;
			txtPersonPhone.getStyleClass().add("txtError");
		} else {
			pr1.setPhone(phone);
		}
		
		String street = txtPersonAdress.getText();
		if(checkLength(street, 1, 255)) {
			hasError = true;
			txtPersonAdress.getStyleClass().add("txtError");
		} else {
			pr1.setStreet(street);
		}
		
		String zip = txtPersonZIP.getText();
		if(checkLength(zip, 4, 5)) {
			hasError = true;
			txtPersonZIP.getStyleClass().add("txtError");
		} else {
			pr1.setZip(zip);
		}
		
		String place = txtPersonPlace.getText();
		if(checkLength(place, 1, 255)) {
			hasError = true;
			txtPersonPlace.getStyleClass().add("txtError");
		} else {
			pr1.setPlace(place);
		}
		
		//Wenn kein Validations-Fehler: Bauherr speichern oder updaten
		if(hasError == false) {		
			if(principalID != null) {
				pr1.setId(principalID);
				updatePrincipal(pr1);
			} else {
				createPrincipal(pr1);
			}
			
			loadPrincipalMainViewWithMessage("Änderungen erfolgreich gespeichert."); //PrincipalMainView laden inkl. Meldung
		}
	}
	
	/**
	 * Bauherr als Firma speichern
	 */
	@FXML
	private void savePrincipalCompany() {
		
		Boolean hasError = false;
		
		//Eingegebene Daten auslesen und auf Fehler überprüfen
		Principal pr2 = new Principal();
		
		String name = txtCompanyName.getText();
		if(checkLength(name, 1, 255)) {
			hasError = true;
			txtCompanyName.getStyleClass().add("txtError");
		} else {
			pr2.setCompany(name);
		}
		
		String email = txtCompanyEmail.getText();
		if(checkLength(email, 0, 255)) {
			hasError = true;
			txtCompanyEmail.getStyleClass().add("txtError");
		} else {
			pr2.setEmail(email);
		}
		
		String phone = txtCompanyPhone.getText();
		if(checkLength(phone, 0, 255))  {
			hasError = true;
			txtCompanyPhone.getStyleClass().add("txtError");
		} else {
			pr2.setPhone(phone);
		}
		
		String street = txtCompanyAdress.getText();
		if(checkLength(street, 1, 255)) {
			hasError = true;
			txtCompanyAdress.getStyleClass().add("txtError");
		} else {
			pr2.setStreet(street);
		}
		
		String zip = txtCompanyZIP.getText();
		if(checkLength(zip, 4, 5)) {
			hasError = true;
			txtCompanyZIP.getStyleClass().add("txtError");
		} else {
			pr2.setZip(zip);
		}
		
		String place = txtCompanyPlace.getText();
		if(checkLength(place,1,255)) {
			hasError = true;
			txtCompanyPlace.getStyleClass().add("txtError");
		} else {
			pr2.setPlace(place);
		}
		
		//Wenn keine Validations-Fehler: Bauherr speichern oder updaten.
		if(hasError == false) {
			if(principalID != null) {
				pr2.setId(principalID);
				updatePrincipal(pr2);
			} else {
				createPrincipal(pr2);
			}
			
			loadPrincipalMainViewWithMessage("Änderungen erfolgreich gespeichert");
		}
	}
	
	/**
	 * Aktualisiert einen bestehenden Bauherr in der Datenbank.
	 * @param principal Bauherr, der aktualisiert werden soll
	 */
	private void updatePrincipal(Principal principal) {
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
		principalProxy.update(principal);
	}
	
	/**
	 * Speichert einen neuen Bauherr in der Datenbank.
	 * @param principal Bauherr, der gespeichert werden soll
	 */
	private void createPrincipal(Principal principal) {
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
		principalProxy.create(principal);
	}
	
}
