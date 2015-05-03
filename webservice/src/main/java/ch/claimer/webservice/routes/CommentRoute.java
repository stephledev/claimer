package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die verf�gbaren HTTP-Routes der Projekte.
 * L�dt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zur�ck.
 * 
 * @author Raoul Ackermann
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 * 
 */
@Path("/")
public class CommentRoute {

	private Controller<Comment> controller;

	public CommentRoute() {
		this.controller = new Controller<Comment>(Comment.class);
	}

	/**
	 * Zeigt alle Kommentare eine Bauleiters an
	 * 
	 * @param id Identifikator des Bauleiters der anzuzeigenden Projeke
	 * 
	 * @return Response HTTP-Antwort mit Kommentaren
	 */
	@GET
	@RolesAllowed("editor-intern")
	@Path("comment/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySupervisor(@PathParam("id") int id) {
		return controller.showByRelation(Supervisor.class, id);
	}

	/**
	 * Zeigt den Controller um die Kommentare einer Ansprechsperson zu zeigen
	 *            
	 * @param id Identifikator der Ansprechperson der anzuzeigenden Kommentare
	 * 
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed("editor-extern")
	@Path("comment/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByRelation(Contact.class, id);
	}
}