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
	private Date created;
	private Date solved;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Image> images;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Comment> comments;
	@OneToMany(cascade=CascadeType.ALL)
	private List<LogEntry> logEntries;
	@OneToOne
	private State state;
	@OneToOne
	private Supervisor supervisor;
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

	public void setCreated(Date created) {
		this.created = created;
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
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