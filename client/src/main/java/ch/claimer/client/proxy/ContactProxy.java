package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Contact;

/**
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
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
	String getById(@PathParam("id")int id);
	
	@GET
	@Path("contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor(@PathParam("id")int id);
	
	@POST
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Contact contact);
	
	@PUT
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Contact contact);
}
