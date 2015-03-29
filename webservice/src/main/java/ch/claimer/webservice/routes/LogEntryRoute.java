package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.LogEntryController;


/**
 * Defines RESTful routes for LogEntry specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann
 */
@Path("/")
public class LogEntryRoute {
	
	private LogEntryController controller;

	public LogEntryRoute() {
		this.controller = new LogEntryController();
	}
	
	/**
	 * Maps the controller to get a specific LogEntry of a issue
	 * 
	 * @param id LogEntry of a issue to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/logentry/issue/{id}")
	public Response showLogEntryByIssue(@PathParam("id") int id) {
		return controller.showByIssue(id);
	} 
	/**
	 * Maps the controller to get a specific LogEntry of a project
	 * 
	 * @param id LogEntry of a project to show supplied by the URL
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("/logentry/project/{id}")
	public Response showLogEntryByProject(@PathParam("id") int id) {
		return controller.showByProject(id);
	} 
}