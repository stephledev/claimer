package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Definiert die Proxies der Rollen
 * 
 * @author Momcilo Bekcic
 * @since 1.0
 * @version 1.0
 *
 */

public interface RoleProxy {
	@GET
	@Path("category")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();	
}
