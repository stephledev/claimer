package ch.claimer.shared.entities;

import javax.persistence.Entity;

/**
 * @author Stephan Beeler
 */

@Entity
public class Category {

	private static final long serialVersionUID = -8763273254775352447L;
	
	private int id;
	private String name;
	
	public Category() {
		
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
	
}
