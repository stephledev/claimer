package ch.claimer.webservice.routes;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.State;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Status.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Stephan Beeler
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */

@Path("/")
public class StateRoute {	
	
	private Controller<State> controller;

	public StateRoute() {
		this.controller = new Controller<State>(State.class);
	}
	/**
	 * Zeigt alle Status an
	 * 
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@PermitAll
	@Path("state")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten Status an
	 * 
	 * @param id Identifikator des anzuzeigenden Status
	 * @return Response HTTP-Antwort mit Status
	 */
	@GET
	@PermitAll
	@Path("state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}