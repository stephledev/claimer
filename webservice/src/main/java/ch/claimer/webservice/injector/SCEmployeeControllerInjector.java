package ch.claimer.webservice.injector;

import ch.claimer.shared.entities.SCEmployee;
import ch.claimer.webservice.repositories.SCEmployeeRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateSCEmployeeRepository;
import ch.claimer.webservice.service.DataProcessorService;
import ch.claimer.webservice.service.JsonDataProcessorService;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class SCEmployeeControllerInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(SCEmployeeRepository.class).to(HibernateSCEmployeeRepository.class);
	}

}
