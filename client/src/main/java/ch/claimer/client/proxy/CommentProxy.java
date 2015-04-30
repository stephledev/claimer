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

public interface CommentProxy {
	@GET
	@Path("/comment/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getSupervisorById();
	
	@GET
	@Path("/comment/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getContactById();
	
}
