package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Extends the base company, providing access to subcontractor
 * specific relations. Is also used to determine the permissions.
 * 
 * @author Stephan Beeler
 */

@Entity
public class Subcontractor extends Company {
	
	private static final long serialVersionUID = -3288367520438832164L;
	
	
	

	public Subcontractor() {

	}

}
