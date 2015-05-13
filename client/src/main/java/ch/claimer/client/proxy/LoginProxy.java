package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Login;

/**
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
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
	void create(Login login);
	
	@POST
	@Path("check")
	@Consumes(MediaType.APPLICATION_JSON)
	String check(Login login);
	
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Login login);
}
