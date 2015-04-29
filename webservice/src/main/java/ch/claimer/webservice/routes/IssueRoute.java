package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;




import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der M�ngel. Zeigt den Controller gem�ss der URL-Pattern.
 * Diese Klasse wird gem�ss dem Dokument "Rollen und Rechte" erstellt
 * Zeigt den Controller gem�ss der URL-pattern
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
	 * Benutzt den Controller um die Projekte zu lesen
	 * 
	 * @param id-Projekt-Identifizierer um diese gem�ss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/issue/project/{id}")
	public Response showByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	}

	/**
	 * Benutzt den Controller um die Ansprechpersonen zu lesen
	 * 
	 * @param id-Ansprechperson-Identifizierer um diese gem�ss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "extern"})
	@Path("/issue/contact/{id}")
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("contact_id", id);
	}
	
	/**
	 * Benutzt den Controller um die Subunternehmen zu lesen
	 * 
	 * @param id Subunternehmen-Identifizierer um diese gem�ss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"power", "extern"})
	@Path("/issue/subcontractor/{id}")
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	
	}
	
	/**
	 * Benutzt den Controller um die Projekte zu aktualisieren
	 * 
	 * @param id Projekt-Identifizierer um diese gem�ss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@PUT
	@Path("/issue/project/{id}")
	public Response updateByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	
	}
	
}