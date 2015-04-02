package ch.claimer.shared.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Extends the base person, providing access to subcontractor
 * employee specific relations. Is also used to determine the 
 * permissions.
 * 
 * @author Stephan Beeler
 */

@Entity
public class SCEmployee extends Person {

	private static final long serialVersionUID = -8763273254775352447L;
	
	@ManyToOne
	private Subcontractor subcontractor;
	
	public SCEmployee() {
		
	}

	public Subcontractor getSubcontractor() {
		return subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}	
}
