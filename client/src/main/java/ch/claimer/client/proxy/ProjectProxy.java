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
	String getProjectById();
	
	@GET
	@Path("/project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getProjectSupervisorById();
	
	@GET
	@Path("/project/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getProjectCategoryById();
	
	@GET
	@Path("/project/state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getProjectStateById();
	
	@GET
	@Path("/project/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getProjectTypeById();
	
	@GET
	@Path("/project/logEntry/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getProjectLogEntryById();
}
