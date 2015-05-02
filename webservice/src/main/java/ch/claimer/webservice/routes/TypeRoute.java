package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Type;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Typen.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */

@Path("/")
public class TypeRoute {	
	
	private Controller<Type> controller;

	public TypeRoute() {
		this.controller = new Controller<Type>(Type.class);
	}
	
	/**
	 * Zeigt alle Projekte an
	 * 
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/type")
	@Produces(MediaType.APPLICATION_JSON)
	public Response show() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten Typen an
	 * 
	 * @param id Identifikator des anzuzeigenden Typen
	 * @return Response HTTP-Antwort mit Projekt
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}