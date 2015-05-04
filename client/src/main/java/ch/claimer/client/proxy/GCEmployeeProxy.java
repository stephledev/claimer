package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.GCEmployee;

/**
 * 
 * @author Kevin Stadelmann
 * @since 1.0
 * @version 1.0
 *
 */

public interface GCEmployeeProxy {
	@GET
	@Path("gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@POST
	@Path("gcemployee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	void create();
	
	@PUT
	@Path("gcemployee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(GCEmployee a1);
	

}
