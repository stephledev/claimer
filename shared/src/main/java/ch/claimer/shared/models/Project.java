package ch.claimer.shared.models;

import java.io.Serializable;
import java.time.chrono.Chronology;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.SingleSelectionModel;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Stephan Beeler
 */

@Entity
public class Project implements Serializable, Model {
	
	private static final long serialVersionUID = -8619970776064713003L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@Temporal(TemporalType.TIMESTAMP)
	private Chronology start;
	@Temporal(TemporalType.TIMESTAMP)
	private Chronology end;
	private String street;
	private String zip;
	private String place;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<LogEntry> logEntries = new ArrayList<LogEntry>();
	@OneToOne(fetch=FetchType.EAGER)
	private SingleSelectionModel<Supervisor> supervisor;
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Contact> contacts = new ArrayList<Contact>();
	@OneToOne(fetch=FetchType.EAGER)
	private SingleSelectionModel<Category> category;
	@OneToOne(fetch=FetchType.EAGER)
	private SingleSelectionModel<Type> type;
	@OneToOne(fetch=FetchType.EAGER)
	private SingleSelectionModel<State> state;
	
	public Project() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Chronology getStart() {
		return start;
	}

	public void setStart(Chronology start) {
		this.start = start;
	}

	public Chronology getEnd() {
		return end;
	}

	public void setEnd(Chronology end) {
		this.end = end;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public List<LogEntry> getLogEntries() {
		return logEntries;
	}

	public void setLogEntries(List<LogEntry> logEntries) {
		this.logEntries = logEntries;
	}

	public SingleSelectionModel<Supervisor> getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(SingleSelectionModel<Supervisor> supervisor) {
		this.supervisor = supervisor;
	}

	public SingleSelectionModel<Category> getCategory() {
		return category;
	}

	public void setCategory(SingleSelectionModel<Category> category) {
		this.category = category;
	}

	public SingleSelectionModel<Type> getType() {
		return type;
	}

	public void setType(SingleSelectionModel<Type> type) {
		this.type = type;
	}

	public SingleSelectionModel<State> getState() {
		return state;
	}

	public void setState(SingleSelectionModel<State> state) {
		this.state = state;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	
	
}
