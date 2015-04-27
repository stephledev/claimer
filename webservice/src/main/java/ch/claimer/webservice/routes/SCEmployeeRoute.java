package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der Sachbearbeiter des Subunternehmens.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 */

@Path("/")
public class SCEmployeeRoute {	
	
	private Controller<SCEmployee> controller;

	public SCEmployeeRoute() {
		this.controller = new Controller<SCEmployee>(SCEmployee.class);
	}
	
	
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/scEmployee/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	
}