package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Representiert die Entität Mangel und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die IssueSeed-Klasse die Eigenschaften.
 * Getter- und Settermethoden werden gesetzt.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class Issue implements Serializable, Model {
	
	private static final long serialVersionUID = -8619970776064713003L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar created;
	@Temporal(TemporalType.TIMESTAMP)
	private GregorianCalendar solved;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Image> images;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<LogEntry> logEntries = new ArrayList<LogEntry>();
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<Comment> comments = new ArrayList<Comment>();
	@OneToOne
	private Project project;
	@OneToOne
	private State state;
	@OneToOne
	private Contact contact;
	@OneToOne
	private Subcontractor subcontractor;
	
	public Issue() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setCreated(GregorianCalendar created){
		this.created = created;
	}
	
	public GregorianCalendar getCreated() {
		return created;
	}

	public GregorianCalendar getSolved() {
		return solved;
	}

	public void setSolved(GregorianCalendar solved) {
		this.solved = solved;
	}

//	public List<Image> getImages() {
//		return images;
//	}
//
//	public void setImages(List<Image> images) {
//		this.images = images;
//	}

	public List<LogEntry> getLogEntries() {
		return logEntries;
	}

	public void setLogEntries(List<LogEntry> logEntries) {
		this.logEntries = logEntries;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Subcontractor getSubcontractor() {
		return subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

}
