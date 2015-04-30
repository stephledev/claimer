package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.LoginProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.shared.models.Company;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Subcontractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author Kevin Stadelmann
 * @since 16.04.2015
 * @version 1.0
 *
 */

public class SubcontractorController {
	
	ObservableList<Company> data =
			FXCollections.observableArrayList(
			);
	
	//Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private TableView<Company> subcontractorTableView;
	
	@FXML
	private Pane mainContent;
	
	@FXML
	private TableColumn<Company, String> colName;
	
	@FXML
	private TableColumn<Company, String> colZip;
	
	@FXML
	private TableColumn<Company, String> colPlace;
	
	@FXML
	private TableColumn<Company, String> colPhone;
	
	@FXML
	private TableColumn<Company, String> colPlz;
	
	@FXML
	private TableColumn<Company, String> colEmail;
	

	//Zur Home-Ansicht wechseln (mainView.xml)
	@FXML
	private void loadSubcontracotrAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/CompanyAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);
		
	}
	
	//Zum User Add/Change View wechseln (UserAddView.xml)
	@FXML
	private void loadSubcontractorAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);	
	}
	
	
	@FXML
	private void editUser(MouseEvent t) throws IOException {
        
        //Wenn Doppelklick auf Person
        if(t.getClickCount() == 2) {
               //Angeklickte Firma laden
               Company subcontractor = subcontractorTableView.getSelectionModel().getSelectedItem();
            		   
               Pane myPane = FXMLLoader.load(getClass().getResource("../view/SubcontractorAddView.fxml"));
               mainContent.getChildren().clear();
               mainContent.getChildren().setAll(myPane);     
        
        }
	}
	
	public void initialize() {
		
		
		////Subunternehmen aus Datenbank laden
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
		}
		
		Subcontractor subcontractorFromDB = new Subcontractor();
		for(int i=0; i<subcontractorList.size(); i++) {
			subcontractorFromDB = subcontractorList.get(i);
			data.add(subcontractorFromDB);
			subcontractorFromDB = null;
		}
		
		
		//Spalten-Values definieren (m�ssen den Parameter des Company-Objekts entsprechen)
		colName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
		colPhone.setCellValueFactory(new PropertyValueFactory<Company, String>("phone"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
		colPlace.setCellValueFactory(new PropertyValueFactory<Company, String>("place"));
		colZip.setCellValueFactory(new PropertyValueFactory<Company, String>("zip"));

		//Observable-List, welche die Daten beinhaltet, an die Tabelle �bergeben
		subcontractorTableView.setItems(data);
		
	}
	
}
