package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.webservice.repositories.DefaultRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.webservice.services.DataProcessorService;
import ch.claimer.webservice.services.JsonDataProcessorService;

public class DefaultController<T> implements Controller<T, Integer> {

	private final Class<T> clazz;
	private final DefaultRepository<T, Integer> repository;
	private final DataProcessorService<T> processor;
	
	public DefaultController(Class<T> clazz) {
		this.clazz = clazz;
		this.repository = new HibernateDefaultRepository<T, Integer>(clazz);
		this.processor = new JsonDataProcessorService<T>();
	}

	@Override
	public Response index() {
		String entitiesString = processor.write(repository.getAll());
		return Response.status(200).entity(entitiesString).build();
		
	}

	@Override
	public Response show(Integer id) {	
		String entityString = processor.write(repository.getById(id));
		return Response.status(200).entity(entityString).build();
	}

	@Override
	public Response store(String entityString) {
		T entity = processor.read(entityString, clazz);
		repository.store(entity);
		return Response.status(200).entity(clazz.toString() + " has been created: " + entityString).build();
		
	}

	@Override
	public Response update(String entityString) {
		T entity = processor.read(entityString, clazz);
		repository.update(entity);
		return Response.status(200).entity(clazz.toString() + " has been updated: " + entityString).build();
		
	}

	@Override
	public Response destroy(String entityString) {
		T entity = processor.read(entityString, clazz);
		repository.destroy(entity);
		return Response.status(200).entity(clazz.toString() + " has been deleted: " + entityString).build();		
	}
	
}
