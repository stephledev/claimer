package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Kevin Stadelmann
 *
 */

public interface ProjectProxy {
	@GET
	@Path("/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@GET
	@Path("project")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("/project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySupervisor();
	
	@GET
	@Path("/project/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByCategory();
	
	@GET
	@Path("/project/state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByState();
	
	@GET
	@Path("/project/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByType();
	
	@GET
	@Path("/project/logEntry/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getLogEntry();

	
}
