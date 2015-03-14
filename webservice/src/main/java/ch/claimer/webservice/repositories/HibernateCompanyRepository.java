package ch.claimer.webservice.repositories;

import java.util.List;

import ch.claimer.shared.models.Company;

public class HibernateCompanyRepository implements CompanyRepository<Integer>, Dao<Company, Integer> {
	
	private HibernateDao<Company, Integer> dao = new HibernateDao<Company, Integer>(Company.class);

	@Override
	public Company create(Company company) {
		return dao.create(company);
	}

	@Override
	public Company getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<Company> getAll() {
		return dao.getAll();
	}

	@Override
	public Company update(Company company) {
		return dao.update(company);
	}

	@Override
	public void delete(Company company) {
		dao.delete(company);
	}
}
