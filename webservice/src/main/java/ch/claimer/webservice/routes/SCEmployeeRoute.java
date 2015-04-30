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
	 * SU-Sachbearbeiter 
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
	 * Benutzt den Controller um die Subunternehmen zu lesen
	 * 
	 * @param id-Subunternehmen-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom "controller"
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/scemployee/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}
	
	/**
	 * SU-Sachbearbeiter nach Subunternehmen erstellen
	 * @param id Identifizierer vom Controller
	 * @return Antwort vom Controller
	 */
	@PUT
	@RolesAllowed({"admin", "intern"})
	@Path("/scemployee/subcontractor{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBySubcontractor(@PathParam("id") int id) {	//Methodennamen überprüfen
		return controller.showById(id);
	}
	
	/**
	 * SU-Sachbearbeiter nach Subunternehmen aktualisieren
	 * @param id Identifizierer 
	 * @return Antwort vom Controller
	 */
	@POST
	@RolesAllowed({"admin", "intern"})
	@Path("/scemployee/subcontractor{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBySubcontractor(@PathParam("id") int id) {	//Methodennamen überprüfen
		return controller.showById(id);
	}
}