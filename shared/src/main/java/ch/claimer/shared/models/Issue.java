package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 * @author Stephan Beeler
 */

@Entity
public class Issue implements Serializable, Model {
	
	private static final long serialVersionUID = -8619970776064713003L;
	
	@Id
	@GeneratedValue
	private int id;
	private String description;
	@Generated(value=GenerationTime.INSERT)
	@Temporal(TemporalType.DATE)
	private Date created;
	@Temporal(TemporalType.DATE)
	private Date solved;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Image> images;
	@OneToMany(cascade=CascadeType.ALL)
	private List<LogEntry> logEntries;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Comment> comments;
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
	
	public Date getCreated() {
		return created;
	}

	public Date getSolved() {
		return solved;
	}

	public void setSolved(Date solved) {
		this.solved = solved;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

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
