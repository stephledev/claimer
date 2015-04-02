package ch.claimer.webservice.routes;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

import ch.claimer.shared.models.*;
import ch.claimer.webservice.controller.DefaultController;
import ch.claimer.webservice.util.HibernateUtil;

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

	
	@SuppressWarnings("unchecked")
	public DefaultRoute() {
		SessionFactory session = HibernateUtil.getSessionFactory();
		Map<String, ClassMetadata> map = session.getAllClassMetadata();
		mapper = new HashMap<String, DefaultController<?>>();
		
		for(ClassMetadata metadata : map.values()){
			String entityName = metadata.getEntityName();
			entityName = entityName.substring(entityName.lastIndexOf(".")+1);
			mapper.put(entityName.toLowerCase(), new DefaultController<Model>(metadata.getMappedClass()));
		}
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
	@Path("{model}/{id}")
	public Response destroyModel(@PathParam("model") String model, @PathParam("id") int id) {
		if((controller = mapper.get(model)) != null) {
			return controller.destroy(id);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
}