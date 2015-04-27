package ch.claimer.webservice.routes;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der Ansprechperson. Zeigt den Controller gemäss der URL-Pattern.
 * Diese Klasse wird gemäss dem Dokument "Rollen und Rechte" erstellt
 * 
 * @author Raoul Ackermann
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
	 * @return Anwort vom Controller
	 */
	@GET
	@Path("/contact/subcontractor/{id}")
	public Response showBySubcontractor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	
	}
	
}