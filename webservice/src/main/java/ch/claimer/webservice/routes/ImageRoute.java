package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.ImageController;


/**
 * Defines RESTful routes for image specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class ImageRoute {
	
	private ImageController controller;

	public ImageRoute() {
		this.controller = new ImageController();
	}
	
	/**
	 * Maps the controller to get a specific contact of a subcontractor
	 * 
	 * @param id subcontractor of a contact to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/image/issue/{id}")
	public Response showImageByIssue(@PathParam("id") int id) {
		return controller.showByIssue(id);
	} 
}