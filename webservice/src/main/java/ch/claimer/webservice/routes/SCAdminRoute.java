package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.SCAdminController;


/**
 * Defines RESTful routes for SCAdmin specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class SCAdminRoute {
	
	private SCAdminController controller;

	public SCAdminRoute() {
		this.controller = new SCAdminController();
	}
	
	/**
	 * Maps the controller to get a specific subcontractor admin of a subcontractor
	 * 
	 * @param id subcontractor admin of a subcontractor to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/scadmin/subcontractor/{id}")
	public Response showSCAdminBySubcontractor(@PathParam("id") int id) {
		return controller.showBySubcontractor(id);
	} 
}