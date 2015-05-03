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

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die verfügbaren HTTP-Routes der Mängel.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */
@Path("/")
public class IssueRoute {

	private Controller<Issue> controller;

	public IssueRoute() {
		this.controller = new Controller<Issue>(Issue.class);
	}
	
	/**
	 * Zeigt alle Mängel an
	 * 
	 * @return Response HTTP-Antwort mit Projekten
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("issue") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt einen bestimmten Mangel an
	 * 
	 * @param id Identifikator des anzuzeigenden Mangels
	 * @return Response HTTP-Antwort mit dem Mangel
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}

	/**
	 * Zeigt alle Mängel eines Projektes an
	 * 
	 * @param id Identifikator des Projektes der anzuzeigenden Mängel
	 * 
	 * @return Response HTTP-Antwort mit Mängeln
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	}

	/**
	 * Zeigt alle Mängel, die zu einer Ansprechsperson gehören, an
	 * 
	 * @param id Identifikator des Mangels der anzuzeigenden Ansprechsperson
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "extern"})
	@Path("issue/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("contact_id", id);
	}
	
	/**
	 * Zeigt alles Mängel eines Subunternehmens an
	 * 
	 * @param id Subunternehmen-identifikator der anzuzeigenden Mängel
	 * 
	 * @return Response HTTP-Antwort mit Mängeln
	 */
	@GET
	@RolesAllowed({"power", "extern"})
	@Path("issue/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("subcontractor_id", id);
	
	}
	
	/**
	 * Legt einen neuen Mangel an
	 * (siehe 'Mangel nach Projekt erstellen')
	 * 
	 * @param issue neu anzulegender Mangel
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed({"editor", "intern"})
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Issue issue) {	
		return controller.store(issue);
	}
	
	/**
	 * Aktualisiert einen bestehenden Mangel
	 * (Siehe 'Mangel nach Projekt erstellen')
	 * 
	 * @param issue zu aktualisierender Mangel
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed({"editor", "intern"})
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Issue issue) {
		return controller.update(issue);
	}	
	
}