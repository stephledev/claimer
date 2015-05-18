package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.pmw.tinylog.Logger;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import ch.claimer.client.proxy.ContactProxy;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;

/**
 * Controller für das Hinzufügen und Ändern von Subunternehmen
 * @author Alexander Hauck
 * @since 1.0
 * @version 1.0
 */

public class SubcontractorAddController implements Initializable {

	private ObservableList<Person> data = FXCollections.observableArrayList();
	public static ObservableList<Person> data2 = FXCollections.observableArrayList(); 
	private ObservableList<Person> personToDelete = FXCollections.observableArrayList(); 
	private Subcontractor subcontractorContainer = null;
	private Person persontoEdit = null;
	private Integer roleValue = AuthenticationUtil.getLogin().getRole().getValue();
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
	private TableColumn<Person, String> colDeleteButton;
	
	@FXML
	private TableColumn<Person, String> colFunction;
	
	@FXML
	private Label lblEmployees;
	
	@FXML
	private Button btnAddSCEmployee;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private Button btnSave;
	
	/**
	 * Initialisiert den View.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
		sceTableView.setVisible(false);
		lblEmployees.setVisible(false);
		btnAddSCEmployee.setVisible(false);
		btnDelete.setVisible(false);
		
		//Listener,um Änderungen zu überprüfen.
		data2.addListener(new ListChangeListener<Person>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Person> c) {
				if(data2.size() > 0) {
						
					data.remove(persontoEdit);	//den aktualisierten aus der Liste entfernen		
					//TableView neu Laden
					data.addAll(data2);
					fillTableView();
				}
			}
		 
		 });
		
		if(roleValue == 15) {
			btnDelete.setVisible(false);
			txtName.setEditable(false);
			txtAdress.setEditable(false);
			txtEmail.setEditable(false);
			txtPhone.setEditable(false);
			txtPlace.setEditable(false);
			txtZip.setEditable(false);
			
		}
		
	}
	
	/**
	 * Befüllt den View mit den Daten des zu bearbeitenden Subunternehmens.
	 * @param subcontractor - Subcontractor der bearbeitet wird.
	 */
	public void initData(Company subcontractor) {
		
		lblEmployees.setVisible(true);
		sceTableView.setVisible(true);
		btnAddSCEmployee.setVisible(true);
		btnDelete.setVisible(true);
		subcontractorContainer = (Subcontractor) subcontractor;
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
		ContactProxy cProxy =  ResteasyClientUtil.getTarget().proxy(ContactProxy.class);
	    ObjectMapper mapper = new ObjectMapper();
	    List<Person> personList = null;
	    
	    try {
	    	if(roleValue != 15) {
				personList = mapper.readValue(sceProxy.getBySubcontractor(subcontractorID), new TypeReference<List<SCEmployee>>(){});
				for(Person p : personList) {
					if(p.isActive()) {
						data.add(p);
					}
				}
	    	}
			
			personList = mapper.readValue(cProxy.getBySubcontractor(subcontractorID), new TypeReference<List<Contact>>(){});
			for(Person p : personList) {
				if(p.isActive()) {
					data.add(p);
				}
			}	
			
		} catch (IOException e1) {
			Logger.error("Ansprechpersonen und SCEmployees können nicht aus der Datenbank geladen werden.");
		}
		
	    fillTableView();

	}
	
	
	/**
	 * Lädt den Subunternehmen Hauptview mit einer Nachricht.
	 * @param message - Mitteilung die angezeigt werden soll.
	 */
	private void showMainViewWithMessage(String message) {
		try {
			//FXMLLoader erstellen
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/SubcontractorMainView.fxml"));
			
			//Neuen View laden
			Pane myPane;
			myPane = loader.load();
			
			//SubcontractorController holen
			SubcontractorController controller = loader.<SubcontractorController>getController();
			
			//Controller starten
			controller.initWithMessage(message);			
			
			//Neuen View einfügen
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"SubcontractorMainView.fxml\" kann nicht geladen werden.");
		}
		
	}
	
	/**
	 * Lädt die Subunternehmen Hauptansicht
	 */
	@FXML
	private void loadSubcontractorMainView() {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/SubcontractorMainView.fxml"));
			mainContent.getChildren().clear();
			mainContent.getChildren().setAll(myPane);
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"SubcontractorMainView.fxml\" kann nicht geladen werden.");
		}
	}
	
	/**
	 * Befüllt den TableView mit den Mitarbeitern des entsprechenden Subunternehmens.
	 */
	private void fillTableView() {
		colFirstname.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		colLastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		
		colFunction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Person, String> data) {
				try {
					String roleName = data.getValue().getLogin().getRole().getName();
					if(roleName.equals("power")) {
						roleName = "Sachbearbeiter";
					} else if(roleName.equals("editor-extern")) {
						roleName = "Ansprechperson";
					} else {
						roleName = "Nicht definiert";
					}
					
					return new SimpleStringProperty(roleName);
				} catch(NullPointerException e) {
					Logger.error("Probleme beim Befüllen der Subunternehmen Mitarbeiter-Tabelle, Funktions-Spalte.");
					return null;
				}
			}
		  });
		
		colDeleteButton.setCellFactory(new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
		      @Override
		      public TableCell<Person, String> call(TableColumn<Person, String> param) {
		             final TableCell<Person, String> cell = new TableCell<Person, String>() {
	                      @Override
	                      public void updateItem(String value, boolean empty) {
	                            super.updateItem(value, empty);
	                            if(!empty) {
	
		                            final VBox vbox = new VBox(0);
		                            Image image = new Image(getClass().getResourceAsStream("/delete.png"));
		                            Button button = new Button("", new ImageView(image));
		                            button.getStyleClass().add("deleteButton");
		                            final TableCell<Person, String> c = this;
		                            button.setOnAction(new EventHandler<ActionEvent>() {
		                                  @Override
		                                  public void handle(ActionEvent event) {
		                                	  try {
		                                  			Stage stage = new Stage();
		                                  			stage.setTitle("Subunternehmen löschen");
		                                  			
		                                  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
		                                  			Pane myPane = loader.load();

		                                  			Scene scene = new Scene(myPane);
		                                  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
		                                  			stage.setScene(scene);
		                                  		    
		                                  		    //Open new Stage
		                                  			stage.show();
			                            		} catch (IOException | NullPointerException e) {
			                            			Logger.error("View \"DeleteConfirmation.fxml\" kann nicht geladen werden.");
		                                  		} 
		                                	  	@SuppressWarnings("unchecked")
												TableRow<Person> tableRow = c.getTableRow();
		                                          Person person= (Person)tableRow.getTableView().getItems().get(tableRow.getIndex());
		                                          
		                                          data.remove(person);
		                                          fillTableView();
		                                          
		                                          if((Integer)person.getId() != 0) {
		                                        	  personToDelete.add(person);
		                                          }
		                                  }
		                            });
		                      vbox.getChildren().add(button);
		                      setGraphic(vbox);
		                    }
		               }
		        };
		        return cell;
		    }
		});
		
		
		//Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
		sceTableView.setItems(data);
	}
	
	/**
	 * Überprüft beim übergebenen Parameter, ob dieser die Mindest- und Maximallänge besitzt.
	 * @param text
	 * @param minLength
	 * @param maxLength
	 * @return
	 */
	private Boolean checkLength(String text, Integer minLength, Integer maxLength) {
		
		if((text.length() > maxLength) || (text.length() < minLength)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/**
	 * Löscht ein Subunternehmen.
	 */
	@FXML
	private void deleteSubcontractor() {
		try {
  			Stage stage = new Stage();
  			stage.setTitle("Subunternehmen löschen");
  			
  			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/DeleteConfirmation.fxml"));
  			Pane myPane = loader.load();

  			Scene scene = new Scene(myPane);
  			scene.getStylesheets().add(getClass().getResource("../claimer_styles.css").toExternalForm()); // CSS-File wird geladen
  			stage.setScene(scene);
  		    
  		    //Open new Stage
  			stage.show();
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"DeleteConfirmation.fxml\" kann nicht geladen werden.");
  		}
		subcontractorContainer.setActive(false);
		SubcontractorProxy scProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);
		scProxy.update(subcontractorContainer);
		showMainViewWithMessage("Subunternehmen wurde gelöscht.");
	}
	
	/**
	 * Speichert ein Subunternehmen
	 */
	@FXML
	private void saveSubcontractor() {
		Boolean hasError = false;
		
		//Subcontractor erstellen, Validation und mit eingegebenen Daten befüllen
		Subcontractor sc = new Subcontractor();
		sc.setActive(true);
		
		String name = txtName.getText();
		if(checkLength(name, 1, 255)) {
			hasError = true;
			txtName.getStyleClass().add("txtError");
		} else {
			sc.setName(name);
		}
		
		String street = txtAdress.getText();
		if(checkLength(street, 1, 255)) {
			hasError = true;
			txtAdress.getStyleClass().add("txtError");
		} else {
			sc.setStreet(street);
		}
		
		String phone = txtPhone.getText();
		if(checkLength(phone, 0, 255)) {
			hasError = true;
			txtPhone.getStyleClass().add("txtError");
		} else {
			sc.setPhone(phone);
		}
		
		String zip = txtZip.getText();
		if(checkLength(zip, 4, 5)) {
			hasError = true;
			txtZip.getStyleClass().add("txtError");
		} else {
			sc.setZip(zip);
		}
		
		String place = txtPlace.getText();
		if(checkLength(place, 1, 255)) {
			hasError = true;
			txtPlace.getStyleClass().add("txtError");
		} else {
			sc.setPlace(place);
		}
		
		String email = txtEmail.getText();
		if(checkLength(email, 0, 255)) {
			hasError = true;
			txtEmail.getStyleClass().add("txtError");
		} else {
			sc.setEmail(email);
		}
	
		//Wenn kein Fehler bei Validation: Subunternehmen speichern oder updaten.
		if(!hasError) {
			if(roleValue != 15) {
				SubcontractorProxy scProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);
				
				if(subcontractorID != null) {
					sc.setId(subcontractorID);
					scProxy.update(sc);
				} else {
					scProxy.create(sc);
				}
			}
			saveSCEmployees();
			
			showMainViewWithMessage("Änderungen erfolgreich gespeichert.");
					
			}
			
	}
	
	private void saveSCEmployees() {
		// Subcontractor Mitarbeiter auslesen und Speichern / Updaten
		ObservableList<Person> olp = sceTableView.getItems();
		olp.addAll(personToDelete);
		SCEmployeeProxy sceProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
		ContactProxy cProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);
		SCEmployee sce = null;
		Contact contact = null;
		for(Person p : olp) {
			
			if(personToDelete.contains(p)) {
				p.setActive(false);
			} else {
				p.setActive(true);
			}
			
			switch(p.getLogin().getRole().getName()) {
				case("power"): {
					sce = (SCEmployee)p;
					sce.setSubcontractor(subcontractorContainer);
					if(sce.getId() != 0) {
						//SCEmployee updaten
						sceProxy.update(sce);
					} else {
						//Neuen SCEmployee erstellen
						sceProxy.create(sce);
					}
				} break;
				case("editor-extern"): {
					contact = (Contact)p;
					contact.setSubcontractor(subcontractorContainer);
					if(contact.getId() != 0) {
						//Contact updaten
						cProxy.update(contact);
					} else {
						//Neuen Contact erstellen
						cProxy.create(contact);
					}
				}
				break;
			}
				
		}
	}

	/**
	 * Öffnet ein neues Fenster, um einen Subunternehmen-Mitarbeiter zu erfassen
	 */
	@FXML
	private void addSubcontractorStaff() {
		
		try {
			Stage stage = new Stage();
			stage.setTitle("Mitarbeiter für Subunternehmen erfassen");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserAddView.fxml"));
			Pane myPane = loader.load();
			UserAddController controller = loader.<UserAddController>getController();
			
			//Controller starten
			controller.initScStaffAdd();

			Scene scene = new Scene(myPane);
			scene.getStylesheets().add(getClass().getResource("/claimer_styles.css").toExternalForm()); // CSS-File wird geladen
			stage.setScene(scene);
		    
		    //Open new Stage
			stage.show();
		} catch (IOException | NullPointerException e) {
			Logger.error("View \"UserAddView.fxml\" kann nicht geladen werden.");
		}
	}
	
	/**
	 * Öffnet ein neues Fenster, um den angeklickten Subunternehmen-Mitarbeiter zu bearbeiten.
	 * @param t - Mouseevent = Klick auf den Subunternehmen-Mitarbeiter
	 */
	@FXML
	private void editSubcontractorStaff (MouseEvent t){
		if(t.getClickCount() == 2) {
			try {

				persontoEdit = sceTableView.getSelectionModel().getSelectedItem();
				
				Stage stage = new Stage();
				stage.setTitle("Mitarbeiter von Subunternehmen bearbeiten");
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserAddView.fxml"));
				Pane myPane = loader.load();
				UserAddController controller = loader.<UserAddController>getController();
				
				//Controller starten
				controller.initscStaffEdit(persontoEdit);
	
				Scene scene = new Scene(myPane);
				scene.getStylesheets().add(getClass().getResource("/claimer_styles.css").toExternalForm()); // CSS-File wird geladen
				
				stage.setScene(scene);
			    
			    //Open new Stage
				stage.show();
			} catch (IOException | NullPointerException e) {
				Logger.error("View \"UserAddView.fxml\" kann nicht geladen werden.");
			}
		}
	}	
}
