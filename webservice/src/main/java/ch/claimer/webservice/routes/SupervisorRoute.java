package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.webservice.controller.DefaultController;

/**
 * Defines RESTful routes for supervisor specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Stephan Beeler
 */
@Path("/")
public class SupervisorRoute {
	
	private DefaultController<SupervisorRoute> controller;

	public SupervisorRoute() {
		this.controller = new DefaultController<SupervisorRoute>(SupervisorRoute.class);
	}
	
	/**
	 * Maps the controller to store the specified supervisor instance
	 * 
	 * @param data data of the supervisor instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("admin")
	@Path("supervisor")
	public Response storeSupervisor(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified supervisor instance
	 * 
	 * @param data data of the supervisor instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("admin")
	@Path("supervisor")
	public Response updateSupervisor(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified supervisor instance
	 * 
	 * @param data data of the supervisor instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("admin")
	@Path("supervisor/{id}")
	public Response destroySupervisor(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}