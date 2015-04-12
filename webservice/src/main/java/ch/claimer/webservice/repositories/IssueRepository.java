package ch.claimer.webservice.repositories;

import java.util.List;

import ch.claimer.shared.models.Issue;

public interface IssueRepository {
	
	List<Issue> getByContact(Integer id);
	List<Issue> getBySubcontractor(Integer id);
	List<Issue> getByState(Integer id);
	List<Issue> getByProject(Integer id);
	
}
