package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.IssueController;


/**
 * Defines RESTful routes for issue specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class IssueRoute {
	
	private IssueController<Issue> controller;

	public IssueRoute() {
		this.controller = new IssueController<Issue>();
	}
	
	/**
	 * Maps the controller to get a specific issue of a comment
	 * 
	 * @param id issue of a comment to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/contact/{id}")
	public Response showIssueByContact(@PathParam("id") int id) {
		return controller.showByContact(id);
	} 
	
	/**
	 * Maps the controller to get a specific issue of a supervisor
	 * 
	 * @param id issue of a supervisor to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/supervisor/{id}")
	public Response showIssueBySupervisor(@PathParam("id") int id) {
		return controller.showBySupervisor(id);
	} 
	
	/**
	 * Maps the controller to get a specific issue of a state
	 * 
	 * @param id issue of a state to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/state/{id}")
	public Response showIssueByState(@PathParam("id") int id) {
		return controller.showByState(id);
	} 
	
	/**
	 * Maps the controller to get a specific issue of a project
	 * 
	 * @param id issue of a project to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/issue/project/{id}")
	public Response showIssueByProject(@PathParam("id") int id) {
		return controller.showByProject(id);
	} 
}