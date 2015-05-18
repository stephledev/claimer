package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.SCEmployee;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Subunternehmen-Angestellten an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface SCEmployeeProxy {
	/**
	 * Holt alle verfügbaren SU-Angestellten
	 * 
	 * @return String von SU-Angestellten
	 */
	@GET
	@Path("scemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt einen bestimmten SU-Angestellten
	 * 
	 * @param id Identfikator eines bestimmten SU-Angestellten
	 * @return
	 */
	@GET
	@Path("scemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Holt alle SU-Angestellten einer Subunternehmung 
	 * 
	 * @param id Identfikator es des Subunternehmens der anzuzigenden SU-Angestellten
	 * @return String von SU-Angestellten
	 */
	@GET
	@Path("scemployee/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor(@PathParam("id")int id);
	
	/**
	 * Legt einen neuens SU-Angestellten an
	 * 
	 * @param scEmployee neu anzulegender SU-Angestellter
	 */
    @POST
    @Path("scemployee")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(SCEmployee scEmployee);
    
    /**
     * Aktualisiert einen bestehenden SU-Angestellten
     * 
     * @param scEmployee zu aktualisierender SU-Angestellter
     */
    @PUT
    @Path("scemployee")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(SCEmployee scEmployee);
}
