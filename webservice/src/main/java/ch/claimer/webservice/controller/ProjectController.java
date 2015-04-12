package ch.claimer.webservice.controller;

import ch.claimer.shared.models.Project;
import ch.claimer.webservice.repositories.ProjectRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateProjectRepository;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Handles project specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Momcilo Bekcic
 * {@link ch.claimer.webservice.repositories}
 */
public class ProjectController<V> extends DefaultController<Project, V> {
	
	private final ProjectRepository repository;
	
	public ProjectController(ResponseHandlerService<V> response) {
		super(Project.class, response);
		this.repository = new HibernateProjectRepository();
	}

	/**
	 * Gets project(s) by its supervisor from the repository and returns it.
	 * 
	 * @param id supervisor identifier of issue(s) to show
	 * 
	 * @return Response with status and project data
	 */
	public V showBySupervisor(int id) {
		String entityString = processor.write(repository.getBySupervisor(id));
		return response.success().entity(entityString).build();
	}

}
