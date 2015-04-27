package ch.claimer.webservice.routes;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die RESTfull Routes der Mängel.
 * Zeigt den Controller gemäss der URL-pattern
 * 
 * @author Raoul Ackermann / Momcilo Bekcic
 */
@Path("/")
public class IssueRoute {

	private Controller<Issue> controller;

	public IssueRoute() {
		this.controller = new Controller<Issue>(Issue.class);
	}

	/**
	 * Maps the controller to show comment(s) of a supervisor
	 * 
	 * @param id
	 *            supervisor identifier of comment(s) to show supplied by the
	 *            URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/project/{id}")
	public Response showByProject(@PathParam("id") int id) {
		return controller.showByProperty("project_id", id);
	}

	/**
	 * Maps the controller to show comment(s) of a contact
	 * 
	 * @param id
	 *            contact identifier of comment(s) to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/contact/{id}")
	public Response showByContact(@PathParam("id") int id) {
		return controller.showByProperty("contact_id", id);
	}
	
	/**
	 * Maps the controller to show comment(s) of a contact
	 * 
	 * @param id
	 *            contact identifier of comment(s) to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	
	@GET
	@Path("/issue/subcontractor/{id}")
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	
	}
	
}