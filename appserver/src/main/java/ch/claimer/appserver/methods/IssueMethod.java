package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Issue;

public interface IssueMethod extends Remote {
	public List<Issue> getByAll();
	public List<Issue> getById();
	public List<Issue> getByProject();
	public List<Issue> getByContact();
	public List<Issue> getBySubcontractor();
	public Issue create(Issue issue);
	public Issue update(Issue issue);
}
