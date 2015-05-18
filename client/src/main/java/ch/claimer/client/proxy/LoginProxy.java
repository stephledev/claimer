package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Login;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface LoginProxy {
	/**
	 * Holt alle Logins
	 * 
	 * @return String des Logins
	 */
	@GET
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt einen bestimmtes Login
	 * 
	 * @param id Identifikator eines bestimmten Logins
	 * @return String des Logins
	 */
	
	@GET
	@Path("login/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	/**
	 * Legt ein neues Login an
	 * 
	 * @param login zu erstellendes Login
	 */
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Login login);
	
	/**
	 * 	Meldet das übergeben Login an
	 * 
	 * @param login zu anmeldendes Login
	 */
	
	@POST
	@Path("check")
	@Consumes(MediaType.APPLICATION_JSON)
	String check(Login login);
	
	/**
	 * Aktualisiert ein bestehendes Login
	 * 
	 * @param login zu aktualisierendes Login
	 */
	
	@PUT
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Login login);
}
