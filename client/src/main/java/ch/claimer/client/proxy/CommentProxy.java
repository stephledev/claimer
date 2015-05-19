package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface CommentProxy {
	
	/**
	 * Holt eine bestimmter Kommentar
	 * 
	 * @param id Identifikator einer Supervisors
	 * @return String des Kommentars
	 */
	@GET
	@Path("comment/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySupervisor(@PathParam("id")int id);
	
	/**
	 * Holt eine bestimmte Kategorie
	 * 
	 * @param id Identifikator eines Kontakts
	 * @return String des Kommentars
	 */
	
	@GET
	@Path("comment/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact(@PathParam("id")int id);
	
	/**
	 * Holt eine bestimmte Kommentare von einem Mangel
	 * 
	 * @param id Identifikator eines bestimmten Mangels
	 * @return String des Kommentars
	 */
	
	@GET
	@Path("comment/issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByIssue(@PathParam("id")int id);
	
}
