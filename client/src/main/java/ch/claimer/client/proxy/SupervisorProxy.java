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

public interface SupervisorProxy {
	@GET
	@Path("/supervisor")
	@Produces(MediaType.APPLICATION_JSON)
	String getSupervisorAll();
	
	@GET
	@Path("/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getSupervisorById();
	

}
