package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ch.claimer.client.proxy.PrincipalProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Principal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Controller für das Hinzufügen und Ändern von Bauherren
 * @author Alexander Hauck
 * @since 02.05.2015
 * @version 2.0
 *
 */

public class PrincipalAddController implements Initializable {
	
	private Integer principalID = null;
	
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
	private void loadPrincipalMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/PrincipalMainView.fxml"));
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
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}

	/**
	 * Fenster mit zu bearbeitendem Bauherr füllen.
	 * @param principalToEdit
	 */
	public void initData(Principal principalToEdit) {
		
		principalID = principalToEdit.getId();
		
		//Texte im View anpassen
		lblTitel.setText("Bauherr bearbeiten");
		lblDescription.setText("");
		
		// Überprüfen ob Principal eine Person oder eine Firma ist
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
	 * Principal als Person speichern
	 */
	@FXML
	private void savePrincipalPerson() {
		
		//Eingegebene Daten auslesen
		Principal pr1 = new Principal();
		pr1.setFirstname(txtPersonFirstname.getText());
		pr1.setLastname(txtPersonLastname.getText());
		pr1.setEmail(txtPersonEmail.getText());
		pr1.setPhone(txtPersonPhone.getText());
		pr1.setStreet(txtPersonAdress.getText());
		pr1.setZip(txtPersonZIP.getText());
		pr1.setPlace(txtPersonPlace.getText());
				
		if(principalID != null) {
			pr1.setId(principalID);
			updatePrincipal(pr1);
		} else {
			createPrincipal(pr1);
		}
		
		loadPrincipalView(); //PrincipalMainView laden inkl. Meldung
		
	}
	
	/**
	 * Principal als Company speichern
	 */
	@FXML
	private void savePrincipalCompany() {
		//Eingegebene Daten auslesen
		Principal pr2 = new Principal();
		pr2.setCompany(txtCompanyName.getText());
		pr2.setEmail(txtCompanyEmail.getText());
		pr2.setPhone(txtCompanyPhone.getText());
		pr2.setStreet(txtCompanyAdress.getText());
		pr2.setZip(txtCompanyZIP.getText());
		pr2.setPlace(txtCompanyPlace.getText());

		if(principalID != null) {
			pr2.setId(principalID);
			updatePrincipal(pr2);
		} else {
			createPrincipal(pr2);
		}
		
		loadPrincipalView();

	}
	
	/**
	 * Bestehenden Principal in der Datenbank updaten
	 * @param principal
	 */
	private void updatePrincipal(Principal principal) {
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
		principalProxy.update(principal);
	}
	
	
	/**
	 * Neuen Principal in der Datenbank speichern
	 * @param principal
	 */
	private void createPrincipal(Principal principal) {
		PrincipalProxy principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
		principalProxy.create(principal);
	}

	/**
	 * PrincipalMainView laden inklusive Statusmeldung.
	 */
	private void loadPrincipalView() {
		//PrincipalMainView laden inkl. Meldung
		try {

			//FXMLLoader erstelen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PrincipalMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//PrincipalController holen
			PrincipalController controller = loader.<PrincipalController>getController();
			
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
	
	
}
