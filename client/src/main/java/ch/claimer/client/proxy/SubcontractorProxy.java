package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Subcontractor;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Subunternehmen an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface SubcontractorProxy {
	/**
	 * Holt alle Subunternehmen
	 * 
	 * @return String von Subunternehmen
	 */
	@GET
	@Path("subcontractor")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt ein bestimmtes Subunternehmen
	 * 
	 * @param id Identifikator eines bestimmten Subunternehmens
	 * @return String des Subunternehmens
	 */
	@GET
	@Path("subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Leigt ein neues Subunternehmen an
	 * 
	 * @param subcontractor neu anzulegendes Subunternehmen
	 */
    @POST
    @Path("subcontractor")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Subcontractor subcontractor);
    
    /**
     * Aktualisiert ein bestehendes Subunternehmen
     * 
     * @param subcontractor zu aktualisierendes Subunternehmen
     */
    @PUT
    @Path("subcontractor")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Subcontractor subcontractor);

	

}
