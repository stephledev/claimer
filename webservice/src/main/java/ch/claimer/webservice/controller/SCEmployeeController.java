package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import com.google.inject.Inject;

import ch.claimer.shared.entities.SCEmployee;
import ch.claimer.webservice.injector.DataProcessorServiceFactory;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.service.DataProcessorService;
import ch.claimer.webservice.service.JsonDataProcessorService;

public class SCEmployeeController implements Controller<Integer> {

	private final SCEmployeeRepository repo;
	private final JsonDataProcessorService<SCEmployee> processor;
	
	@Inject
	public SCEmployeeController(SCEmployeeRepository repo) {
		this.repo = repo;
		this.processor = new JsonDataProcessorService<SCEmployee>(SCEmployee.class);
	}

	@Override
	public Response index() {
		String entitiesString = processor.write(repo.getAll());
		return Response.status(200).entity(entitiesString).build();
		
	}

	@Override
	public Response show(Integer id) {	
		String entityString = processor.write(repo.getById(id));
		return Response.status(200).entity(entityString).build();
	}

	@Override
	public Response store(String entityString) {
		SCEmployee entity = processor.read(entityString);
		repo.store(entity);
		return Response.status(200).entity("General Contractor has been created: " + entityString).build();
		
	}

	@Override
	public Response update(String entityString) {
		SCEmployee entity = processor.read(entityString);
		repo.update(entity);
		return Response.status(200).entity("General Contractor has been updated: " + entityString).build();
		
	}

	@Override
	public Response destroy(String entityString) {
		SCEmployee entity = processor.read(entityString);
		repo.destroy(entity);
		return Response.status(200).entity("General Contractor has been deleted: " + entityString).build();		
	}
	
}
