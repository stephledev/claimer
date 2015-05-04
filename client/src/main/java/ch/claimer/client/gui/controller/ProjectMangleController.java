package ch.claimer.client.gui.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CommentProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.LoginProxy;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Login;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author Michael Lötscher
 * @since 21.04.2015
 * @version 1.1
 *
 */

public class ProjectMangleController implements Initializable {
	
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget;
    ObjectMapper mapper;
	
    ObservableList<Comment> data = FXCollections.observableArrayList();
    List<Comment> commentsToShow = null;
    
    private Integer issueID;
    private Integer id;
	
	// Maincontent, hierhin werden die verschiedenen Views geladen
	@FXML
	private Pane mainContent;
	
	@FXML
	private DatePicker issueDate_start;
	
	@FXML
	private DatePicker issueDate_end;

	@FXML
	private Label lbl_title;

	@FXML
	private TextField txt_mangleName;

	@FXML
	private TextField txt_issueId;
	
	@FXML
	private TextField txt_addComment;

	@FXML
	private TextField txt_projectName;

	@FXML
	private TextField txt_area;
	
	@FXML
	private TextField txt_commentAuthor;
	
	@FXML
	private TextField txt_commentDate;

	@FXML
	private TextArea txt_mangleDescription;

	@FXML
	private TextField txt_contactPerson;

	@FXML
	private TextField txt_principalPhone;

	@FXML
	private ComboBox<?> combo_principalName;

	@FXML
	private ComboBox<?> combo_status;

	@FXML
	private ComboBox<?> combo_subcontractor;

	@FXML
	private Button bttn_saveMangle;

	@FXML
	private Button bttn_quitMangle;

	@FXML
	private Button bttn_quitComment;

	@FXML
	private Button bttn_addComment;

	@FXML
	private Button bttn_addPhoto;

	@FXML
	private TableView<Comment> commentTableView;

	@FXML
	private TableColumn<Comment, String> colComment;

	@FXML
	private TableColumn<Comment, String> colAuthor;

	@FXML
	private TableColumn<Comment, String> colAdded;

	// Zur ProjectAdd-Ansicht wechseln (mainView.xml)
	@FXML
	private void backToProjectAddView(ActionEvent event) throws IOException {
		Pane myPane = FXMLLoader.load(getClass().getResource(
				"../view/ProjectAddView.fxml"));
		mainContent.getChildren().clear();
		mainContent.getChildren().setAll(myPane);

	}
		
	@FXML
	private void addComment(ActionEvent event) throws IOException {

		String comment = txt_addComment.getText();
		Comment c1 = new Comment();
		c1.setContent(comment);

	}

	public void initData(Issue issueToEdit) {
		lbl_title.setText("Benutzer bearbeiten");

		issueID = issueToEdit.getId();
		id = issueToEdit.getId();
		txt_issueId.setText(issueID.toString());

		if (issueToEdit.getDescription() != null) {
			txt_mangleName.setText(issueToEdit.getDescription());
		}

		if (issueToEdit.getProject() != null) {
			txt_projectName.setText(issueToEdit.getProject().getName());
		}
		
		if (issueToEdit.getCreated() != null) {
			Date b = new Date();
			Date a = issueToEdit.getCreated().getTime();
			long diff = a.getTime() - b.getTime();
			issueDate_start.setValue(LocalDate.now().plusDays(diff));
			
		}
		
		if (issueToEdit.getSolved() != null) {
			Date b = new Date();
			Date a = issueToEdit.getSolved().getTime();
			long diff = a.getTime() - b.getTime();
			issueDate_end.setValue(LocalDate.now().plusDays(diff));
		}
		
		//TODO Value von Object zu Combobox hinzufügen
//		if (issueToEdit.getState() != null) {
//			combo_status.setSelectedItem(issueToEdit.getState().getName());
//		}
//		
//		if (issueToEdit.getSubcontractor() != null) {
//			combo_subcontractor.setSelectedItem(issueToEdit.getSubcontractor());
//		}
//		
//		if (issueToEdit.getSubcontractor() != null) {
//			combo_subcontractor.setSelectedItem(issueToEdit.getSubcontractor());
//		}
//		if (issueToEdit.getProject().getContacts() != null) {
//			combo_principalName.setSelectedItem(issueToEdit.getProject().getContacts());
//		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txt_issueId.setEditable(false);
		getComment();

		// Spalten-Values definieren (müssen den Parameter des Mangel-Objekts
		// entsprechen)
		colComment.setCellValueFactory(new PropertyValueFactory<Comment, String>(
				"content"));
		colAuthor
				.setCellValueFactory(new PropertyValueFactory<Comment, String>(
						"person"));
		colAdded.setCellValueFactory(new PropertyValueFactory<Comment, String>(
						"created"));
		

		commentTableView.setItems(data);
	}
	
	/**
	 * Lädt alle Kommentare aus der Datenbank
	 */
	private void getComment() {

		Comment comment = new Comment();
		CommentProxy commentProxy = rtarget.proxy(CommentProxy.class);

		
		//ACHTUNG: Hier gibt es zwei: getByContact und getBySupervisor. Evtl muss man das noch anpassen.
		try {
			commentsToShow = mapper.readValue(commentProxy.getByContact(),
					new TypeReference<List<Issue>>() {
					});

			for (int i = 0; i < commentsToShow.size(); i++) {

				comment = commentsToShow.get(i);
				data.add(comment);

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		commentsToShow = null;

	}
}
