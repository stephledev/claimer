package ch.claimer.appserver.methods;

import java.util.List;

import ch.claimer.shared.models.Project;

public interface ProjectMethod extends Method<Project> {
	public List<Project> getBySupervisor(Integer id);
}
