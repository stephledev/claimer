package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Project;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 *  
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 *
 */

public interface ProjectProxy {
	
	/**
	 * Holt alle Projekte
	 * 
	 * @return String von Projekten
	 */
	@GET
	@Path("project")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt ein bestimmtes Projekt
	 * 
	 * @param id Identifikator eines bestimmten Projektes
	 * @return String des Projektes
	 */
	@GET
	@Path("project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Holt alle Projekte eines Bauleiters
	 * 
	 * @param id Identifikator des Bauleiters der anzuzeigenden Projekte
	 * @return String von Projekten
	 */
	@GET
	@Path("project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySupervisor(@PathParam("id")int id);
	
	/**
	 * Holt alle Projekte einer Ansprechsperson
	 * 
	 * @param id Identifikator der Ansprechsperson der anzuzeigenden Projekte
	 * @return String von Projekten
	 */
	@GET
	@Path("project/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact(@PathParam("id")int id);
	
	/**
	 * Legt ein neues Projekt an
	 * 
	 * @param project neu anzulegendes Projekt
	 */
    @POST
    @Path("project")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Project project);
    
    /**
     * Aktualisiert ein bestehendes Projekt
     * 
     * @param project zu aktualisierendes Projekt
     */
    @PUT
    @Path("project")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Project project);
}
