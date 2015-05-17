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

import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.webservice.controller.Controller;

/**
 * Definiert die verfügbaren HTTP-Routes der Projekte.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 */
@Path("/")
public class ProjectRoute {

	private Controller<Project> controller;

	public ProjectRoute() {
		this.controller = new Controller<Project>(Project.class);
	}
	
	/**
	 * Zeigt alle Projekte an
	 * 
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed("admin")
	@Path("project") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt ein bestimmtes Projekt an
	 *  
	 * @param id Identifikator des anzuzeigenden Projektes
	 * @return Response HTTP-Antwort mit Projekt
	 */
	@GET
	@RolesAllowed("admin")
	@Path("project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}

	/**
	 * Zeigt alle Projekte eines Bauleiters an
	 * 
	 * @param id Identifikator des Bauleiters der anzuzeigenden Projekte
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed("editor-intern")
	@Path("project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySupervisor(@PathParam("id") int id) {
		return controller.showByRelation(Supervisor.class, id);
	}
	
	/**
	 * Zeigt alle Projekte einer Ansprechsperson an
	 * 
	 * @param id Identifikator der Ansprechsperson der anzuzeigenden Projekte
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed("editor-intern")
	@Path("project/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByRelations(Contact.class, id);
	}
	
	/**
	 * Legt ein neues Projekt an
	 * 
	 * @param project neu anzulegendes Projekt
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed("admin")
	@Path("project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Project project) {	
		return controller.store(project);
	}
	
	/**
	 * Aktualisiert ein bestehendes Projekt
	 * 
	 * @param project zu aktualisierendes Projekt
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed("admin")
	@Path("project")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Project project) {
		return controller.update(project);
	}	
}