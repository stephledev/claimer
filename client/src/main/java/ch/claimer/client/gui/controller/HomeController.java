package ch.claimer.client.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import ch.claimer.shared.models.Issue;

/**
 * Kontroller für die Home-Ansicht
 * 
 * @author Michael Lötscher, Alexander Hauck
 * @since 1.0
 * @version 1.1
 *
 */

public class HomeController implements Initializable {

	Client client;
	WebTarget target;
	ResteasyWebTarget rtarget;
	ObjectMapper mapper;

	//Beinhaltet alle Projekte bei der Initialisation
	ObservableList<Issue> data = FXCollections.observableArrayList(); 
	//Contains filtered Data (search-function...)
	ObservableList<Issue> dataCopy = FXCollections.observableArrayList(); 
	List<Issue> issuesToShow = null;

	@FXML
	private Pane mainContent;

	@FXML
	private TableView<Issue> homeTableView;

	@FXML
	private TableColumn<Issue, String> colProject;

	@FXML
	private TableColumn<Issue, String> colMangel;

	@FXML
	private TableColumn<Issue, String> colStatus;

	@FXML
	private TableColumn<Issue, String> colDeadline;

	@FXML
	private TextField txt_search;

	@FXML
	private Label lbl_title;


	
	/*
	/**
	 * Initialisiert den TableView automatisch mit den nötigen Daten, sobald der View aufgerufen wird.
	 */
	@Override
	public void initialize (URL location, ResourceBundle resources) {
	
	}


}




