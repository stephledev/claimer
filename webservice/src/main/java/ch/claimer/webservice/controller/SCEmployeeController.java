package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateSCEmployeeRepository;

/**
 * Handles subcontractor employee specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Momcilo Bekcic
 * {@link ch.claimer.webservice.repositories}
 */
public class SCEmployeeController<T> extends DefaultController<SCEmployee> {
	
	private final SCEmployeeRepository repository;

	public SCEmployeeController() {
		super(SCEmployee.class);
		this.repository = new HibernateSCEmployeeRepository();
	}

	/**
	 * Gets the subcontractor employee instance by his subcontractor from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the subcontractor employee instance to show
	 * 
	 * @return Response with HTTP Status and subcontractor employee data
	 */
	public Response showBySubcontractor(int id) {
		String entityString = processor.write(repository.getBySubcontractor(id));
		return Response.status(200).entity(entityString).build();
	}

}
