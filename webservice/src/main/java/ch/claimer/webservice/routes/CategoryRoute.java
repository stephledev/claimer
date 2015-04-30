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
 * 
 * @author Stephan Beeler
 *
 */

@Path("/")
public class CategoryRoute {	
	
	private Controller<Category> controller;

	public CategoryRoute() {
		this.controller = new Controller<Category>(Category.class);
	}
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/category") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response show() {
		return controller.showAll();
	}
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}