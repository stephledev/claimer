package ch.claimer.client.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Person;
import ch.claimer.shared.models.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */
public class ProjectAddController implements Initializable {
	
	ObservableList<Project> data =
			FXCollections.observableArrayList(
			);

	//Maincontent, hierhin werden die verschiedenen Views geladen
			@FXML
			private Pane mainContent;
			
			@FXML
			private Label lbl_title;
			
			@FXML
			private Button bttn_saveProject;
			
			@FXML
			private Button bttn_quitProject;
			
			@FXML
			private Button bttn_addMangle;
			
			@FXML
			private TextField txt_projectName;
			
			@FXML
			private TextField txt_street;
			
			@FXML
			private TextField txt_plz;
			
			@FXML
			private TextField txt_place;
			
			@FXML
			private TextField txt_principalPhone;
			
			@FXML
			private ComboBox<?> combo_supervisor;
			
			@FXML
			private ComboBox<?> combo_status;
			
			@FXML
			private ComboBox<?> combo_principal;
			
			@FXML
			private DatePicker date_start;
			
			@FXML
			private DatePicker date_end;
			
			@FXML
			private TableView<Issue> mangleTableView;
			
			@FXML
			private TableColumn<Issue, String> colMangle;
			
			@FXML
			private TableColumn<Issue, String> colSubcontractor;
			
			@FXML
			private TableColumn<Issue, String> colDeadline;
			
			@FXML
			private TableColumn<Issue, String> colStatus;
			
			
			

			//Zur ProjectMangle-Ansicht wechseln (mainView.xml)
			@FXML
			private void loadProjectMangleView(ActionEvent event) throws IOException {
				Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectMangleView.fxml"));
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
				
			}
			
			//Zur ProjectMain-Ansicht wechseln (mainView.xml)
			@FXML
			private void loadProjectMainView(ActionEvent event) throws IOException {
				Pane myPane = FXMLLoader.load(getClass().getResource("../view/ProjectsMainView.fxml"));
				mainContent.getChildren().clear();
				mainContent.getChildren().setAll(myPane);
				
			}
			
			public void initialize() {
				ObservableList<Issue> data = FXCollections.observableArrayList();

				// Mangel als Platzhalter
				Issue i1 = new Issue();
				i1.setId(1);
//				i1.setProject(p1);
				i1.setDescription("Wand fehlt");
//				i1.setSolved(new GregorianCalendar(1,1,2000));
//				i1.setCreated(new GregorianCalendar(1,1,2001));
//				i1.setState(State);
//				i1.setSubcontractor(subcontractor);
				
			
				
				

				// Daten zu Observable-List hinzufügen
				data.addAll(i1);

				// Spalten-Values definieren (müssen den Parameter des Personen-Objekts
				// entsprechen)
				colMangle.setCellValueFactory(new PropertyValueFactory<Issue, String>("description"));
				colSubcontractor.setCellValueFactory(new PropertyValueFactory<Issue, String>("subcontractor"));
				colDeadline.setCellValueFactory(new PropertyValueFactory<Issue, String>("end"));
				colStatus.setCellValueFactory(new PropertyValueFactory<Issue, String>("state"));
				// colProject.setSortTyp(descending);

				// Observable-List, welche die Daten beinhaltet, an die Tabelle übergeben
				mangleTableView.setItems(data);

			}

			@Override
			public void initialize(URL arg0, ResourceBundle arg1) {
				// TODO Auto-generated method stub
				
			}
			
		}