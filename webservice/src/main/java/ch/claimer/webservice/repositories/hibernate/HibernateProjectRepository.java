package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.claimer.shared.models.Project;
import ch.claimer.webservice.repositories.ProjectRepository;
import ch.claimer.webservice.util.HibernateUtil;

public class HibernateProjectRepository implements ProjectRepository {
	
	private Session session;
	
	public HibernateProjectRepository() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByCategory(Integer id) {
		Query query = session.createQuery("select p from Project p inner join p.category c where c.id = "+ id);  
		List<Project> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByType(Integer id) {
		Query query = session.createQuery("select p from Project p inner join p.type t where t.id = "+ id);  
		List<Project> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getBySupervisor(Integer id) {
		Query query = session.createQuery("select p from Project p inner join p.supervisor s where s.id = "+ id);  
		List<Project> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByState(Integer id) {
		Query query = session.createQuery("select p from Project p inner join p.state s where s.id = "+ id);  
		List<Project> list = query.list();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByGCEmployee(Integer id) {
		Query query = session.createQuery("select p from Project p inner join p.gcemployee g where g.id = "+ id);  
		List<Project> list = query.list();
		return list;
	}

}
