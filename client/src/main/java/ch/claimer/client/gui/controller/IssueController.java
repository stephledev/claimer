package ch.claimer.client.gui.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CommentProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.PrincipalProxy;
import ch.claimer.client.proxy.StateProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Principal;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author Michael Lötscher, Alexander Hauck
 * @since 21.04.2015
 * @version 2.0
 *
 */

public class IssueController implements Initializable {
	
	Client client;
    WebTarget target;
    ResteasyWebTarget rtarget = ResteasyClientUtil.getTarget();
    ObjectMapper mapper =  new ObjectMapper();
    ObservableList<Comment> data = FXCollections.observableArrayList();
    List<Comment> commentsToShow = null;
    
    private Integer issueId;
    
    private Integer projectId;
    private Integer subcontractorId;
    private Integer contactId;

    
    
	@FXML
	private Pane mainContent;
	
	@FXML
	private DatePicker dateCreated;
	
	@FXML
	private DatePicker dateEnd;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txt_mangleName;
	
	@FXML
	private TextField txt_addComment;
	
	@FXML
	private TextField txt_area;
	
	@FXML
	private TextField txt_commentAuthor;
	
	@FXML
	private TextField txt_commentDate;

	@FXML
	private TextArea txtIssueDescription;

	@FXML
	private TextField txt_contactPerson;

	@FXML
	private TextField txt_principalPhone;

	@FXML
	private ComboBox<String> dropdownState;

	@FXML
	private Button btnSave;

	@FXML
	private Button bttn_quitComment;

	@FXML
	private Button bttn_addComment;

	@FXML
	private Button bttn_addPhoto;
	
	@FXML
	private Button bttn_export;

	@FXML
	private TableView<Comment> commentTableView;

	@FXML
	private TableColumn<Comment, String> colComment;

	@FXML
	private TableColumn<Comment, String> colAuthor;

	@FXML
	private TableColumn<Comment, String> colAdded;

	@FXML
	private ComboBox<String> dropdownSubcontractor;
	
	@FXML
	private ComboBox<String> dropdownContact;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		getComments();
		//setDropdownState();
		setDropdownSubcontractor();
		
		// Spalten-Values definieren
		colComment.setCellValueFactory(new PropertyValueFactory<Comment, String>("content"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Comment, String>("person"));
		colAdded.setCellValueFactory(new PropertyValueFactory<Comment, String>("created"));

		commentTableView.setItems(data);*/
	}

	
	/**
	 * Speichert einen Mangel.
	 */
	@FXML
	private void saveIssue() {
		Issue issue = new Issue();
		issue = getTextfieldProperties();
		
		if(issue != null) {
			ProjectAddController.dataTransfer.add(issue);
			ProjectAddController.dataTransfer.clear();
			closeStage();
		}
	}
	
