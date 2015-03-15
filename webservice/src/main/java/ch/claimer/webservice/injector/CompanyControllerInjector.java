package ch.claimer.webservice.injector;

import ch.claimer.webservice.repositories.CompanyRepository;
import ch.claimer.webservice.repositories.hibernate.HibernateCompanyRepository;

import com.google.inject.AbstractModule;

public class CompanyControllerInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(CompanyRepository.class).to(HibernateCompanyRepository.class);
		
	}

}
