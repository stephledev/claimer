package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Rollen an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Momcilo Bekcic
 * @since 1.0
 * @version 1.0
 *
 */

public interface RoleProxy {
	/**
	 * Holt alle Rollen
	 * 
	 * @return String von Rollen
	 */
	@GET
	@Path("role")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();	
}
