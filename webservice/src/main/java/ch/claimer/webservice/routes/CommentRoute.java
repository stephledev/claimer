package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.CommentController;


/**
 * Defines RESTful routes for comment specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic
 */
@Path("/")
public class CommentRoute {
	
	private CommentController controller;

	public CommentRoute() {
		this.controller = new CommentController();
	}
	
	/**
	 * Maps the controller to get a specific comment by his issue
	 * 
	 * @param id issue of the comment to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/comment/issue/{id}")
	public Response showCommentsByIssue(@PathParam("id") int id) {
		return controller.showByIssue(id);
	} 
}