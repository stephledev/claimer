package ch.claimer.webservice.controller;

import org.hibernate.Session;

import ch.claimer.webservice.repositories.DefaultRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.webservice.services.DataProcessorService;
import ch.claimer.webservice.services.JsonDataProcessorService;
import ch.claimer.webservice.services.ResponseHandlerService;
import ch.claimer.webservice.util.HibernateUtil;

/**
 * Handles default CRUD operations for RESTful interactions with
 * all models. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.webservice.repositories}
 */
public class DefaultController<T, V> implements Controller<T, V> {

	protected final Class<T> type;
	private final DefaultRepository<T> repository;
	protected final DataProcessorService<T> processor;
	protected final ResponseHandlerService<V> response;
	protected Session session;
	
	public DefaultController(Class<T> type, ResponseHandlerService<V> response) {
		this.type = type;
		this.repository = new HibernateDefaultRepository<T>(type);
		this.processor = new JsonDataProcessorService<T>();
		this.response = response;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	/**
	 * Gets all models of the specified type from the repository
	 * and returns it.
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public V index() {
		session.beginTransaction();
		String entitiesString = processor.write(repository.getAll());
		session.getTransaction().commit();
		return response.success().entity(entitiesString).build();
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
	public V show(Integer id) {	
		session.beginTransaction();
		String entityString = processor.write(repository.getById(id));
		session.getTransaction().commit();
		return response.success().entity(entityString).build();
	}

	/**
	 * Stores the specified model instance data
	 * 
	 * @param entityString data of the model instance to store
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public V store(String entityString) {
		session.beginTransaction();
		T entity = processor.read(entityString, type);
		repository.store(entity);
		session.getTransaction().commit();
		return response.success().entity(entityString).build();
		
	}

	/**
	 * Updates the specified model instance data
	 * 
	 * @param entityString data of the model instance to update
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public V update(String entityString) {
		session.beginTransaction();
		T entity = processor.read(entityString, type);
		repository.update(entity);
		session.getTransaction().commit();
		return response.success().entity(entityString).build();
		
	}

	/**
	 * Destroys the specified model instance data
	 * 
	 * @param entityString data of the model instance to destroy
	 * 
	 * @return Response with HTTP Status and model data
	 */
	@Override
	public V destroy(Integer id) {
		session.beginTransaction();
		repository.destroy(id);
		session.getTransaction().commit();
		return response.success().entity(id.toString()).build();	
	}
	
}
