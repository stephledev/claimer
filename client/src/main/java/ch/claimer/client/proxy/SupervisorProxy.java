package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Supervisor;

/**
 * 
 * Bindet die verfügbaren HTTP-Routes und Methoden für Bauleiter an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface SupervisorProxy {
	/**
	 * Holt alle Bauleiter
	 * 
	 * @return String von den Bauleitern
	 */
	@GET
	@Path("supervisor")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * 
	 * Holt einen bestimmten Bauleiter
	 * 
	 * @param id Identifikator eines bestimmten Bauleiters
	 * @return String des bestimmten Bauleiters
	 */
	@GET
	@Path("supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Legt einen neuen Bauleiter an
	 * 
	 * @param supervisor neu anzulegender Bauleiter
	 */
    @POST
    @Path("supervisor")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Supervisor supervisor);
    
    /**
     * Aktualisiert einen bestehnden Bauleiter
     * 
     * @param supervisor zu aktualisierender Bauleiter
     */
    @PUT
    @Path("supervisor")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Supervisor supervisor);

	

}