	private Boolean checkLength(String text, int minLength, int maxLength) {
		if(text.length() < minLength || text.length() > maxLength) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Liest die Textfelder aus und validiert diese.
	 * @return
	 */
	private Issue getTextfieldProperties() {

		Issue issue = new Issue();
		Boolean validationError = false;
		
		String description = txtIssueDescription.getText();
		if(checkLength(description, 1, 255)) {
			validationError = true;
			txtIssueDescription.getStyleClass().add("txtError");
		} else {
			issue.setDescription(description);
		}
		
		//Subunternehmen dem Mangel zuweisen.
		if(dropdownSubcontractor.getValue() != null) { 
			try {
				SubcontractorProxy scProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);		
				ObjectMapper mapper = new ObjectMapper();	    
				List<Subcontractor> scList = mapper.readValue(scProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
				
				for(Subcontractor sc: scList) {
					if(sc.getName().equals(dropdownSubcontractor.getValue())) {
						issue.setSubcontractor(sc);
					}
			}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		} else {
			validationError = true;
			dropdownSubcontractor.getStyleClass().add("txtError");
		}
		
		//TODO Ansprechperson zuweisen.
		
		// Status dem Mangel zuweisen
		if(dropdownState.getValue() != null) {
			try {
				StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);			    
			    List<State> stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
			    
			    for(State state: stateList) {
					if(state.getName().equals(dropdownState.getValue()))
						issue.setState(state);	
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			validationError = true;
			dropdownState.getStyleClass().add("txtError");
		}
		
		//Startdatum generieren
		if(dateCreated.getValue() != null) {
			Integer dayCreated = dateCreated.getValue().getDayOfMonth();
		    Integer monthCreated = dateCreated.getValue().getMonthValue();
		    Integer yearCreated =  dateCreated.getValue().getYear();
		    
		    GregorianCalendar dateCreated = new GregorianCalendar();
		    dateCreated.set(yearCreated, monthCreated - 1, dayCreated);
		    issue.setCreated(dateCreated);
		} else {
			validationError = true;
			dateCreated.getStyleClass().add("txtError");
		}
	    
	    //Startdatum generieren
		if(dateEnd.getValue() != null) {
	  		Integer dayEnd = dateEnd.getValue().getDayOfMonth();
	  	    Integer monthEnd = dateEnd.getValue().getMonthValue();
	  	    Integer yearEnd =  dateEnd.getValue().getYear();
	  	    
	  	    GregorianCalendar dateEnd = new GregorianCalendar();
	  	    dateEnd.set(yearEnd, monthEnd - 1, dayEnd);
	  	    issue.setSolved(dateEnd);
		} else {
			validationError = true;
			dateEnd.getStyleClass().add("txtError");
		}
		
		if(validationError == false) {
			return issue;
		} else {
			return null;
		}
	}
		
	
	/**
	 * Schliesst das aktuelle Fenster.
	 */
	@FXML
	private void closeStage() {
		Stage stage = (Stage) btnSave.getScene().getWindow();
	    stage.close();
	}
	
	/**
	 * Befüllt die Textfelder mit den Daten des zu bearbeitenden Mangels.
	 * @param issueToEdit
	 */
	public void initData(Issue issueToEdit) {
		lblTitle.setText("Mangel bearbeiten.");	
/*
		subcontractorId = issueToEdit.getSubcontractor().getId();
		dropdownState.setValue(issueToEdit.getState().getName());	
		txtIssueDescription.setText(issueToEdit.getDescription());
		dropdownSubcontractor.setValue(issueToEdit.getSubcontractor().getName());
			System.out.println("hallo");
		System.out.println(issueToEdit.getSubcontractor().getId());
		
		System.out.println(issueToEdit.getSubcontractor().getName());
		
		//dateCreated.setValue(LocalDate.now().plusDays(diff));

		//dateEnd.setValue(LocalDate.now().plusDays(diff));
	*/	
	}
	

	
	/**
	 * Lädt alle Kommentare aus der Datenbank
	 */
	
	private void getComments() {

		Comment comment = new Comment();
		CommentProxy commentProxy = rtarget.proxy(CommentProxy.class);

		
		//ACHTUNG: Hier gibt es zwei: getByContact und getBySupervisor. Evtl muss man das noch anpassen.
		try {
			commentsToShow = mapper.readValue(commentProxy.getByContact(2),
					new TypeReference<List<Issue>>() {
					});

			for (int i = 0; i < commentsToShow.size(); i++) {

				comment = commentsToShow.get(i);
				data.add(comment);

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		commentsToShow = null;

	}
	
	
	/**
	 * Befüllt das "Status"-Dropdown mit den Inhalten aus der Datenbank.
	 */
	public void setDropdownState()  {

		StateProxy stateProxy = ResteasyClientUtil.getTarget().proxy(StateProxy.class);		
		ObjectMapper mapper = new ObjectMapper();	    
		List<State> stateList = null;

		try {
			stateList = mapper.readValue(stateProxy.getAll(), new TypeReference<List<State>>(){});
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Status dem Dropdown hinzufügen
		for(State state: stateList) {
			dropdownState.getItems().add(state.getName());
		}
	}

	/**
	 * Befüllt das "Subunternehmen"-Dropdown mit den Inhalten aus der Datenbank.
	 */
	public void setDropdownSubcontractor()  {

		try {
			SubcontractorProxy subcontractorProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);		
			List<Subcontractor> subcontractorList = mapper.readValue(subcontractorProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
			
			//Rollen dem Dropdown hinzufügen
			for(Subcontractor subcontractor: subcontractorList) {
				dropdownSubcontractor.getItems().add(subcontractor.getName());
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
	}

	
	// "Speicher"-Button: Speichert den Mangel
	@FXML
	private void saveComment() {

		Comment comment = new Comment();
		Issue issue = new Issue();

		//Textfeldproperties (inklusive Login & Rolle) auslesen und zuweisen
		comment = (Comment)  getIssueTextfieldProperties(issue, comment);

		CommentProxy commentProxy = ResteasyClientUtil.getTarget().proxy(CommentProxy.class);
		commentProxy.create(comment);


		//showIssueViewWithMessage(issue);
	}
	
	// liest Textfelder aus und speichert Daten des Projektes in der DB
	// Dropdown-Felder füllen
	private Comment getIssueTextfieldProperties(Issue i1, Comment c1) {
		issueId = i1.getId();
		i1.setComments(commentsToShow);
		Comment comment = new Comment();
		comment.setContent(txt_addComment.getText());
		commentsToShow.add(comment);
		
		return comment;
	}
	
	@FXML
	private void addComment(ActionEvent event) throws IOException {

		String comment = txt_addComment.getText();
		Comment c1 = new Comment();
		c1.setContent(comment);
	}
	
	@FXML
	public void export(ActionEvent e)  {
		try {
			writeExcel();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	public void writeExcel() throws Exception {
		Writer writer = null;
		ObservableList<Issue> data = FXCollections.observableArrayList(); 

		try {

			File file = new File("C:\\Mängel.csv.");
			writer = new BufferedWriter(new FileWriter(file));
			Issue issue = new Issue();
			IssueProxy issueProxy = rtarget.proxy(IssueProxy.class);


			List<Issue> issuesToShow = mapper.readValue(issueProxy.getAll(), new TypeReference<List<Issue>>(){});

			for(int i = 0; i < issuesToShow.size(); i++) {
				issue = issuesToShow.get(i);
				data.add(issue);
			}


		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {

			for (Issue issue : data) {
				String text = issue.getId() + "," + issue.getDescription()+ "," + issue.getProject().getName() + "," + issue.getCreated()+ "," + issue.getSolved() + "," + issue.getState()+ "\n";

				writer.write(text);
			}

			writer.flush();
			writer.close();
		} 
	}
		
}
		
		
