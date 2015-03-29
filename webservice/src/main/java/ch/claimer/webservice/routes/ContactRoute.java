package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.ContactController;


/**
 * Defines RESTful routes for contact specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class ContactRoute {
	
	private ContactController controller;

	public ContactRoute() {
		this.controller = new ContactController();
	}
	
	/**
	 * Maps the controller to get a specific contact of a subcontractor
	 * 
	 * @param id subcontractor of a contact to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/contact/subcontractor/{id}")
	public Response showContactBySubcontractor(@PathParam("id") int id) {
		return controller.showBySubcontractor(id);
	} 
}