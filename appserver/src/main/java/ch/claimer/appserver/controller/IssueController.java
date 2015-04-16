package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.appserver.methods.IssueMethod;
import ch.claimer.appserver.repositories.IssueRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateIssueRepository;
import ch.claimer.shared.models.Issue;

public class IssueController extends Controller<Issue> implements IssueMethod {
	
	private static final long serialVersionUID = 8023237994221064231L;
	private final IssueRepository issueRepository;
	
	public IssueController() throws RemoteException {
		super(Issue.class);
		this.issueRepository = new HibernateIssueRepository();
	}

	public List<Issue> getByProject(Integer id) {
		session.beginTransaction();
		List<Issue> issue = issueRepository.getByProject(id);
		session.getTransaction().commit();
		return issue;
	}
	
	public List<Issue> getByContact(Integer id) {
		session.beginTransaction();
		List<Issue> issue = issueRepository.getByContact(id);
		session.getTransaction().commit();
		return issue;
	}
	
	public List<Issue> getBySubcontractor(Integer id) {
		session.beginTransaction();
		List<Issue> issue = issueRepository.getBySubcontractor(id);
		session.getTransaction().commit();
		return issue;
	}

}
