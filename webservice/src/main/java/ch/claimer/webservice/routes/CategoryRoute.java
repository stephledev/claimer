package ch.claimer.webservice.routes;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	public Response show() {
		return controller.showAll();
	}
	
	@GET
	@Path("/category/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}