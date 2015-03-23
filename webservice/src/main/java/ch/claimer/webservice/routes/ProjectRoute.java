package ch.claimer.webservice.routes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.ProjectController;
import ch.claimer.webservice.injector.ProjectInjector;
 
@Path("/")
public class ProjectRoute {
	
	Injector injector = Guice.createInjector(new ProjectInjector());
	ProjectController pc = injector.getInstance(ProjectController.class);
 
    @GET
	@Path("/project")
	public Response getAllProjects() {
		return pc.index();
	} 
    
	@GET
	@Path("/project/{id}")
	public Response getProjectById(@PathParam("id") int id) {
		return pc.show(id);
	}
	
	@GET
	@Path("/project/manager/{id}")
	public Response getProjectByManager(@PathParam("id") int id) {
		return pc.getByManager(id);
	}
	
	@POST
	@Path("/project")
	public Response storeProject(@FormParam("data") String data) {
		return pc.store(data);
	} 
	
	@PUT
	@Path("/project")
	public Response updateProject(@FormParam("data") String data) {
		return pc.update(data);
	} 
	
	@DELETE
	@Path("/project")
	public Response destroyProject(@FormParam("data") String data) {
		return pc.destroy(data);
	} 
	
	
	
	
 
}