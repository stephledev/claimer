package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Principal;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Momcilo Bekcic
 * @since 1.0
 * @version 1.0
 *
 */

public interface PrincipalProxy {
	
	/**
	 * Holt alle Bauherren
	 * 
	 * @return String von Bauherr
	 */
	
	@GET
	@Path("principal")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt eine bestimmten Bauherr
	 * 
	 * @param id Identifikator eines bestimmten Bauherren
	 * @return String des Bauherr
	 */
	
	@GET
	@Path("principal/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Legt einen neuen Bauherr an
	 * 
	 * @param principal zu erstellender Bauherr
	 */
	
	@POST
	@Path("principal")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Principal principal);
	
	 /**
     * Aktualisiert ein bestehender Bauherr
     * 
     * @param principal zu aktualisierender Bauherr
     */
	
	@PUT
	@Path("principal")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Principal principal);
}
