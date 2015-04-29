package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der Bauleiter. Zeigt den Controller gemäss der URL-Pattern.
 * Diese Klasse wird gemäss dem Dokument "Rollen und Rechte" erstellt
 * 
 * @author Momcilo Bekcic
 */

@Path("/")
public class SupervisorRoute {	
	
	private Controller<Supervisor> controller;

	public SupervisorRoute() {
		this.controller = new Controller<Supervisor>(Supervisor.class);
	}
	
	/**
	 * 
	 * @param request
	 * @return Antwort vom Controller
	 */
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/supervisor") 
	public Response show(@Context HttpServletRequest request) {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id-Identifizierer um Angaben gemäss der URL zu liefern
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/supervisor/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}