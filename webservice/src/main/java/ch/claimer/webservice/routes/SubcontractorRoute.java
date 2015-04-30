package ch.claimer.webservice.routes;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@PermitAll
	@Path("/subcontractor") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	
	/**
	 * Zeigt auf den Controller das Subunternehmen anzuzeigen
	 * 
	 * @param id Subunternehmen-Identifizierer der Kommentare um die, gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@PermitAll
	@Path("/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}