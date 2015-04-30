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

public interface CategoryProxy {
	@GET
	@Path("category")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
}
