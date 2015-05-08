package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Representiert die Entität Subunternehmer und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die SubcontractorSeed-Klasse die Eigenschaften.
 * Zudem erbt Subcontractor von der Company-Klasse.
 * Getter- und Settermethoden werden gesetzt.
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
