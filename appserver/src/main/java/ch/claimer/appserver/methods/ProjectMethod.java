package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Project;

public interface ProjectMethod extends Remote {
	public List<Project> getAll();
	public Project getById();
	public Project create(Project project);
	public Project update(Project project);
}
