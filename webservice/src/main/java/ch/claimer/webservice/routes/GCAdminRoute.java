package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.GCAdminController;


/**
 * Defines RESTful routes for GCAdmin specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class GCAdminRoute {
	
	private GCAdminController controller;

	public GCAdminRoute() {
		this.controller = new GCAdminController();
	}
	
	/**
	 * Maps the controller to get a specific general contractor admin of a general contractor employee
	 * 
	 * @param id general contractor employee of a general contractor admin to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/gcadmin/gcemployee/{id}")
	public Response showSCAdminByGCEmployee(@PathParam("id") int id) {
		return controller.showByGCEmployee(id);
	} 
}