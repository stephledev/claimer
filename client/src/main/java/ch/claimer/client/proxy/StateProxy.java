package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Status an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface StateProxy {
	/**
	 * Holt alle bestehenden Status
	 * 
	 * @return String von Status
	 */
	@GET
	@Path("state")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt einen bestimmten Status
	 * 
	 * @param id Identifikator eines bestimmten Status'
	 * @return String des Statuses
	 */
	@GET
	@Path("state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	

}
