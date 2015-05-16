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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import ch.claimer.client.proxy.CommentProxy;
import ch.claimer.client.proxy.ContactProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.StateProxy;
import ch.claimer.client.proxy.SubcontractorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private List<Subcontractor> subcontractorList = null;

	@FXML
	private Pane mainContent;
	
	@FXML
	private DatePicker dateCreated;
	
	@FXML
	private DatePicker dateEnd;

	@FXML
	private Label lblTitle;

	@FXML
	private TextField txtComment;

	@FXML
	private TextArea txtIssueDescription;

	@FXML
	private ComboBox<String> dropdownState;

	@FXML
	private Button btnSave;

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
		
		getComments();
		setDropdownState();
		setDropdownSubcontractor();
		
		//Listener,um Änderungen am Subunternehmen-Dropdown zu überprüfen.
		dropdownSubcontractor.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String oldValue, String newValue) {
				dropdownContact.getItems().clear();
				dropdownContact.setValue("");
				for(Subcontractor sc : subcontractorList) {
					if(sc.getName().equals(newValue)) {
						setDropdownContact(sc.getId());
					}
				}
			}
		 });
		
		// Spalten-Values definieren
		colComment.setCellValueFactory(new PropertyValueFactory<Comment, String>("content"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<Comment, String>("person"));
		colAdded.setCellValueFactory(new PropertyValueFactory<Comment, String>("created"));

		commentTableView.setItems(data);
	}
	
	/**
	 * Befüllt die Textfelder mit den Daten des zu bearbeitenden Mangels.
	 * @param issueToEdit
	 */
	public void initData(Issue issueToEdit) {
		issueId = issueToEdit.getId();
		lblTitle.setText("Mangel bearbeiten.");	

		dropdownState.setValue(issueToEdit.getState().getName());	
		txtIssueDescription.setText(issueToEdit.getDescription());
		dropdownSubcontractor.setValue(issueToEdit.getSubcontractor().getName());
		dropdownContact.setValue(issueToEdit.getContact().getLastname() + ", " + issueToEdit.getContact().getFirstname());
		
		long timeStart;
		long timeEnd;
		long days;
		Date date = new Date();
		long timenow = date.getTime();
		long daysnow = Math.round( (double)timenow / (24. * 60.*60.*1000.));
		long diff;
			
		//Erstellungsdatum
		timeStart = issueToEdit.getCreated().getTime().getTime();
		days = Math.round( (double)timeStart / (24. * 60.*60.*1000.));
		diff = days - daysnow;
		dateCreated.setValue(LocalDate.now().plusDays(diff));
		
		//Enddatum
		timeEnd = issueToEdit.getSolved().getTime().getTime();
		days = Math.round( (double)timeEnd / (24. * 60.*60.*1000.));
		diff = days - daysnow;
		dateEnd.setValue(LocalDate.now().plusDays(diff));
	
	}

	
	/**
	 * Speichert einen Mangel.
	 */
	@FXML
	private void saveIssue() {
		 Issue issue = getTextfieldProperties();
		
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
		
		if(issueId != null) {
			issue.setId(issueId);
		}
		
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
		if(dropdownContact.getValue() != null) {
			
			String contactName = dropdownContact.getValue();
			String[] parts = contactName.split(",");
			String lastname = parts[0];
			String firstname = parts[1].substring(1);
			
			try {
				ContactProxy cProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);			    
			    List<Contact> contactList = mapper.readValue(cProxy.getAll(), new TypeReference<List<Contact>>(){});
			    
			    for(Contact contact: contactList) {
					if(contact.getFirstname().equals(firstname) && contact.getLastname().equals(lastname))
						issue.setContact(contact);;	
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			validationError = true;
			dropdownContact.getStyleClass().add("txtError");
		}
		
		
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
	private void setDropdownSubcontractor()  {

		try {
			SubcontractorProxy subcontractorProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);		
			subcontractorList = mapper.readValue(subcontractorProxy.getAll(), new TypeReference<List<Subcontractor>>(){});
			
			for(Subcontractor subcontractor: subcontractorList) {
				dropdownSubcontractor.getItems().add(subcontractor.getName());
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
	}
	
	/**
	 * Befüllt das "
	 */
	private void setDropdownContact(Integer subcontractorId) {
		
		
		try {
			ContactProxy cProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);		
			List<Contact> contactList = mapper.readValue(cProxy.getBySubcontractor(subcontractorId), new TypeReference<List<Contact>>(){});
			
			
			for(Contact contact: contactList) {
				dropdownContact.getItems().add(contact.getLastname() + ", " + contact.getFirstname());
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	
	
	// "Speicher"-Button: Speichert den Mangel
	@FXML
	private void saveComment() {
		
	}
	

	// liest Textfelder aus und speichert Daten des Projektes in der DB
	// Dropdown-Felder füllen
	private Comment getIssueTextfieldProperties(Issue i1, Comment c1) {

		return null;
	}
	
	@FXML
	private void addComment(ActionEvent event) throws IOException {

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
		
		
