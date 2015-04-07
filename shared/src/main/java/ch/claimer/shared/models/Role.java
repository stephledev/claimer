package ch.claimer.shared.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Stephan Beeler
 */

@Entity
public class Role implements Serializable, Model {
	
	private static final long serialVersionUID = 5766207295876788122L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public Role() {
		
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
