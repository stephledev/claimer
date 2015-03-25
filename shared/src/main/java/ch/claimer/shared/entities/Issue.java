package ch.claimer.shared.entities;

import java.util.Date;

import javax.persistence.Entity;

/**
 * @author Stephan Beeler
 */

@Entity
public class Issue {

	private static final long serialVersionUID = -8763273254775352447L;
	
	private int id;
	private String description;
	private Date created;
	private Date solved;
	private State state;
	
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
	
}
