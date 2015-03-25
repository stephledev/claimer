package ch.claimer.shared.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Stephan Beeler
 */

@Entity
public class Type {

	private static final long serialVersionUID = -8763273254775352447L;
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	
	public Type() {
		
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
