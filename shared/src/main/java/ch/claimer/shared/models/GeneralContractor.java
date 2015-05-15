package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Erbt von der Entit�t Firma. Repr�sentiert die Entit�t Generalunternehmer und
 * gew�hrleistet Zugriff auf die Eigenschaften der Klasse via Getter- und
 * Setter-Methoden.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class GeneralContractor extends Company {

	private static final long serialVersionUID = 1386743620569289562L;

	public GeneralContractor() {

	}

}
