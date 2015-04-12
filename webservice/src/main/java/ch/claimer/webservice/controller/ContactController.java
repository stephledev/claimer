package ch.claimer.webservice.controller;

import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.repositories.ContactRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateContactRepository;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Handles comment specific operations for RESTful  interactions. 
 * Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Stephan Beeler
 * {@link ch.claimer.webservice.repositories}
 */
public class ContactController<V> extends DefaultController<Contact, V> {
	
	private final ContactRepository repository;

	public ContactController(ResponseHandlerService<V> response) {
		super(Contact.class, response);
		this.repository = new HibernateContactRepository();
	}

	/**
	 * Gets comment(s) by its supervisor from the repository and returns it.
	 * 
	 * @param id subcontractor identifier of contact(s) to show
	 * 
	 * @return Response with status and comment data
	 */
	public V showBySubcontractor(int id) {
		String entityString = processor.write(repository.getBySubcontractor(id));
		return response.success().entity(entityString).build();
	}
}
