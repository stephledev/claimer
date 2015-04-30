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






import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der Mängel. Zeigt den Controller gemäss der URL-Pattern.
 * Diese Klasse wird gemäss dem Dokument "Rollen und Rechte" erstellt
 * Zeigt den Controller gemäss der URL-pattern
 * 
 * @author Raoul Ackermann
 * @author Momcilo Bekcic
 */
@Path("/")
public class IssueRoute {

	private Controller<Issue> controller;

	public IssueRoute() {
		this.controller = new Controller<Issue>(Issue.class);
	}
	
	/**
	 * Mangel lesen
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/issue") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Mangel lesen
	 * @param id Identifizierer um Angaben gemäss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}

	/**
	 * Benutzt den Controller um die Mängel nach Projekten zu lesen
	 * 
	 * @param id-Projekt-Identifizierer um Angaben gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	}

	/**
	 * Benutzt den Controller um Mängel nach Ansprechpersonen zu lesen
	 * 
	 * @param id-Ansprechperson-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "extern"})
	@Path("/issue/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("contact_id", id);
	}
	
	/**
	 * Benutzt den Controller um die Subunternehmen zu lesen
	 * 
	 * @param id Subunternehmen-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"power", "extern"})
	@Path("/issue/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	
	}
	
	/**
	 * Benutzt den Controller um die Mängel nach Projekten zu aktualisieren
	 * 
	 * @param id Projekt-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@POST
	@RolesAllowed({"editor","intern"})
	@Path("/issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	
	}
	
	@POST
	@RolesAllowed({"admin","intern"})
	@Path("/issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateById(@PathParam("id") int id) {		//Methodenname vllt. nicht optimal
		return controller.showByProperty("id", id);
	
	}
	
	
}