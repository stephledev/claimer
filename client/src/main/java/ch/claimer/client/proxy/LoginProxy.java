package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Kevin Stadelmann
 * @since 1.0
 * @version 1.0
 *
 */

public interface LoginProxy {
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	void add();
	
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	void update();
}
