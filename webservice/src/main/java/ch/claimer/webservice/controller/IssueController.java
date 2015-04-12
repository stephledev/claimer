package ch.claimer.webservice.controller;

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.repositories.IssueRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateIssueRepository;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Handles issue specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Raoul Ackermann
 * {@link ch.claimer.webservice.repositories}
 */
public class IssueController<V> extends DefaultController<Issue, V> {
	
	private final IssueRepository repository;
	
	public IssueController(ResponseHandlerService<V> response) {
		super(Issue.class, response);
		this.repository = new HibernateIssueRepository();
	}

	/**
	 * Gets issue(s) by its project from the repository and returns it.
	 * 
	 * @param id project identifier of issue(s) to show
	 * 
	 * @return Response with status and issue data
	 */
	public V showByProject(int id) {
		String entityString = processor.write(repository.getByProject(id));
		return response.success().entity(entityString).build();
	}
	
	/**
	 * Gets issue(s) by its contact from the repository and returns it.
	 * 
	 * @param id contact identifier of issue(s) to show
	 * 
	 * @return Response with status and issue data
	 */
	public V showByContact(int id) {
		String entityString = processor.write(repository.getByContact(id));
		return response.success().entity(entityString).build();
	}
	
	/**
	 * Gets issue(s) by its subcontractor from the repository and returns it.
	 * 
	 * @param id subcontractor identifier of issue(s) to show
	 * 
	 * @return Response with status and issue data
	 */
	public V showBySubcontractor(int id) {
		String entityString = processor.write(repository.getBySubcontractor(id));
		return response.success().entity(entityString).build();
	}
	
}
