package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import ch.claimer.appserver.repositories.Repository;
import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Model;

/**
 * Implementiert die verfügbaren RMI-Methoden.
 * @see Method
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entität, die behandelt werden soll
 */
public class Controller<T extends Model> extends UnicastRemoteObject implements
		Method<T> {

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
	public T getById(int id) {
		return repository.getById(id);
	}

	@Override
	public List<T> getByProperty(String name, Object value) {
		return repository.getByProperty(name, value);
	}

	@Override
	public List<T> getByRelation(Class<?> relation, int id) {
		return repository.getByRelation(relation, id);
	}
	
	@Override
	public List<T> getByRelations(Class<?> relation, int id) {
		return repository.getByRelations(relation, id);
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
	public Integer delete(int id) {
		repository.delete(id);
		return id;
	}

}
