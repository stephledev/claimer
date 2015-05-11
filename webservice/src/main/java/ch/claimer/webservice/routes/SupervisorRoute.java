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

import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Bauleiter.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */

@Path("/")
public class SupervisorRoute {	
	
	private Controller<Supervisor> controller;

	public SupervisorRoute() {
		this.controller = new Controller<Supervisor>(Supervisor.class);
	}
	
	/**
	 * Zeigt alle Bauleiter an
	 * 
	 * @param request HTTP-Anfrage
	 * @return Response HTTP-Antwort mit Bauleitern
	 */
	@GET
	@RolesAllowed("admin")
	@Path("supervisor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten Bauleiter an
	 * 
	 * @param id Idetentifikator des anzuzeigenden Bauleiters
	 * @return Response HTTP-Antwort mit Bauleitern
	 */
	@GET
	@RolesAllowed("admin")
	@Path("supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Legt einen neuen Bauleiter an
	 * 
	 * @param supervisor neu anzulegender Bauleiter
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed("admin")
	@Path("supervisor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Supervisor supervisor) {	
		return controller.store(supervisor);
	}
	
	/**
	 * Aktualisiert einen bestehenden Bauleiter
	 * 
	 * @param supervisor zu aktualisierender Bauleiter
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed("admin")
	@Path("supervisor")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Supervisor supervisor) {
		return controller.update(supervisor);
	}	
}