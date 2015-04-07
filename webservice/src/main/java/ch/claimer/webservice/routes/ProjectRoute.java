package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Project;
import ch.claimer.webservice.controller.ProjectController;


/**
 * Defines RESTful routes for project specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic 
 */
@Path("/")
public class ProjectRoute {
	
	private ProjectController<Project> controller;

	public ProjectRoute() {
		this.controller = new ProjectController<Project>();
	}
	
	/**
	 * Maps the controller to get a specific Project by its category
	 * 
	 * @param id project of a category to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
	@Path("/project/category/{id}")
	public Response showProjectsByCategory(@PathParam("id") int id) {
		return controller.showByCategory(id);
		
	}
	
	/**
	 * Maps the controller to get a specific project by his type
	 * 
	 * @param id project of the type to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
	@Path("/project/type/{id}")
	public Response showProjectsByType(@PathParam("id") int id) {
		return controller.showByType(id);
	}
	
	/**
	 * Maps the controller to get a specific project by his supervisor
	 * 
	 * @param id project of the supervisor to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
	@Path("/project/supervisor/{id}")
	public Response showProjectsBySupervisor(@PathParam("id") int id) {
		return controller.showBySupervisor(id);
	}
	
	/**
	 * Maps the controller to get a specific project by his state
	 * 
	 * @param id project of the state to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
	@Path("/project/state/{id}")
	public Response showProjectsByState(@PathParam("id") int id) {
		return controller.showByState(id);
	} 
	
	 /**
	  * Maps the controller to get a specific project by his general constructor employee
	  * 
	  * @param id project of the general constructor employee to show supplied by the URL
	  * 
	  * @return Response from the controller
	  */
	 @GET
	 @RolesAllowed("basic")
	 @Path("/project/gcemployee/{id}")
	 public Response showProjectsByGCEmployee(@PathParam("id") int id) {
	  return controller.showByGCEmployee(id);
	 } 
	 
	 /**
	 * Maps the controller to store the specified project instance
	 * 
	 * @param data data of the project instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("intern")
	@Path("project")
	public Response storeProject(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified project instance
	 * 
	 * @param data data of the project instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("intern")
	@Path("project")
	public Response updateProject(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified project instance
	 * 
	 * @param data data of the project instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("intern")
	@Path("project/{id}")
	public Response destroyProject(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
	
}