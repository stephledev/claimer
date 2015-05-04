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
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 *
 */

public interface ProjectProxy {
	
	@GET
	@Path("project")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@GET
	@Path("project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySupervisor();
	
    @POST
    @Path("project")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Project project);
    
    @PUT
    @Path("project")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Project project);
}
