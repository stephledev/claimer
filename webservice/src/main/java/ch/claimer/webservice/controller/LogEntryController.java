package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.LogEntry;
import ch.claimer.webservice.repositories.LogEntryRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateLogEntryRepository;

/**
 * Handles contact specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Raoul Ackermann
 * {@link ch.claimer.webservice.repositories}
 */
public class LogEntryController<T> extends DefaultController<LogEntry> {
	
	private final LogEntryRepository repository;

	public LogEntryController() {
		super(LogEntry.class);
		this.repository = new HibernateLogEntryRepository();
	}

	/**
	 * Gets the LogEntry instance by his issue from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the LogEntry instance to show
	 * 
	 * @return Response with HTTP Status and contact data
	 */
	public Response showByIssue(int id) {
		String entityString = processor.write(repository.getByIssue(id));
		return Response.status(200).entity(entityString).build();
	}
	
	/**
	 * Gets the LogEntry instance by his project from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the LogEntry instance to show
	 * 
	 * @return Response with HTTP Status and contact data
	 */
	public Response showByProject(int id) {
		String entityString = processor.write(repository.getByProject(id));
		return Response.status(200).entity(entityString).build();
	}

}
