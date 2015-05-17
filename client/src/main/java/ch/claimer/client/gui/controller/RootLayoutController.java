package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.AuthenticationUtil;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Supervisor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Kontroller f�r das RootLayout
 * 
 * @author Alexander Hauck, Michael L�tscher
 * @since 1.0
 * @version 1.1
 *
 */

public class RootLayoutController implements Initializable {
	ObservableList<Person> data = FXCollections.observableArrayList();
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    
    ObjectMapper mapper;
    List<Person> personsToShow = null;
	public static Person personToTransmit;
	
	@FXML
	private SplitMenuButton splitMenuButton;

	
	@FXML
	private Label lblTitel;

	
	@FXML
	private Label lblName;
	
	@FXML
	private MenuItem menuLogout;
	
	@FXML
	private MenuItem menuEditUser;
	
	@FXML
	private MenuItem menuClose;
	
	@FXML
	private Button naviHome;
	
	@FXML
	private Button naviProjects;
	
	@FXML
	private Button naviSubcontractors;
	
	@FXML
	private Button naviPrincipals;
	
	@FXML
	private Button naviUsers;
	
	@FXML
	private Pane mainContent;

	
	/**
	 * Zur Home-Ansicht wechseln (mainView.xml)
	 * 
	 * @param event - ActionEvent = Klick auf den Home-Button
	 * @throws IOException
	 */
	@FXML
	private void loadHomeView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/HomeView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
	
	/**
	 * Zur Projekte-Hauptansicht (projectsMainView.xml) wechseln
	 * 
	 * @param event - ActionEvent = Klick auf den Projekt-Button
	 * @throws IOException
	 */
	@FXML
	private void loadProjectsMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectsMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	
	/**
	 * Zur Bauleiter-Hauptansicht wechseln (supervisorMainView.xml)
	 * @param event - ActionEvent = Klick auf den Bauleiter-Button
	 * @throws IOException
	 */
	@FXML
	private void loadSupervisorMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/SupervisorMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	

	/**
	 * Zur Subunternehmen-Hauptansicht wechseln (supervisorMainView.xml)
	 * @param event - ActionEvent = Klick auf den Subunternehmen-Button
	 * @throws IOException
	 */
	@FXML
	private void loadSubcontractorMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	/**
	 * Zur Kunden-Hauptansicht wechseln  (principalMainView.xml)
	 * @param event - ActionEvent = Klick auf den Kunden-Button
	 * @throws IOException
	 */
	@FXML
	private void loadPrincipalMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/PrincipalMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	/**
	 * Zur Benutzer-Hauptansicht wechseln  (userMainView.xml)
	 * @param event - ActionEvent = Klick auf den Benutzer-Button
	 * @throws IOException
	 */
	@FXML
	private void loadUserMainView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/UserMainView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
	}
	
	/**
	 * Applikatin wird geschlossen
	 * 
	 * @param event - ActionEvent = Klick auf Schliessen-Button
	 * @throws IOException
	 */
	@FXML
	private void closeClaimer(ActionEvent event) throws IOException {
		System.exit(0);
	}
	
	/**
	 * Logout und Login-Seite laden
	 * @param event - ActionEvent = Klick auf Abmelden-Button
	 * @throws IOException
	 */
	@FXML
	private void logout(ActionEvent event) throws IOException {

		try {
			
			AuthenticationUtil.setLogin(null);
			Stage stage1 = (Stage) lblTitel.getScene().getWindow();
		    stage1.close();
			
			Stage stage = new Stage();
			stage.setTitle("Claimer");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Login.fxml"));
			Pane myPane = loader.load();

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
	
	
	/**
	 * Benutzer-Profil bearbeiten
	 * 
	 * @param event - ActionEvent = Klick auf "Profil bearbeiten"-Button
	 * @throws IOException
	 */
	@FXML
	private void editUser(ActionEvent event) throws IOException {
		String username = AuthenticationUtil.getLogin().getUsername();
		System.out.println(username);
		//FXMLLoader erstelen
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../view/UserAddView.fxml")
				);

		//Neuen View laden
		Pane myPane = loader.load();
		//Neuen View einf�gen
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);

		// Falls der User ein GCEmployee ist
		if(AuthenticationUtil.getLogin().getRole().getName().equals("superadmin")){
			GCEmployeeProxy gceProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
			ObjectMapper mapper = new ObjectMapper();
			List<GCEmployee> personList1 = null;
			personList1 = mapper.readValue(gceProxy.getAll(), new TypeReference<List<GCEmployee>>(){});
			System.out.println(personList1.get(4).getLogin().getUsername());
			int i=0;
			for(personList1.get(i); i < personList1.size(); i++){

				if(personList1.get(i).getLogin().getUsername().equals(username)){
					System.out.println(personList1.get(i).getLogin().getUsername());


					//UserAddController holen
					UserAddController controller = loader.<UserAddController>getController();

					//Controller starten
					controller.initData(personList1.get(i));			
				}
				i++;
			}
		}
		// Falls der User ein Supervisor ist
		//TODO funktioniert nicht
		else {
			SupervisorProxy svProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
			List<Supervisor> personList = null;
			personList = mapper.readValue(svProxy.getAll(), new TypeReference<List<Supervisor>>(){});
			int i = 0;
			for(personList.get(i); i < personList.size(); i++){

				if(personList.get(i).getLogin().getUsername().equals(username)){
					System.out.println(personList.get(i).getLogin().getUsername());


					//UserAddController holen
					UserAddController controller = loader.<UserAddController>getController();

					//Controller starten
					controller.initData(personList.get(i));			
				}
				i++;
			}
		}


	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Namen setzen
		lblName.setText(AuthenticationUtil.getLogin().getUsername());
		
		//Mainnavi an die Rolle anpassen
		String roleName = AuthenticationUtil.getLogin().getRole().getName();
		switch(roleName) {
			case("power"): {
				naviPrincipals.setVisible(false); //Bauherren-Navipunkt ausblenden
				naviUsers.setVisible(false); //Benutzer-Navipunkt ausblenden
			}
			break;
			case("editor-intern"):
			break;
			case("editor-extern"):
			break;
		}
		
	}
	
	
}
