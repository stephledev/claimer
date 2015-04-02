package ch.claimer.webservice.repositories.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import ch.claimer.webservice.repositories.DefaultRepository;
import ch.claimer.webservice.services.HibernateService;

public class HibernateDefaultRepository<T, Id extends Serializable> implements DefaultRepository<T, Id> {
	
	private final HibernateService hibernate;
	private final Class<T> clazz;
	
	public HibernateDefaultRepository(Class<T> clazz) {
        this.clazz = clazz;
        this.hibernate = new HibernateService();
    }

	@Override
	public T store(T t) {
		hibernate.openSessionwithTransaction().save(t);
		hibernate.closeSessionwithTransaction();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Id id) {
		T t = (T) hibernate.openSession().get(clazz, id);
		hibernate.closeSession();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		List<T> list = (List<T>) hibernate.openSession().createQuery("from " + clazz.getName()).list();
		hibernate.closeSession();
		return list;
	}

	@Override
	public T update(T t) {
		hibernate.openSessionwithTransaction().update(t);
		hibernate.closeSessionwithTransaction();
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void destroy(Id id) {
		Session session = hibernate.openSessionwithTransaction();
		T t = (T) session.get(clazz, id);		
		session.delete(t);
		hibernate.closeSessionwithTransaction();	
	}

}
