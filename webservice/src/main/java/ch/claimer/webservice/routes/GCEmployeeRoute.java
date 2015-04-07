package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.webservice.controller.DefaultController;


/**
 * Defines RESTful routes for general constructor employee specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class GCEmployeeRoute {
	
	private DefaultController<GCEmployee> controller;

	public GCEmployeeRoute() {
		this.controller = new DefaultController<GCEmployee>(GCEmployee.class);
	}
	
	/**
	 * Maps the controller to store the specified general constructor employee instance
	 * 
	 * @param data data of the general constructor employee instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("admin")
	@Path("gcemployee")
	public Response storeGCEmployee(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified general constructor employee instance
	 * 
	 * @param data data of the general constructor employee instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("admin")
	@Path("gcemployee")
	public Response updateGCEmployee(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified general constructor employee instance
	 * 
	 * @param data data of the general constructor employee instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("admin")
	@Path("gcemployee/{id}")
	public Response destroyGCEmployee(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}