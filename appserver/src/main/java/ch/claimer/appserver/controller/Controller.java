package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.hibernate.Session;

import ch.claimer.appserver.repositories.DefaultRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateDefaultRepository;
import ch.claimer.appserver.util.HibernateUtil;

public abstract class Controller<T> extends UnicastRemoteObject {

	private static final long serialVersionUID = -2398155992360834786L;
	protected final Class<T> clazz;
	protected final DefaultRepository<T> repository;
	protected Session session;
	
	public Controller(Class<T> clazz) throws RemoteException {
		this.clazz = clazz;
		this.repository = new HibernateDefaultRepository<T>(clazz);
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
}
