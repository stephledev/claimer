package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.repositories.ContactRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateContactRepository;

/**
 * Handles contact specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Raoul Ackermann
 * {@link ch.claimer.webservice.repositories}
 */
public class ContactController<T> extends DefaultController<Contact> {
	
	private final ContactRepository repository;

	public ContactController() {
		super(Contact.class);
		this.repository = new HibernateContactRepository();
	}

	/**
	 * Gets the contact instance by his subcontractor from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the contact instance to show
	 * 
	 * @return Response with HTTP Status and contact data
	 */
	public Response showBySubcontractor(int id) {
		String entityString = processor.write(repository.getBySubcontractor(id));
		return Response.status(200).entity(entityString).build();
	}

}
