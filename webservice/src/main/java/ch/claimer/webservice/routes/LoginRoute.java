package ch.claimer.webservice.routes;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Login;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die verfügbaren HTTP-Routes der Logins.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 */
@Path("/")
public class LoginRoute {

	private Controller<Login> controller;

	public LoginRoute() {
		this.controller = new Controller<Login>(Login.class);
	}
	
	/**
	 * Zeigt alle Logins an
	 * 
	 * @return Response HTTP-Antwort mit Logins
	 */
	@GET
	@PermitAll
	@Path("login") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	
	/**
	 * Zeigt einen bestimmten Login an 
	 * 
	 * @param id Login-Identifikator des anzuzeigenden Logins
	 * @return Response HTTP-Antwort mit dem Login
	 */
	@GET
	@PermitAll
	@Path("login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Legt ein neues Login an
	 * 
	 * @param login neu anzulegender Login
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed("admin")
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Login login) {	
		return controller.store(login);
	}
	
	/**
	 * Aktualisiert einen bestehenden Login
	 * 
	 * @param login zu aktualisierender Login
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@PermitAll
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Login login) {
		return controller.update(login);
	}
	
}