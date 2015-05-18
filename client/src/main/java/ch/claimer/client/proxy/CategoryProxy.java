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
 *
 *
 */

	
public interface CategoryProxy {
	/**
	 * Holt alle Kategorien
	 * 
	 * @return String von Projekten
	 */
	
	@GET
	@Path("category")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt eine bestimmte Kategorie
	 * 
	 * @param id Identifikator einer bestimmten Kategorie
	 * @return String der Kategorie
	 */
	
	@GET
	@Path("category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
}
