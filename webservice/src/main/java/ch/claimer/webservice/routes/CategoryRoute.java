package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Category;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Kommentare.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
@Path("/")
public class CategoryRoute {	
	
	private Controller<Category> controller;

	public CategoryRoute() {
		this.controller = new Controller<Category>(Category.class);
	}
	
	/**
	 * Zeigt alle Kategorien an
	 * 
	 * @return Response HTTP-Antwort mit Kategorien
	 */
	@GET
	@RolesAllowed("admin")
	@Path("category") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt ein bestimmte Kategorie an
	 *  
	 * @param id Identifikator der anzuzeigenden Kategorie
	 * @return Response HTTP-Antwort mit Kategorie
	 */
	@GET
	@RolesAllowed("admin")
	@Path("category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}