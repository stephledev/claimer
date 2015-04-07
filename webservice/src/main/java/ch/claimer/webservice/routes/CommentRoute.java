package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Comment;
import ch.claimer.webservice.controller.DefaultController;


/**
 * Defines RESTful routes for comment specific interactions. 
 * Maps the controller according to the URL pattern
 * 
 * @author Stephan Beeler
 */
@Path("/")
public class CommentRoute {
	
	private DefaultController<Comment> controller;

	public CommentRoute() {
		this.controller = new DefaultController<Comment>(Comment.class);
	}
	
	/**
	 * Maps the controller to store the specified comment instance
	 * 
	 * @param data data of the comment instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("basic")
	@Path("comment")
	public Response storeComment(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified comment instance
	 * 
	 * @param data data of the comment instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("basic")
	@Path("comment")
	public Response updateComment(@FormParam("data") String data) {
		return controller.update(data);
	} 
}