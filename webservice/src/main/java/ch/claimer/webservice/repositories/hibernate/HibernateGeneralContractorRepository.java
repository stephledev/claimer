package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import ch.claimer.shared.models.GeneralContractor;
import ch.claimer.webservice.repositories.GeneralContractorRepository;

public class HibernateGeneralContractorRepository implements GeneralContractorRepository {
	
	private final HibernateDao<GeneralContractor, Integer> dao = new HibernateDao<GeneralContractor, Integer>(GeneralContractor.class);
	
	@Override
	public GeneralContractor create(GeneralContractor generalContractor) {
		return dao.create(generalContractor);
	}

	@Override
	public GeneralContractor getById(Integer id) {
		return dao.getById(id);
	}

	@Override
	public List<GeneralContractor> getAll() {
		return dao.getAll();
	}

	@Override
	public GeneralContractor update(GeneralContractor generalContractor) {
		return dao.update(generalContractor);
	}

	@Override
	public void delete(GeneralContractor generalContractor) {
		dao.delete(generalContractor);
	}
}
