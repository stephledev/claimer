package ch.claimer.webservice.injector;

import ch.claimer.webservice.service.DataProcessorService;

public interface DataProcessorServiceFactory<T> {
	DataProcessorService<T> create(Class<T> clazz);
}
