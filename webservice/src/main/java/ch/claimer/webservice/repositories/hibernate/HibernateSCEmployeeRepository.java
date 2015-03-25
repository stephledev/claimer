package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import ch.claimer.shared.entities.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;

public class HibernateSCEmployeeRepository implements SCEmployeeRepository {
	
	private final HibernateDao<SCEmployee, Integer> dao = new HibernateDao<SCEmployee, Integer>(SCEmployee.class);
	
	@Override
	public SCEmployee store(SCEmployee employee) {
		return dao.store(employee);
	}

	@Override
	public SCEmployee getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<SCEmployee> getAll() {
		return dao.getAll();
	}

	@Override
	public SCEmployee update(SCEmployee employee) {
		return dao.update(employee);
	}

	@Override
	public void destroy(SCEmployee employee) {
		dao.destroy(employee);
	}
}
