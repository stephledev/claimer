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
 * Defines RESTful routes for subcontractor specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Stephan Beeler
 */
@Path("/")
public class SubcontractorRoute {
	
	private DefaultController<SubcontractorRoute> controller;

	public SubcontractorRoute() {
		this.controller = new DefaultController<SubcontractorRoute>(SubcontractorRoute.class);
	}
	
	/**
	 * Maps the controller to store the specified subcontractor instance
	 * 
	 * @param data data of the subcontractor instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("intern")
	@Path("scemployee")
	public Response storeSubcontractor(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified subcontractor instance
	 * 
	 * @param data data of the subcontractor instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("intern")
	@Path("scemployee")
	public Response updateSubcontractor(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified subcontractor instance
	 * 
	 * @param data data of the subcontractor instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("intern")
	@Path("scemployee/{id}")
	public Response destroySubcontractor(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}