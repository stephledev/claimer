package ch.claimer.webservice.controller;

import ch.claimer.shared.models.Comment;
import ch.claimer.webservice.repositories.CommentRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateCommentRepository;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Handles comment specific operations for RESTful  interactions. 
 * Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.webservice.repositories}
 */
public class CommentController<V> extends DefaultController<Comment, V> {
	
	private final CommentRepository repository;

	public CommentController(ResponseHandlerService<V> response) {
		super(Comment.class, response);
		this.repository = new HibernateCommentRepository();
	}

	/**
	 * Gets comment(s) by its supervisor from the repository and returns it.
	 * 
	 * @param id supervisor identifier of comment(s) to show
	 * 
	 * @return Response with status and comment data
	 */
	public V showBySupervisor(int id) {
		String entityString = processor.write(repository.getBySupervisor(id));
		return response.success().entity(entityString).build();
	}
	
	/**
	 * Gets comment(s) by its contact from the repository and returns it.
	 * 
	 * @param id contact identifier of comment(s) to show
	 * 
	 * @return Response with status and contact data
	 */
	public V showByContact(int id) {
		String entityString = processor.write(repository.getByContact(id));
		return response.success().entity(entityString).build();
	}
}
