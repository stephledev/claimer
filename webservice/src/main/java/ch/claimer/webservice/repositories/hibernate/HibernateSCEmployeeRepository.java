package ch.claimer.webservice.repositories.hibernate;

import java.io.Serializable;
import java.util.List;

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
		List<SCEmployee> list = (List<SCEmployee>) hibernate.openSession().createQuery("from " + SCEmployee.class).list();
		hibernate.closeSession();
		return list;
	}
	
	


}
