package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Erbt von der Entität Person. Repräsentiert die Entität Sachbearbeiter
 * Generalunternehmung und gewährleistet Zugriff auf die Eigenschaften der
 * Klasse via Getter- und Setter-Methoden.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class GCEmployee extends Person {

	private static final long serialVersionUID = -8763273254775352447L;

	public GCEmployee() {

	}

}
