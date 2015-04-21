package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ch.claimer.appserver.methods.Method;
import ch.claimer.appserver.repositories.Repository;
import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;

public class Controller<T> extends UnicastRemoteObject implements Method<T> {

	private static final long serialVersionUID = -2398155992360834786L;
	protected final Class<T> clazz;
	protected final Repository<T> repository;
	
	public Controller(Class<T> clazz) throws RemoteException {
		this.clazz = clazz;
		this.repository = new EclipseLinkRepository<T>(clazz);
	}
	
	@Override
	public List<T> getAll() {
		return repository.getAll();
	}	

	@Override
	public T getById(Integer id) {
		return repository.getById(id);
	}
	
	@Override
	public List<T> getByProperty(String name, List<?> values) {
		return repository.getByProperty(name, values);
	}
	

	@Override
	public T create(T t) {
		repository.create(t);
		return t;
	}

	@Override
	public T update(T t) {
		repository.update(t);
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		repository.delete(id);
		return id;
	}
	
}
