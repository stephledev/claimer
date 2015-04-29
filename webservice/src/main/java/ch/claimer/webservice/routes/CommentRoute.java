package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Comment;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der Kommentare.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Raoul Ackermann
 * @author Momcilo Bekcic
 * 
 */
@Path("/")
public class CommentRoute {

	private Controller<Comment> controller;

	public CommentRoute() {
		this.controller = new Controller<Comment>(Comment.class);
	}

	/**
	 * Zeigt auf den Controller um die Kommentare eines Bauleiters anzuzeigen
	 * 
	 * @param id Bauleiter-Identifizierer der Kommentare um die, von der URL, 
	 * unterstützen anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/comment/supervisor/{id}")
	public Response showBySupervisor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}

	/**
	 * Zeigt den Controller um die Kommentare einer Ansprechsperson zu zeigen
	 *            
	 * @param id Kontakt-Identifizierer eines Kommentars um die, von der URL unterstützten,
	 * anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "extern"})
	@Path("/comment/contact/{id}")
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}
}