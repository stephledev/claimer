package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Subcontractor;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes des Subunternehmens.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 */

@Path("/")
public class SubcontractorRoute {	
	
	private Controller<Subcontractor> controller;

	public SubcontractorRoute() {
		this.controller = new Controller<Subcontractor>(Subcontractor.class);
	}
	
	
	/**
	 * Zeigt auf den Controller das Subunternehmen anzuzeigen
	 * 
	 * @param id Subunternehmen-Identifizierer der Kommentare um die, gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/subcontractor/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}