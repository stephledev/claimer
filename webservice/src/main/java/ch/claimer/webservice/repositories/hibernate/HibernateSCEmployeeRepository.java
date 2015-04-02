package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;

import ch.claimer.shared.models.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.services.HibernateService;

public class HibernateSCEmployeeRepository implements SCEmployeeRepository {
	
	private final HibernateService hibernate;
	
	public HibernateSCEmployeeRepository() {
        this.hibernate = new HibernateService();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<SCEmployee> getBySubcontractor(Integer id) {
		Query query = hibernate.openSession().createQuery("select e from SCEmployee e inner join e.subcontractor s where s.id = "+ id);
		List<SCEmployee> list = query.list();
		hibernate.closeSession();
		return list;
	}

}
