package ch.claimer.shared.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class LogEntry implements Serializable, Model {
	
	private static final long serialVersionUID = 7471174781773890597L;
	
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	private String description;

	public LogEntry() {
		
	}

	public int getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
