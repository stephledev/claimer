package ch.claimer.webservice.routes;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Login;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes des Logins.
 * Zeigt den "Controller" gemäss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 */
@Path("/")
public class LoginRoute {

	private Controller<Login> controller;

	public LoginRoute() {
		this.controller = new Controller<Login>(Login.class);
	}
	
	@GET
	@PermitAll
	@Path("/login") 
	public Response show(@Context HttpServletRequest request) {
		return controller.showAll(request);
	}
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/login/{id}")
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
}