package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.controller.ContactController;


/**
 * Defines RESTful routes for contact specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class ContactRoute {
	
	private ContactController<Contact> controller;

	public ContactRoute() {
		this.controller = new ContactController<Contact>();
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