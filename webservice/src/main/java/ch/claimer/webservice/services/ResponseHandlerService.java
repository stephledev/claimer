package ch.claimer.webservice.services;

public interface ResponseHandlerService<V> {
	public ResponseHandlerService<V> success();
	public ResponseHandlerService<V> entity(String model);
	public V build();
}
