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

import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.SCEmployee;
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

	public DefaultRoute() {
		mapper = new HashMap<String, DefaultController<?>>();
		mapper.put("scemployee", new DefaultController<SCEmployee>(SCEmployee.class));
		mapper.put("gcemployee", new DefaultController<GCEmployee>(GCEmployee.class));
	}
	
	/**
	 * Maps the controller to show all models of the specified type
	 * 
	 * @return Response from the controller
	 */
	@GET
	@Path("{model}")
	public Response showAllModels(@PathParam("model") String model) {
		return mapper.get(model).index();
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
		return mapper.get(model).show(id);
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
	public Response storeGCEmployee(@PathParam("model") String model, @FormParam("data") String data) {
		return mapper.get(model).store(data);
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
	public Response updateGCEmployee(@PathParam("model") String model, @FormParam("data") String data) {
		return mapper.get(model).update(data);
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
	public Response destroyGCEmployee(@PathParam("model") String model, @FormParam("data") String data) {
		return mapper.get(model).destroy(data);
	} 
}