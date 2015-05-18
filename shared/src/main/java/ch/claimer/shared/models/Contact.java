package ch.claimer.shared.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Erbt von der Entität Person. Repräsentiert die Entität Kontakt und
 * gewährleistet Zugriff auf die Eigenschaften der Klasse via Getter- und
 * Setter-Methoden.
 * 
 * @author Stephan Beeler
 * @author Fabio Baviera
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
