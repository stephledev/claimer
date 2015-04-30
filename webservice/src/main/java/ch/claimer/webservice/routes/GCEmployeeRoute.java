package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die REST-Routes der GU-Angestellten.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 * 
 */

@Path("/")
public class GCEmployeeRoute {	
	
	private Controller<GCEmployee> controller;

	public GCEmployeeRoute() {
		this.controller = new Controller<GCEmployee>(GCEmployee.class);
	}
	
	/**
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"superadmin", "intern"})
	@Path("/gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id-Identifizierer um Angaben gemäss URL zu liefern
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"superadmin", "intern"})
	@Path("/gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * GU-Sachbearbeiter erstellen
	 * @param id Identifizierer um Angaben gemäss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@PUT
	@RolesAllowed({"superadmin", "intern"})
	@Path("/gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createById(@PathParam("id") int id) { 		//Methodenname vllt nicht optional
		return controller.showByProperty("person_id", id);
	
	}
	
	/**
	 * GU-Sachbearbeiter aktualisieren
	 * @param id Identifizierer um Angaben gemäss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@POST
	@RolesAllowed({"superadmin", "intern"})
	@Path("/gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateById(@PathParam("id") int id) { 		//Methodenname vllt nicht optional
		return controller.showByProperty("person_id", id);
	
	}
}