package ch.claimer.appserver.repositories;

import java.util.List;

import ch.claimer.shared.models.Project;

public interface ProjectRepository {
	
	List<Project> getByCategory(Integer id);
	List<Project> getByType(Integer id);
	List<Project> getBySupervisor(Integer id);
	List<Project> getByState(Integer id);
	List<Project> getByGCEmployee(Integer id);
}
