package ch.claimer.webservice.routes;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Login;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes des Logins.
 * Zeigt den "Controller" gem�ss der URL-Pattern
 * 
 * @author Momcilo Bekcic
 */
@Path("/")
public class LoginRoute {

	private Controller<Login> controller;

	public LoginRoute() {
		this.controller = new Controller<Login>(Login.class);
	}
	
	/**
	 * 
	 * @param id-Identifizierer der Logins um die - von der URL
	 * unterst�tzen - anzuzeigen
	 * @return Antowrt vom Controller
	 * 
	 */
	@GET
	@PermitAll
	@Path("/login") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll(@Context HttpServletRequest request) {
		return controller.showAll();
	}
	
	
	/**
	 * 
	 * @param id-Identifizierer der Logins um die - von der URL
	 * unterst�tzen - anzuzeigen
	 * @return Antwort vom Controller
	 * 
	 */
	@GET
	@PermitAll
	@Path("/login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Login erstellen
	 * @param id Identifizierer des Controllers um Angaben gem�ss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@PUT
	@RolesAllowed({"admin", "intern"})
	@Path("/login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createById(@PathParam("id") int id) {	//Methodennamen �berpr�fen
		return controller.showById(id);
	}
	
	/**
	 * Login aktualisieren
	 * @param id Identifizierer des Controllers um Angaben gem�ss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@POST
	@PermitAll
	@Path("/login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateById(@PathParam("id") int id) {	//Methodennamen �berpr�fen
		return controller.showById(id);
	}
	
}