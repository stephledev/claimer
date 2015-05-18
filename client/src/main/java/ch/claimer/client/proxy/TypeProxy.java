package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Typen an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface TypeProxy {
	/**
	 * Holt alle Bautypen
	 * 
	 * @return String von den Bautypen
	 */
	@GET
	@Path("type")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt einen bestimmten Bautypen
	 * 
	 * @param id Identfikator eines bestimmten Bautypen
	 * @return String vom entsprechenden Bautypen
	 */
	@GET
	@Path("type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getType(@PathParam("id")int id);
	

}
