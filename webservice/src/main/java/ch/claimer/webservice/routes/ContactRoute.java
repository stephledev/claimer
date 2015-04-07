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

import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.controller.ContactController;


/**
 * Defines RESTful routes for contact specific interactions. 
 * Maps the controller according to the URL pattern
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
	@RolesAllowed("basic")
	@Path("/contact/subcontractor/{id}")
	public Response showContactBySubcontractor(@PathParam("id") int id) {
		return controller.showBySubcontractor(id);
	}
	
	/**
	 * Maps the controller to store the specified contact instance
	 * 
	 * @param data data of the contact instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("power")
	@Path("contact")
	public Response storeContact(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified contact instance
	 * 
	 * @param data data of the contact instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("power")
	@Path("contact")
	public Response updateContact(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified contact instance
	 * 
	 * @param data data of the contact instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("power")
	@Path("contact/{id}")
	public Response destroyContact(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}