package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Type;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der Kommentare.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 * 
 */

@Path("/")
public class TypeRoute {	
	
	private Controller<Type> controller;

	public TypeRoute() {
		this.controller = new Controller<Type>(Type.class);
	}
	
	/**
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/type") 
	public Response show() {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id-Identifizierer der Angaben gemäss URL liefert
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/type/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}