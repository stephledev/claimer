package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Image;
import ch.claimer.webservice.repositories.ImageRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateImageRepository;

/**
 * Handles issue specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Raoul Ackermann
 * {@link ch.claimer.webservice.repositories}
 */
public class ImageController<T> extends DefaultController<Image> {
	
	private final ImageRepository repository;

	public ImageController() {
		super(Image.class);
		this.repository = new HibernateImageRepository();
	}

	/**
	 * Gets the image instance by his issue from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the image instance to show
	 * 
	 * @return Response with HTTP Status and image data
	 */
	public Response showByIssue(int id) {
		String entityString = processor.write(repository.getByIssue(id));
		return Response.status(200).entity(entityString).build();
	}

}
