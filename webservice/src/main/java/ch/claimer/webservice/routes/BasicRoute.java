package ch.claimer.webservice.routes;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

import ch.claimer.shared.models.*;
import ch.claimer.webservice.controller.DefaultController;
import ch.claimer.webservice.util.HibernateUtil;

/**
 * Defines basic routes for read actions for all models. 
 * Maps the controller according to the URL pattern.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.shared.models}
 * {@link ch.claimer.webservice.controller}
 */
@Path("/")
public class BasicRoute {
	
	private HashMap<String, DefaultController<?>> mapper;
	private DefaultController<?> controller;

	
	@SuppressWarnings("unchecked")
	public BasicRoute() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Map<String, ClassMetadata> map = sessionFactory.getAllClassMetadata();
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
	@RolesAllowed("basic")
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
	@RolesAllowed("basic")
	@Path("{model}/{id}")
	public Response showModelById(@PathParam("model") String model, @PathParam("id") int id) {
		if((controller = mapper.get(model)) != null) {
			return controller.show(id);
		} else {
			return Response.status(404).entity("Entity doesn't exist").build();
		}
	} 
	
}