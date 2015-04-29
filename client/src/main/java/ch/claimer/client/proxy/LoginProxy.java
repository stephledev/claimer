package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public interface LoginProxy {
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
}
