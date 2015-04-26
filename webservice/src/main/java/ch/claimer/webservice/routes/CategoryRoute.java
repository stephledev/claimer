package ch.claimer.webservice.routes;

import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
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
	@Path("/category") 
	public Response show(@Context HttpServletRequest request) {
		String auth = request.getHeader("Authorization").replaceFirst("Basic ", "");
		auth = new String(Base64.getDecoder().decode(auth));
		System.out.println(auth);
		return controller.showAll();
	}
	
	@GET
	@Path("/category/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
}