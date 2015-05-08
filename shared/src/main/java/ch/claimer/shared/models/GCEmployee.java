package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Extends the base person, providing access to general contractor
 * employee specific relations. Is also used to determine the 
 * permissions.
 * 
 * @author Stephan Beeler
 */
/**
 * Representiert die Entität GCEmployee und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. Beispielsweise benutzt die GCEmployeeSeed-Klasse die Eigenschaften.
 * GCEmployee erbt von der Klasse Person.
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
