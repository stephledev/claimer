package ch.claimer.webservice.app;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.CompanyController;
import ch.claimer.webservice.injector.CompanyControllerInjector;


public class Test {

	public static void main(String[] args) {

		Injector injector = Guice.createInjector(new CompanyControllerInjector());
	    CompanyController companyController = injector.getInstance(CompanyController.class);
	    companyController.create();

	}

}
