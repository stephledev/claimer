package ch.claimer.shared.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Representiert die Entität SCEmployee und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die SCEmployeeSeed-Klasse die Eigenschaften.
 * Zudem erbt SCEmployee von der Klasse Person.
 * Getter- und Settermethoden werden gesetzt.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
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
