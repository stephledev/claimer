package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Image;
import ch.claimer.webservice.controller.DefaultController;


/**
 * Defines RESTful routes for image specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic
 */
@Path("/")
public class ImageRoute {
	
	private DefaultController<Image> controller;

	public ImageRoute() {
		this.controller = new DefaultController<Image>(Image.class);
	}
	
	/**
	 * Maps the controller to store the specified image instance
	 * 
	 * @param data data of the image instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("basic")
	@Path("image")
	public Response storeImage(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified image instance
	 * 
	 * @param data data of the image instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("basic")
	@Path("image")
	public Response updateImage(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified image instance
	 * 
	 * @param data data of the image instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("basic")
	@Path("image/{id}")
	public Response destroyImage(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}