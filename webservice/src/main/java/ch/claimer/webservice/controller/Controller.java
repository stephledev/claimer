package ch.claimer.webservice.controller;

public interface Controller<T, V> {
	
	public V index();
	public V show(Integer id);
	public V store(String data);
	public V update(String data);
	public V destroy(Integer id);
	
}
