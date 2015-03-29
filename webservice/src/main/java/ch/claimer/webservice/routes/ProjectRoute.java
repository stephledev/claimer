package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.ProjectController;


/**
 * Defines RESTful routes for project specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic 
 */
@Path("/")
public class ProjectRoute {
	
	private ProjectController controller;

	public ProjectRoute() {
		this.controller = new ProjectController();
	}
	
	/**
	 * Maps the controller to get a specific Project by its category
	 * 
	 * @param id project of a category to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
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
	 @Path("/project/gcemployee/{id}")
	 public Response showProjectsByGCEmployee(@PathParam("id") int id) {
	  return controller.showByGCEmployee(id);
	 } 
	
	
}