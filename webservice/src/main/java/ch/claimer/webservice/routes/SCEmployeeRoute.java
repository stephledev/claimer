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

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.controller.SCEmployeeController;


/**
 * Defines RESTful routes for subcontractor employee specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic 
 */
@Path("/")
public class SCEmployeeRoute {
	
	private SCEmployeeController<SCEmployee> controller;

	public SCEmployeeRoute() {
		this.controller = new SCEmployeeController<SCEmployee>();
	}
	
	/**
	 * Maps the controller to get a specific subcontractor employee by his subcontractor
	 * 
	 * @param id subcontractor of the subcontractor employee to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
	@Path("/scemployee/subcontractor/{id}")
	public Response showSCEmployeesBySubcontractor(@PathParam("id") int id) {
		return controller.show(id);
	} 
	
	/**
	 * Maps the controller to store the specified subcontractor employee instance
	 * 
	 * @param data data of the subcontractor employee instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("power")
	@Path("scemployee")
	public Response storeSCEmployee(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified subcontractor employee instance
	 * 
	 * @param data data of the subcontractor employee instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("power")
	@Path("scemployee")
	public Response updateSCEmployee(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified subcontractor employee instance
	 * 
	 * @param data data of the subcontractor employee instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("power")
	@Path("scemployee/{id}")
	public Response destroySCEmployee(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}