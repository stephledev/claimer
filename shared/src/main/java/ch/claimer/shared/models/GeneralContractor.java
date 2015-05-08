package ch.claimer.shared.models;

import javax.persistence.Entity;

/**
 * Representiert die Entität Generalunternehmer und gewährleistet Zugriff auf die Eigenschaften 
 * der Klasse. 
 * Zudem erbt die Klasse von der Company-Klasse.
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
