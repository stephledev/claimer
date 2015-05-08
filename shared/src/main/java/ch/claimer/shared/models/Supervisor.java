package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Representiert die Entität Supervisor und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die SupervisorSeed-Klasse die Eigenschaften.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */

@Entity
public class Supervisor extends Person {

	private static final long serialVersionUID = -8763273254775352447L;
	
	public Supervisor() {
		
	}
	
}
