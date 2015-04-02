package ch.claimer.webservice.controller;

import javax.ws.rs.core.Response;

import ch.claimer.shared.models.Project;
import ch.claimer.webservice.repositories.ProjectRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateProjectRepository;

/**
 * Handles project specific operations for RESTful 
 * interactions. Retrieves and persists the data via repositories.
 * Generates responses with the model data in String format.
 * 
 * @author Momcilo Bekcic
 * {@link ch.claimer.webservice.repositories}
 */
public class ProjectController<T> extends DefaultController<Project> {
	
	private final ProjectRepository repository;

	public ProjectController() {
		super(Project.class);
		this.repository = new HibernateProjectRepository();
	}

	/**
	 * Gets the project instance by its category from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the project instance to show
	 * 
	 * @return Response with HTTP Status and project data
	 */
	public Response showByCategory(int id) {
		String entityString = processor.write(repository.getByCategory(id));
		return Response.status(200).entity(entityString).build();
	}
	
	/**
	 * Gets the project instance by his type from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the project instance to show
	 * 
	 * @return Response with HTTP Status and project data
	 */
	public Response showByType(int id) {
		String entityString = processor.write(repository.getByType(id));
		return Response.status(200).entity(entityString).build();
	}
	
	/**
	 * Gets the project instance by his supervisor from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the project instance to show
	 * 
	 * @return Response with HTTP Status and project data
	 */
	public Response showBySupervisor(int id) {
		String entityString = processor.write(repository.getBySupervisor(id));
		return Response.status(200).entity(entityString).build();
	}
	
	/**
	 * Gets the project instance by his state from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the project instance to show
	 * 
	 * @return Response with HTTP Status and project data
	 */
	public Response showByState(int id) {
		String entityString = processor.write(repository.getByState(id));
		return Response.status(200).entity(entityString).build();
	}
	
	/**
	 * Gets the project instance by his general constructor employee from the repository
	 * and returns it.
	 * 
	 * @param id primary key of the project instance to show
	 * 
	 * @return Response with HTTP Status and project data
	 */
	public Response showByGCEmployee(int id) {
		String entityString = processor.write(repository.getByGCEmployee(id));
		return Response.status(200).entity(entityString).build();
	}
	
	
	
	

}
