package ch.claimer.webservice.injector;

import ch.claimer.webservice.service.DataProcessorService;
import ch.claimer.webservice.service.JsonDataProcessorService;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class binderModule implements Module {
	public void configure(Binder binder) {
	   binder.install(new FactoryModuleBuilder()
	         .implement(DataProcessorService.class, JsonDataProcessorService.class)
	         .build(DataProcessorServiceFactory.class));
	 }

}
