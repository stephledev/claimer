package ch.claimer.webservice.repositories.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.claimer.webservice.repositories.Dao;
import ch.claimer.webservice.util.HibernateUtil;

public class HibernateDao<T, Id extends Serializable> implements Dao<T, Id> {
	
	private Session currentSession;
	private Transaction currentTransaction;
	private final Class<T> clazz;
	
	public HibernateDao(Class<T> clazz) {
        this.clazz = clazz;
    }

	public Session openCurrentSession() {
		if(currentSession == null) {
			currentSession = HibernateUtil.getSessionFactory().openSession();
		}
		return currentSession;
	}
	
	public Session openCurrentSessionwithTransaction() {
		currentTransaction = openCurrentSession().beginTransaction();
		return currentSession;
	}
	
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}	

	@Override
	public T create(T t) {
		openCurrentSessionwithTransaction();
		currentSession.save(t);
		closeCurrentSessionwithTransaction();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Id id) {
		openCurrentSession();
		T t = (T) currentSession.get(clazz, id);
		closeCurrentSession();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		openCurrentSession();
		List<T> list = (List<T>) currentSession.createQuery("from " + clazz.getName()).list();
		closeCurrentSession();
		return list;
	}

	@Override
	public T update(T t) {
		openCurrentSessionwithTransaction();
		currentSession.update(t);
		closeCurrentSessionwithTransaction();
		return t;
	}

	@Override
	public void delete(T t) {
		openCurrentSessionwithTransaction();
		currentSession.delete(t);
		closeCurrentSessionwithTransaction();
		
	}

}
