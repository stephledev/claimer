package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Kevin Stadelmann
 *
 */

public interface TypeProxy {
	@GET
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getType();
	

}
