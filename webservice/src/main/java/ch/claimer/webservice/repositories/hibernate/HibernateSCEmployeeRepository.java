package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.util.HibernateUtil;

public class HibernateSCEmployeeRepository implements SCEmployeeRepository {
	
	private Session session;
	
	public HibernateSCEmployeeRepository() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<SCEmployee> getBySubcontractor(Integer id) {
		Query query = session.createQuery("select e from SCEmployee e inner join e.subcontractor s where s.id = "+ id);
		List<SCEmployee> list = query.list();
		return list;
	}

}
