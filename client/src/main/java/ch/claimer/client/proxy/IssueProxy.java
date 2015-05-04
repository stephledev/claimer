package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Project;

/**
 * 
 * @author Kevin Stadelmann
 * @since 1.0
 * @version 1.0
 *
 */

public interface IssueProxy {
	
	@GET
	@Path("issue")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@GET
	@Path("issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByProject();
	
	@GET
	@Path("issue/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact();
	
	@GET
	@Path("issue/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor();
	
	@POST
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void add();
	
	@PUT
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void updateByProject(Project p1);
}
