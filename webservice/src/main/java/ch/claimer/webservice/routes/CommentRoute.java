package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Comment;
import ch.claimer.webservice.controller.Controller;
import ch.claimer.webservice.services.RouteResponseHandlerService;

/**
 * Defines RESTful routes for comment specific interactions. Maps the controller
 * according to the URL pattern
 * 
 * @author Stephan Beeler
 */
@Path("/")
public class CommentRoute {

	private Controller<Comment> controller;

	public CommentRoute() {
		this.controller = new Controller<Comment>(Comment.class);
	}

	/**
	 * Maps the controller to show comment(s) of a supervisor
	 * 
	 * @param id
	 *            supervisor identifier of comment(s) to show supplied by the
	 *            URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/comment/supervisor/{id}")
	public Response showBySupervisor(@PathParam("id") int id) {
		return controller.showBySupervisor(id);
	}

	/**
	 * Maps the controller to show comment(s) of a contact
	 * 
	 * @param id
	 *            contact identifier of comment(s) to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed({ "EDITOR", "EXTERN" })
	@Path("/comment/contact/{id}")
	public Response showCommentsByContact(@PathParam("id") int id) {
		return controller.showByContact(id);
	}
}