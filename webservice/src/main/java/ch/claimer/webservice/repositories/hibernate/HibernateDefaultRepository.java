package ch.claimer.webservice.repositories.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import ch.claimer.webservice.repositories.DefaultRepository;
import ch.claimer.webservice.util.HibernateUtil;

public class HibernateDefaultRepository<T, Id extends Serializable> implements DefaultRepository<T, Id> {
	
	private Session session;
	private final Class<T> clazz;
	
	public HibernateDefaultRepository(Class<T> clazz) {
        this.clazz = clazz;
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

	@Override
	public T store(T t) {
		session.save(t);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Id id) {
		T t = (T) session.get(clazz, id);
		return t;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		List<T> list = (List<T>) session.createQuery("from " + clazz.getName()).list();
		return list;
	}

	@Override
	public T update(T t) {
		session.update(t);
		return t;
	}

	@Override
	public void destroy(Id id) {
		T t = (T) getById(id)	;
		session.delete(t);
	}

}
