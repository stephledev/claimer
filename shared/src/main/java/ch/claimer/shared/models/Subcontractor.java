package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Erbt von der Entität Firma. Repräsentiert die Entität Subunternehmer und
 * gewährleistet Zugriff auf die Eigenschaften der Klasse via Getter- und
 * Setter-Methoden.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class Subcontractor extends Company {

	private static final long serialVersionUID = -3288367520438832164L;

	public Subcontractor() {

	}

}
