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

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.webservice.controller.GCEmployeeController;

/**
 * Definiert die verfügbaren HTTP-Routes der Generalunternehmen-Sachbearbeiter.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 */

@Path("/")
public class GCEmployeeRoute {	
	
	private GCEmployeeController controller;

	public GCEmployeeRoute() {
		this.controller = new GCEmployeeController();
	}
	
	/**
	 * Zeigt alle GU-Sachbearbeitende an
	 * 
	 * @return Response HTTP-Antwort mit GU-Sachbearbeitendem
	 */
	@GET
	@RolesAllowed("superadmin")
	@Path("gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showByProperty("isActive", true);
	}
	
	/**
	 * Zeigt einen bestimmten GU-Sachbearbeitenden an
	 * 
	 * @param id-Identifikator des anzuzeigenden GU-Sachbearbeitenden
	 * @return Response HTTP-Antwort mit GU-Sachbearbeiter
	 */
	@GET
	@RolesAllowed("superadmin")
	@Path("gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Legt einen neuen GU-Sachbearbeitenden an
	 * 
	 * @param gcemployee neu anzulegender GU-Sachbearbeitende
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed("superadmin")
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(GCEmployee gcemployee) {	
		return controller.store(gcemployee);
	}
	
	/**
	 * Aktualisiert einen bestehenden Sachbearbeitenden
	 * 
	 * @param project zu aktualisierende Sachbearbeitende
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed("superadmin")
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(GCEmployee gcemployee) {
		return controller.update(gcemployee);
	}
}