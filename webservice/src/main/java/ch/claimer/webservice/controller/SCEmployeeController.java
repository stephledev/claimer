package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateSCEmployeeRepository;

public class SCEmployeeController<T> extends DefaultController<SCEmployee> {
	
	private final SCEmployeeRepository repository;

	public SCEmployeeController() {
		super(SCEmployee.class);
		this.repository = new HibernateSCEmployeeRepository();
	}

	public Response showBySubcontractor(int id) {
		String entityString = processor.write(repository.getBySubcontractor(id));
		return Response.status(200).entity(entityString).build();
	}

}
