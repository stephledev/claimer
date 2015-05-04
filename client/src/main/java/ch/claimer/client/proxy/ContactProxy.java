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

public interface ContactProxy {
	
	@GET
	@Path("contact")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@GET
	@Path("contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor();
	
	@POST
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	String add();
	
	@PUT
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	String update();
}
