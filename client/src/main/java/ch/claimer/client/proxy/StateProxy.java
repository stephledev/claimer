package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface StateProxy {
	@GET
	@Path("state")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	

}
