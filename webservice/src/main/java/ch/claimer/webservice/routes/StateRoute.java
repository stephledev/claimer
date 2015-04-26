package ch.claimer.webservice.routes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.State;
import ch.claimer.webservice.controller.Controller;

/**
 * 
 * @author Stephan Beeler
 *
 */

@Path("/")
public class StateRoute {	
	
	private Controller<State> controller;

	public StateRoute() {
		this.controller = new Controller<State>(State.class);
	}
	
	@GET
	@Path("/state") 
	public Response show() {
		return controller.showAll();
	}
	
	@GET
	@Path("/state/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}