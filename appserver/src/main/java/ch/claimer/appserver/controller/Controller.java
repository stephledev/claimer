package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;

import ch.claimer.appserver.methods.Method;
import ch.claimer.appserver.repositories.DefaultRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.appserver.util.HibernateUtil;

public abstract class Controller<T> extends UnicastRemoteObject implements Method<T> {

	private static final long serialVersionUID = -2398155992360834786L;
	protected final Class<T> clazz;
	protected final DefaultRepository<T> repository;
	protected Session session;
	
	public Controller(Class<T> clazz) throws RemoteException {
		this.clazz = clazz;
		this.repository = new HibernateDefaultRepository<T>(clazz);
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public List<T> getAll() {
		session.beginTransaction();
		List<T> list = repository.getAll();
		session.getTransaction().commit();
		return list;
	}

	@Override
	public T getById(Integer id) {
		session.beginTransaction();
		T t = repository.getById(id);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public T create(T t) {
		session.beginTransaction();
		repository.create(t);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public T update(T t) {
		session.beginTransaction();
		repository.update(t);
		session.getTransaction().commit();
		return t;
	}

	@Override
	public Integer delete(Integer id) {
		session.beginTransaction();
		repository.delete(id);
		session.getTransaction().commit();
		return id;
	}
	
}
