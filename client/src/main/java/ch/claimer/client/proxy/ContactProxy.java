package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Contact;

/**
 * Bindet die verfügbaren HTTP-Routes und Methoden für Projekte an den Client.
 * Nimmt den HTTP-Content als String entgegen.
 * 
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 *
 */

public interface ContactProxy {
	
	/**
	 * Holt alle Kontakte
	 * 
	 * @return String von Kontakten
	 */
	@GET
	@Path("contact")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	/**
	 * Holt eine bestimmter Kontakt
	 * 
	 * @param id Identifikator eines bestimmten Kontakts
	 * @return String des Kontakts
	 */
	
	@GET
	@Path("contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
	/**
	 * Holt eine bestimmter Kontakt(Subunternehmen)
	 * 
	 * @param id Identifikator eines bestimmten Kontakt(Subunternehmen)
	 * @return String des Kontakt(Subunternehmen)
	 */
	
	@GET
	@Path("contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor(@PathParam("id")int id);
	
	/**
	 * Legt einen neuen Kontakt an
	 * 
	 * @param contact neu anzulegender Kontakt
	 */
	
	
	@POST
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	void create(Contact contact);
	
	/**
	 * Aktualisiert einen neuen Kontakt
	 * 
	 * @param contact zu aktualisierender Kontakt
	 */
	
	@PUT
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	void update(Contact contact);
}
