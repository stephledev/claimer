package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.GCEmployee;

/**
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface GCEmployeeProxy {
	@GET
	@Path("gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	@POST
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(GCEmployee gcEmployee);
	
	@PUT
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(GCEmployee gcEmployee);
	

}
