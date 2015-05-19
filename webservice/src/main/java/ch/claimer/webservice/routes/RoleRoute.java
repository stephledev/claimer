package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Role;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Rollen.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methoden.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */

@Path("/")
public class RoleRoute {	
	
	private Controller<Role> controller;

	public RoleRoute() {
		this.controller = new Controller<Role>(Role.class);
	}
	
	/**
	 * Zeigt alle Rollen an
	 * 
	 * @return Response HTTP-Antwort mit Rollen
	 */
	@GET
	@RolesAllowed("power")
	@Path("role") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
}