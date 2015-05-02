package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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
 * Definiert die verfügbaren HTTP-Routes der Subunternehmen-Angestellten.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */

@Path("/")
public class SCEmployeeRoute {	
	
	private Controller<SCEmployee> controller;

	public SCEmployeeRoute() {
		this.controller = new Controller<SCEmployee>(SCEmployee.class);
	}
	
	
	/**
	 * Zeigt alle SU-Sachbearbeitende an
	 * 
	 * @return Response HTTP-Antwort mit SU-Sachbearbeitenden
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/scemployee") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten SU-Sachbearbeitenden an
	 * 
	 * @param id Identifikator des anzuzeigenden SU-Sachbearbeitenden
	 * @return Response HTTP-Antwort mit SU-Sachbearbeitenden
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
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
	 * Legt einen neuen SU-Sachbearbeitenden an
	 * 
	 * @param scemployee neu anzulegender Sachbearbeitender
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed({"admin", "intern"})
	@Path("scemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(SCEmployee scemployee) {	
		return controller.store(scemployee);
	}
	
	/**
	 * Aktualisiert einen besteheden SU-Sachbearbeitenden
	 * 
	 * @param scemployee zu aktualisierender Sachbearbeitende
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed({"admin", "intern"})
	@Path("scemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(SCEmployee scemployee) {
		return controller.update(scemployee);
	}	
}