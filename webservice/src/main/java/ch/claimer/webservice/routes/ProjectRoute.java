package ch.claimer.webservice.routes;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;




import ch.claimer.shared.models.Project;
import ch.claimer.webservice.controller.Controller;


/**
 * Definiert die REST-Routes der Projekte. Zeigt den Controller gemäss der URL-Pattern.
 * Diese Klasse wird gemäss dem Dokument "Rollen und Rechte" erstellt
 * 
 * @author Momcilo Bekcic
 */
@Path("/")
public class ProjectRoute {

	private Controller<Project> controller;

	public ProjectRoute() {
		this.controller = new Controller<Project>(Project.class);
	}
	
	/**
	 * Benutzt den Controller um das Projekt zu lesen
	 * 
	 * @param id-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom "controller"
	 */
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/project/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showById(@PathParam("id") int id) {
		return controller.showById(id);
	}

	/**
	 * Benutzt den Controller um die Bauleiter zu lesen
	 * 
	 * @param id-Bauleiter-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom "controller"
	 */
	@GET
	@RolesAllowed({"editor", "intern"})
	@Path("/project/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showBySupervisor(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}

	/**
	 * Benutzt den "Controller" um die Kategorie
	 * 
	 * @param id-Kategorie-Identifizierer um diese gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@RolesAllowed({"", ""})
	@Path("/project/category/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByCategory(@PathParam("id") int id) {
		return controller.showByProperty("category_id", id);
	}
	
	/**
	 * Benutzt den Controller um den Status anzuzeigen
	 * 
	 * @param id-Status-Identifizierer um es gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@Path("/project/state/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByState(@PathParam("id") int id) {
		return controller.showByProperty("person_id", id);
	}
	
	
	/**
	 * Benutzt den Controller um den Typ
	 * 
	 * @param id-Typ-Identifizierer um dieses gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@Path("/project/type/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showByType(@PathParam("id") int id) {
		return controller.showByProperty("type_id", id);
	}
	
	
	/**
	 * Benutzt den Controller nur um den Logeintrag anzuzeigen
	 * 
	 * @param id-Logeintrag-Identifizierer um dieses gemäss der URL anzuzeigen
	 * 
	 * @return Antwort vom Controller
	 */
	@GET
	@Path("/project/logEntry/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showlogEntry(@PathParam("id") int id) {
		return controller.showByProperty("logEntry_id", id);
	}
	
	@GET
	@RolesAllowed({"admin", "intern"})
	@Path("/project") 
	@Produces(MediaType.APPLICATION_JSON)
	public Response show() {
		return controller.showAll();
	}
	
	
	
}