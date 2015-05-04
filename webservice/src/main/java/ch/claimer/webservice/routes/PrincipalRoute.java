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

import ch.claimer.shared.models.Principal;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die verfügbaren HTTP-Routes der Auftraggeber.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */
@Path("/")
public class PrincipalRoute {

	private Controller<Principal> controller;

	public PrincipalRoute() {
		this.controller = new Controller<Principal>(Principal.class);
	}
	
	/**
	 * Zeigt alle Auftraggeber an
	 * 
	 * @return Response HTTP-Antwort mit Auftraggebern
	 */
	@GET
	@RolesAllowed("admin")
	@Path("principal") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten Auftraggeber an
	 * 
	 * @param id Identifikator des anzuzeigenden Auftraggebers
	 * @return Response HTTP-Antwort mit dem Auftraggeber
	 */
	@GET
	@RolesAllowed("admin")
	@Path("principal/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Legt einen neuen Auftraggeber an
	 * 
	 * @param principal neu anzulegender Auftraggeber
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed("admin")
	@Path("principal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Principal principal) {	
		return controller.store(principal);
	}
	
	/**
	 * Aktualisiert einen bestehenden Auftraggeber
	 * 
	 * @param principal zu aktualisierender Auftragger
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed("admin")
	@Path("principal")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Principal principal) {
		return controller.update(principal);
	}	
	
}