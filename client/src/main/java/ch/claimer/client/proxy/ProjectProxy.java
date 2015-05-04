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
	
	@GET
	@Path("project/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByCategory();
	
	@GET
	@Path("project/state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByState();
	
	@GET
	@Path("project/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByType();
	
	@GET
	@Path("project/logEntry/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getLogEntry();
	
    @POST
    @Path("project/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void create();
    
    @PUT
    @Path("project/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Project pr);


	
}
