package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
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
 * Definiert die verfügbaren HTTP-Routes der Ansprechpersonen.
 * Lädt anhand der URL und der HTTP-Anfrage die entsprechende Controller-Methode.
 * Liefert eine HTTP-Antwort mit Statuscode zurück.
 * 
 * @author Raoul Ackermann
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
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
		return controller.showByProperty("subcontractor_id", id);
	
	}
	
	/**
	 * Zeigt alle Ansprechspersonen an
	 * 
	 * @return Response HTTP-Antwort mit Ansprechspersonen
	 */
	
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/contact") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAll() {
		return controller.showAll();
	}
	
	/**
	 * Zeigt eine bestimmte Ansprechsperson an
	 * @param id Identifizierer der anzuzeigenden Ansprechsperson
	 * 
	 * @return Response HTTP-Antwort mit Ansprechsperson
	 */
	@GET
	@RolesAllowed({"intern", "admin"})
	@Path("/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}
	
	/**
	 * Legt eine neue Ansprechsperson an 
	 * (siehe 'Ansprechsperson nach Subunternehmen erstellen')
	 * 
	 * @param contact neu anzulegendee Ansprechsperson
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@POST
	@RolesAllowed({"admin", "intern"})
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Contact contact) {	
		return controller.store(contact);
	}
	
	/**
	 * Aktualisiert eine bestehende Ansprechsperson
	 * (siehe 'Ansprechsperson nach Subunternehmen aktualisieren')
	 * 
	 * @param contact zu aktualisierende Ansprechsperson
	 * @return Response HTTP-Antwort mit Statusmeldung
	 */
	@PUT
	@RolesAllowed({"admin", "intern"})
	@Path("contact")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Contact contact) {
		return controller.update(contact);
	}
	
}