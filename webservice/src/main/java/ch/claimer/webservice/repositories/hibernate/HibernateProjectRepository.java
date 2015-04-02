package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;

import ch.claimer.shared.models.Project;
import ch.claimer.webservice.repositories.ProjectRepository;
import ch.claimer.webservice.services.HibernateService;

public class HibernateProjectRepository implements ProjectRepository {
	
	private final HibernateService hibernate;
	
	public HibernateProjectRepository() {
        this.hibernate = new HibernateService();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByCategory(Integer id) {
		Query query = hibernate.openSession().createQuery("select p from Project p inner join p.category c where c.id = "+ id);  
		List<Project> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByType(Integer id) {
		Query query = hibernate.openSession().createQuery("select p from Project p inner join p.type t where t.id = "+ id);  
		List<Project> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getBySupervisor(Integer id) {
		Query query = hibernate.openSession().createQuery("select p from Project p inner join p.supervisor s where s.id = "+ id);  
		List<Project> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByState(Integer id) {
		Query query = hibernate.openSession().createQuery("select p from Project p inner join p.state s where s.id = "+ id);  
		List<Project> list = query.list();
		hibernate.closeSession();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getByGCEmployee(Integer id) {
		Query query = hibernate.openSession().createQuery("select p from Project p inner join p.gcemployee g where g.id = "+ id);  
		List<Project> list = query.list();
		hibernate.closeSession();
		return list;
	}

}
