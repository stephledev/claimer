package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.repositories.IssueRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateIssueRepository;

/**
 * Handles issue specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Raoul Ackermann
 * {@link ch.claimer.webservice.repositories}
 */
public class IssueController<T> extends DefaultController<Issue> {
	
	private final IssueRepository repository;

	public IssueController() {
		super(Issue.class);
		this.repository = new HibernateIssueRepository();
	}

	/**
	 * Gets the issue instance by his contact from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the issue instance to show
	 * 
	 * @return Response with HTTP Status and issue data
	 */
	public Response showByContact(int id) {
		String entityString = processor.write(repository.getByContact(id));
		return Response.status(200).entity(entityString).type(type).build();
	}

	/**
	 * Gets the issue instance by his supervisor from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the issue instance to show
	 * 
	 * @return Response with HTTP Status and issue data
	 */
	public Response showBySupervisor(int id) {
		String entityString = processor.write(repository.getBySupervisor(id));
		return Response.status(200).entity(entityString).type(type).build();
	}
	
	/**
	 * Gets the issue instance by his state from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the issue instance to show
	 * 
	 * @return Response with HTTP Status and issue data
	 */
	public Response showByState(int id) {
		String entityString = processor.write(repository.getByState(id));
		return Response.status(200).entity(entityString).type(type).build();
	}
	
	/**
	 * Gets the issue instance by his project from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the issue instance to show
	 * 
	 * @return Response with HTTP Status and issue data
	 */
	public Response showByProject(int id) {
		String entityString = processor.write(repository.getByProject(id));
		return Response.status(200).entity(entityString).type(type).build();
	}
	
}
