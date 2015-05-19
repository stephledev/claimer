package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Issue;

/**
 * 
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface IssueProxy {
	/**
	 * Holt alle Mangel
	 * 
	 * @return String von Mangel
	 */
	
	@GET
	@Path("issue")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt einen bestimmten Mangel
	 * 
	 * @param id Identifikator eines bestimmten Mangels
	 * @return String des Mangels
	 */
	
	@GET
	@Path("issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Holt bestimmte Mängel
	 * 
	 * @param id Identifikator eines bestimmten Projekts von wo die Mängel gezogen werden
	 * @return String der Mängel
	 */
	
	@GET
	@Path("issue/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByProject(@PathParam("id")int id);
	
	/**
	 * Holt bestimmte Mängel
	 * 
	 * @param id Identifikator eines bestimmten Kontakts von wem die Mängel gezogen werden
	 * @return String der Mängel
	 */
	
	@GET
	@Path("issue/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact(@PathParam("id")int id);
	
	/**
	 * Holt bestimmte Mängel
	 * 
	 * @param id Identifikator eines bestimmten Subunternehmens von wem die Mängel gezogen werden
	 * @return String der Mängel
	 */
	
	@GET
	@Path("issue/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor(@PathParam("id")int id);
	
	/**
	 * Legt einen neuen Mangel an
	 * 
	 * @param issue zu erstellender Mangel
	 */
	
	@POST
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Issue issue);
	
	/**
	 * Aktualisiert einen bestimmten Mangel
	 * 
	 * @param issue zu zu aktualisierender Mangel
	 */
		
	@PUT
	@Path("issue")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Issue issue);
}
