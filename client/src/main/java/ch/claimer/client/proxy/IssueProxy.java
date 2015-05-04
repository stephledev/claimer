package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Issue;

/**
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface IssueProxy {
	
	@GET
	@Path("issue")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	@GET
	@Path("issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByProject(@PathParam("id")int id);
	
	@GET
	@Path("issue/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact(@PathParam("id")int id);
	
	@GET
	@Path("issue/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor(@PathParam("id")int id);
	
	@POST
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Issue issue);
	
	@PUT
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Issue issue);
}
