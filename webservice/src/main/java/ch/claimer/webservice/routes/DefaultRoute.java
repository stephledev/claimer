package ch.claimer.webservice.routes;

import java.util.HashMap;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import ch.claimer.shared.models.*;
import ch.claimer.webservice.controller.DefaultController;

/**
 * Defines default CRUD routes for RESTful interactions with
 * all models. Maps the controller according to the URL pattern.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.shared.models}
 * {@link ch.claimer.webservice.controller}
 */
@Path("/")
public class DefaultRoute {
	
	private HashMap<String, DefaultController<?>> mapper;
	private DefaultController<?> controller;

	public DefaultRoute() {
		mapper = new HashMap<String, DefaultController<?>>();
		mapper.put("category", new DefaultController<Category>(Category.class));
		mapper.put("comment", new DefaultController<Comment>(Comment.class));
		mapper.put("contact", new DefaultController<Contact>(Contact.class));
		mapper.put("image", new DefaultController<Image>(Image.class));
		mapper.put("issue", new DefaultController<Issue>(Issue.class));
		mapper.put("LogEntry", new DefaultController<LogEntry>(LogEntry.class));
		mapper.put("Login", new DefaultController<Login>(Login.class));
		mapper.put("Project", new DefaultController<Project>(Project.class));
		mapper.put("scemployee", new DefaultController<SCEmployee>(SCEmployee.class));
		mapper.put("state", new DefaultController<State>(State.class));
		mapper.put("subcontractor", new DefaultController<Subcontractor>(Subcontractor.class));
		mapper.put("supervisor", new DefaultController<Supervisor>(Supervisor.class));
		mapper.put("type", new DefaultController<Type>(Type.class));
	}
	
	/**
	 * Maps the controller to show all models of the specified type
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("{model}")
	public Response showAllModels(@PathParam("model") String model) {
		if((controller = mapper.get(model)) != null) {
			return controller.index();
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
		
	} 
	
	/**
	 * Maps the controller to show the specified model instance by his primary key
	 * 
	 * @param id primary key of the model instance to show supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("{model}/{id}")
	public Response showModelById(@PathParam("model") String model, @PathParam("id") int id) {
		if((controller = mapper.get(model)) != null) {
			return controller.show(id);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
	
	/**
	 * Maps the controller to store the specified model instance
	 * 
	 * @param data data of the model instance to store supplied by a form
	 * 
	 * @return Response from the controller
	 */
	@POST
	@Path("{model}")
	public Response storeModel(@PathParam("model") String model, @FormParam("data") String data) {
		if((controller = mapper.get(model)) != null) {
			return controller.store(data);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
	
	/**
	 * Maps the controller to update the specified model instance
	 * 
	 * @param data data of the model instance to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("{model}")
	public Response updateModel(@PathParam("model") String model, @FormParam("data") String data) {
		if((controller = mapper.get(model)) != null) {
			return controller.update(data);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
	
	/**
	 * Maps the controller to destroy the specified model instance
	 * 
	 * @param data data of the model instance to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("{model}")
	public Response destroyModel(@PathParam("model") String model, @FormParam("data") String data) {
		if((controller = mapper.get(model)) != null) {
			return controller.destroy(data);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
}