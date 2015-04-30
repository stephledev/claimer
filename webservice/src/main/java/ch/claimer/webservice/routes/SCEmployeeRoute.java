package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der Kommentare.
 * Zeigt den "Controller" gemäss der URL-Pattern
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
	@Path("/scemployee") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id-Identifizeirer um die -von der URL unterstützten- anzuzeigen
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/scemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * 
	 * 
	 * @return Antwort vom "controller"
	 */
	@GET
	@Path("/scemployee/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}
}