package ch.claimer.shared.entities;

import javax.persistence.Entity;

/**
 * Extends the base company, providing access to general contractor
 * specific relations. Is also used to determine the permissions.
 * 
 * @author Stephan Beeler
 */

@Entity
public class GeneralContractor extends Company {
	
	private static final long serialVersionUID = 1386743620569289562L;

	public GeneralContractor() {
		
	}

}
