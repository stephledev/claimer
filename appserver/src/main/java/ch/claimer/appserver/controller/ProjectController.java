package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.appserver.methods.ProjectMethod;
import ch.claimer.appserver.repositories.ProjectRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateProjectRepository;
import ch.claimer.shared.models.Project;


public class ProjectController extends Controller<Project> implements ProjectMethod {

	private static final long serialVersionUID = -2905267943004594013L;
	private final ProjectRepository projectRepository;
	
	protected ProjectController() throws RemoteException {
		super(Project.class);
		this.projectRepository = new HibernateProjectRepository();
	}
	
	public List<Project> getBySupervisor(Integer id) {
		session.beginTransaction();
		List<Project> projects = projectRepository.getBySupervisor(id);
		session.getTransaction().commit();
		return projects;
	}
}
