package ch.claimer.webservice.services;

import java.util.List;

public interface DataProcessorService<T> {
	public T read(String data, Class<T> clazz);
	public String write(T t);
	public String write(List<T> list);
}
