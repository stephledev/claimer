package ch.claimer.webservice.routes;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Comment;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die RESTfull Routes der Kommentare. Maps the controller
 * according to the URL pattern
 * 
 * @author Raoul Ackermann / Momcilo Bekcic
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
		return controller.showByProperty("person_id", id);
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
	@Path("/comment/contact/{id}")
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}
}