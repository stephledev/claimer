package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateSCEmployeeRepository;

/**
 * Handles comment specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Momcilo Bekcic
 * {@link ch.claimer.webservice.repositories}
 */
public class CommentController<T> extends DefaultController<SCEmployee> {
	
	private final CommentRepository repository;

	public CommentController() {
		super(Comment.class);
		this.repository = new HibernateCommentRepository();
	}

	/**
	 * Gets the comment instance by his issue from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the comment instance to show
	 * 
	 * @return Response with HTTP Status and comment data
	 */
	public Response showByIssue(int id) {
		String entityString = processor.write(repository.getByIssue(id));
		return Response.status(200).entity(entityString).build();
	}

}
