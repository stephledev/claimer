package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;






import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der Ansprechperson. Zeigt den Controller gemäss der URL-Pattern.
 * Diese Klasse wird gemäss dem Dokument "Rollen und Rechte" erstellt
 * 
 * @author Raoul Ackermann
 * @author Momcilo Bekcic
 */
@Path("/")
public class ContactRoute {

	private Controller<Contact> controller;

	public ContactRoute() {
		this.controller = new Controller<Contact>(Contact.class);
	}

	/**
	 * Benutzt den Controller um die Subunternehmen zu lesen
	 * 
	 * @param id Ansprechperson-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"editor", "extern"})
	@Path("/contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	
	}
	
	/**
	 * 
	 * @return Antwort vom Controller
	 */
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/contact") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * 
	 * @param id Identifizierer umd Daten gemäss URL anzuzeigen
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Ansprechperson nach Subunternehmen erstellen
	 * @param id Idetifizierer um Angaben gemäss URL Pattern zu erstellen
	 * @return Antwort vom Controller
	 */
	@PUT
	@RolesAllowed({"admin","intern"})
	@Path("/contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("id", id);
	
	}
	
	/**
	 * Ansprechperson nach Subunternehmen aktualisieren
	 * @param id Idetifizierer um Angaben gemäss URL Pattern zu erstellen
	 * @return Antwort vom Controller
	 */
	@POST
	@RolesAllowed({"admin","intern"})
	@Path("/contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("id", id);
	
	}
	
	
}