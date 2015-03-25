package ch.claimer.shared.entities;

import javax.persistence.Entity;

/**
 * Extends the base person, providing access to  manager specific
 * relations. Is also used to determine the permissions.
 * 
 * @author Stephan Beeler
 */

@Entity
public class Manager extends Person {

	private static final long serialVersionUID = -8763273254775352447L;
	
	public Manager() {
		
	}
	
}
