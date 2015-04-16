package ch.claimer.appserver.methods;

import java.util.List;

import ch.claimer.shared.models.Issue;

public interface IssueMethod extends Method<Issue> {
	public List<Issue> getByProject(Integer id);
	public List<Issue> getByContact(Integer id);
	public List<Issue> getBySubcontractor(Integer id);
}
