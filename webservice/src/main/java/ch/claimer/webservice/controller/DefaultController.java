package ch.claimer.webservice.controller;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.claimer.webservice.repositories.DefaultRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.webservice.services.DataProcessorService;
import ch.claimer.webservice.services.JsonDataProcessorService;

/**
 * Handles default CRUD operations for RESTful interactions with
 * all models. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.webservice.repositories}
 */
public class DefaultController<T> implements Controller<T, Integer> {

	protected final Class<T> clazz;
	private final DefaultRepository<T, Integer> repository;
	protected final DataProcessorService<T> processor;
	protected final String type = MediaType.APPLICATION_JSON;
	
	public DefaultController(Class<T> clazz) {
		this.clazz = clazz;
		this.repository = new HibernateDefaultRepository<T, Integer>(clazz);
		this.processor = new JsonDataProcessorService<T>();
	}

	/**
	 * Gets all models of the specified type from the repository
	 * and returns it.
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public Response index() {
		String entitiesString = processor.write(repository.getAll());
		return Response.status(200).entity(entitiesString).type(type).build();
		
	}

	/**
	 * Gets the specified model instance by his primary key from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the model instance to show
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public Response show(Integer id) {	
		String entityString = processor.write(repository.getById(id));
		return Response.status(200).entity(entityString).type(type).build();
	}

	/**
	 * Stores the specified model instance data
	 * 
	 * @param entityString data of the model instance to store
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public Response store(String entityString) {
		T entity = processor.read(entityString, clazz);
		repository.store(entity);
		return Response.status(200).entity(entityString).type(type).build();
		
	}

	/**
	 * Updates the specified model instance data
	 * 
	 * @param entityString data of the model instance to update
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public Response update(String entityString) {
		T entity = processor.read(entityString, clazz);
		repository.update(entity);
		return Response.status(200).entity(entityString).type(type).build();
		
	}

	/**
	 * Destroys the specified model instance data
	 * 
	 * @param entityString data of the model instance to destroy
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public Response destroy(Integer id) {
		repository.destroy(id);
		return Response.status(200).entity(id.toString()).build();		
	}
	
}
