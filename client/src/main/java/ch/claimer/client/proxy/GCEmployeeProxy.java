package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.GCEmployee;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface GCEmployeeProxy {
	
	/**
	 * Holt alle Mitarbeiter der GU
	 * 
	 * @return String von Mitarbeiter
	 */
	
	@GET
	@Path("gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt eine bestimmten Mitarbeiter der GU
	 * 
	 * @param id Identifikator eines bestimmten Mitarbeiter der GU
	 * @return String des Mitarbeiters
	 */
	
	@GET
	@Path("gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Legt einen neuen Mitarbeiter der GU an
	 * 
	 * @param gcemployee zu erstellender Mitarbeiter der GU
	 */
	
	@POST
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(GCEmployee gcEmployee);
	
	 /**
     * Aktualisiert ein bestehender Mitarbeiter der GU
     * 
     * @param gcemployee zu aktualisierender Mitarbeiter der GU
     */
	
	@PUT
	@Path("gcemployee")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(GCEmployee gcEmployee);
	

}
