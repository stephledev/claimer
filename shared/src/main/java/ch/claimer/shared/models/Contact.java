package ch.claimer.shared.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Representiert die Entität Kontakt und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die ContactSeed-Klasse die Eigenschaften.
 * Getter- und Settermethoden werden gesetzt.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class Contact extends Person {

	private static final long serialVersionUID = -8763273254775352447L;
	
	@ManyToOne
	private Subcontractor subcontractor;
	
	public Contact() {
		
	}

	public Subcontractor getSubcontractor() {
		return subcontractor;
	}

	public void setSubcontractor(Subcontractor subcontractor) {
		this.subcontractor = subcontractor;
	}

}
