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

public interface StateProxy {
	@GET
	@Path("/state")
	@Produces(MediaType.APPLICATION_JSON)
	String getStateAll();
	
	@GET
	@Path("/state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getStateById();
	

}