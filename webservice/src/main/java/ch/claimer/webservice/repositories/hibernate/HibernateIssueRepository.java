package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;

import ch.claimer.shared.models.Issue;
import ch.claimer.webservice.repositories.IssueRepository;
import ch.claimer.webservice.services.HibernateService;

public class HibernateIssueRepository implements IssueRepository {
	
	private final HibernateService hibernate;
	
	public HibernateIssueRepository() {
        this.hibernate = new HibernateService();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getByContact(Integer id) {
		Query query = hibernate.openSession().createQuery("select i from Issue i inner join i.contact c where c.id = "+ id);  
		List<Issue> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getBySupervisor(Integer id) {
		Query query = hibernate.openSession().createQuery("select i from Issue i inner join i.supervisor s where s.id = "+ id);  
		List<Issue> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getByState(Integer id) {
		Query query = hibernate.openSession().createQuery("select i from Issue i inner join i.state s where s.id = "+ id);  
		List<Issue> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Issue> getByProject(Integer id) {
		Query query = hibernate.openSession().createQuery("select i from Issue i inner join i.project p where p.id = "+ id);  
		List<Issue> list = query.list();
		hibernate.closeSession();
		return list;
	}

}
