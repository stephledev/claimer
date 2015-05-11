package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;

/**
 * Controller für das Hinzufügen und Ändern von Subunternehmen
 * @author Alexander Hauck
 * @since 30.04.2015
 * @version 1.0
 */

public class SubcontractorAddController implements Initializable {

	private ObservableList<Person> data = FXCollections.observableArrayList();
	public static ObservableList<Person> data2 = FXCollections.observableArrayList(); 
	
	private SCEmployee sceToEdit = null;
	
	private Integer subcontractorID = null;
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private Label lblTitel;
	
	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtAdress;
	
	@FXML
	private TextField txtZip;
	
	@FXML
	private TextField txtPlace;
	
	@FXML
	private TextField txtPhone;
	
	@FXML
	private TextField txtEmail;
	
	@FXML
	private TableView<Person> sceTableView;
	
	@FXML
	private TableColumn<Person, String> colLastname;
	
	@FXML
	private TableColumn<Person, String> colFirstname;
	
	@FXML
	private TableColumn<Person, String> colEmail;
	
	@FXML
	private TableColumn<Person, String> colPhone;
	
	@FXML
	private Label lblEmployees;
	
	@FXML
	private Button btnAddSCEmployee;
	
	/**
	 * Initialisiert den View mit den Daten des angeklickten Unternehmens
	 * @param subcontractor
	 */
	public void initData(Company subcontractor) {
		
		lblEmployees.setVisible(true);
		sceTableView.setVisible(true);
		btnAddSCEmployee.setVisible(true);
		
		
		subcontractorID = subcontractor.getId();
	
		lblTitel.setText("Subunternehmen bearbeiten");
		
		txtName.setText(subcontractor.getName());
		txtAdress.setText(subcontractor.getStreet());
		txtZip.setText(subcontractor.getZip());
		txtPlace.setText(subcontractor.getPlace());
		txtPhone.setText(subcontractor.getPhone());
		txtEmail.setText(subcontractor.getEmail());
	    
		
		//Mitarbeiter der Firma aus der DB laden
		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Person> personList = null;
	    
	    try {
	    	
			personList = mapper.readValue(sceProxy.getBySubcontractor(subcontractorID), new TypeReference<List<SCEmployee>>(){});
			for(Person p : personList) {
				data.add(p);
			}
						
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	    fillTableView();

	}
	
	/**
	 * Lädt die Subunternehmen Hauptansicht
	 */
	@FXML
	private void loadSubcontractorMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorMainView.fxml"));
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
	
	private void fillTableView() {
		//Tabelle initialisieren
		colFirstname.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		sceTableView.setItems(data);
	}
	
	@FXML
	private void saveSubcontractor() {
		
		//Subcontractor erstellen und mit eingegebenen Daten befüllen
		Subcontractor sc = new Subcontractor();
		sc.setName(txtName.getText());
		sc.setStreet(txtAdress.getText());
		sc.setPhone(txtPhone.getText());
		sc.setZip(txtZip.getText());
		sc.setPlace(txtPlace.getText());
		sc.setEmail(txtEmail.getText());
		sc.setActive(true);
		
		//Neuen Subcontractor erstellen oder bestehenden updaten
		SubcontractorProxy scProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);
		
		if(subcontractorID != null) {
			sc.setId(subcontractorID);
			scProxy.update(sc);
		} else {
			scProxy.create(sc);
		}
		
		showMainViewWithMessage();
		
		
		// Subcontractor Mitarbeiter auslesen und Updaten
		ObservableList<Person> olp = sceTableView.getItems();

		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
		SCEmployee sce = null;
		for(Person p : olp) {
			sce = (SCEmployee)p;
			
			if(sce.getId() != 0) {
				sce.setSubcontractor(sc);

				//SCEmployee updaten
				sceProxy.update(sce);
			} else {
				//Neuen SCEmployee erstellen
				sce.setSubcontractor(sc);
				sceProxy.create(sce);
			}
				
		}
			
		
		
	}
	
	private void showMainViewWithMessage() {
		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SubcontractorMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//SubcontractorController holen
			SubcontractorController controller = loader.<SubcontractorController>getController();
			
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

	/**
	 * Neuen View öffnen, um einen SCEmployee zu erfassen
	 */
	@FXML
	private void addSCEmployee() {
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Mitarbeiter für Subunternehmen erfassen");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserAddView.fxml"));
			Pane myPane = loader.load();
			UserAddController controller = loader.<UserAddController>getController();
			
			//Controller starten
			controller.initSCEAdd();

			Scene scene = new Scene(myPane);
			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
			stage.setScene(scene);
		    
		    //Open new Stage
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@FXML
	private void editSCEmployee (MouseEvent t){
		if(t.getClickCount() == 2) {
			try {
				
				sceToEdit = (SCEmployee) sceTableView.getSelectionModel().getSelectedItem();
				
				Stage stage = new Stage();
				stage.setTitle("Mitarbeiter von Subunternehmen bearbeiten");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/UserAddView.fxml"));
				Pane myPane = loader.load();
				UserAddController controller = loader.<UserAddController>getController();
				
				//Controller starten
				controller.initSCEEdit(sceToEdit);
	
				Scene scene = new Scene(myPane);
				scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
				stage.setScene(scene);
			    
			    //Open new Stage
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
		sceTableView.setVisible(false);
		lblEmployees.setVisible(false);
		btnAddSCEmployee.setVisible(false);
		
		//Listener,um Änderungen zu überprüfen.
		data2.addListener(new ListChangeListener<Person>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Person> c) {
				if(data2.size() > 0) {
						
					data.remove(sceToEdit);	//den aktualisierten aus der Liste entfernen		
					//TableView neu Laden
					data.addAll(data2);
					fillTableView();
				}
				
			}
		 
		 });
		
	}

	
}
