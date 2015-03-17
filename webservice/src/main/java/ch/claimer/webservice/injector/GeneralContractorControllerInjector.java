package ch.claimer.webservice.injector;

import ch.claimer.webservice.repositories.GeneralContractorRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateGeneralContractorRepository;

import com.google.inject.AbstractModule;

public class GeneralContractorControllerInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(GeneralContractorRepository.class).to(HibernateGeneralContractorRepository.class);
		
	}

}
