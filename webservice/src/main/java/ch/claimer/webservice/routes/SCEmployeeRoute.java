package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;



import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der Kommentare.
 * Zeigt den "Controller" gem�ss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 * 
 */

@Path("/")
public class SCEmployeeRoute {	
	
	private Controller<SCEmployee> controller;

	public SCEmployeeRoute() {
		this.controller = new Controller<SCEmployee>(SCEmployee.class);
	}
	
	
	/**
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/scEmployee") 
	public Response show() {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id-Identifizeirer um die -von der URL unterst�tzten- anzuzeigen
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/scEmployee/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}