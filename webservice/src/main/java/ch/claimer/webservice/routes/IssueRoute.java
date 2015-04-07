package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.controller.IssueController;


/**
 * Defines RESTful routes for issue specific interactions. 
 * Maps the controller according to the URL pattern
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
	 * Maps the controller to get a specific issue of a contact
	 * 
	 * @param id issue of a contact to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@RolesAllowed("basic")
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
	@RolesAllowed("basic")
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
	@RolesAllowed("basic")
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
	@RolesAllowed("basic")
	@Path("/issue/project/{id}")
	public Response showIssueByProject(@PathParam("id") int id) {
		return controller.showByProject(id);
	} 
	
	/**
	 * Maps the controller to store the specified issue instance
	 * 
	 * @param data data of the issue instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@RolesAllowed("supervisor")
	@Path("issue")
	public Response storeIssue(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update the specified issue instance
	 * 
	 * @param data data of the issue instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@RolesAllowed("contact")
	@Path("issue")
	public Response updateIssue(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to destroy the specified issue instance
	 * 
	 * @param data data of the issue instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@RolesAllowed("supervisor")
	@Path("issue/{id}")
	public Response destroyIssue(@PathParam("id") int id) {
		return controller.destroy(id);
	} 
}