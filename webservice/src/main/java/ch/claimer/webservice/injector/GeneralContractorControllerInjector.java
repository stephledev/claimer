package ch.claimer.webservice.injector;

import ch.claimer.webservice.repositories.GeneralContractorRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateGeneralContractorRepository;
import ch.claimer.webservice.service.DataProcessorService;
import ch.claimer.webservice.service.JsonDataProcessorService;

import com.google.inject.AbstractModule;

public class GeneralContractorControllerInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(GeneralContractorRepository.class).to(HibernateGeneralContractorRepository.class);
		//bind(DataProcessorService.class).to(JsonDataProcessorService.class);
	}

}
