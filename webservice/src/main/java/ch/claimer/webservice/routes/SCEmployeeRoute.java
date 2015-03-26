package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.SCEmployeeController;


/**
 * Defines RESTful routes for subcontractor employee specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic
 * @author Raoul Ackermann
 */
@Path("/")
public class SCEmployeeRoute {
	
	private SCEmployeeController controller;

	public SCEmployeeRoute() {
		this.controller = new SCEmployeeController();
	}
	
	/**
	 * Maps the controller to get a specific subcontractor employee by his subcontractor
	 * 
	 * @param id subcontractor of the subcontractor employee to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/scemployee/subcontractor/{id}")
	public Response showSCEmployeeBySubcontractor(@PathParam("id") int id) {
		return controller.show(id);
	} 
}