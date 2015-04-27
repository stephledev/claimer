package ch.claimer.appserver.repositories.eclipselink;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import ch.claimer.appserver.repositories.Repository;
import ch.claimer.appserver.util.EclipseLinkUtil;

public class EclipseLinkRepository<T> implements Repository<T> {
	
	private final Class<T> clazz;
	private final EntityManagerFactory factory;
	
	public EclipseLinkRepository(Class<T> clazz) {
        this.clazz = clazz;
        this.factory = EclipseLinkUtil.getEntityManagerFactory();
    }

	@Override
	public T getById(int id) {
		EntityManager em = factory.createEntityManager();
		T t = em.find(clazz, id);
		em.close();
		return t;
	}
	
	@Override
	public List<T> getAll() {
		EntityManager em = factory.createEntityManager();
		List<T> list = em.createQuery("SELECT t FROM " + clazz.getName() + " t", clazz).getResultList();
		em.close();
		return list;
	}
	
	@Override
	public List<T> getByProperty(String name, Object value) {
		EntityManager em = factory.createEntityManager();
		List<T> list = em.createQuery("SELECT t FROM " + clazz.getName() + " t WHERE t."+name + " = :value", clazz)
			.setParameter("value", value)
			.getResultList();
		em.close();
		System.out.println(list);
		return list;
	}
	
	@Override
	public T create(T t) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	@Override
	public T update(T t) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return t;
	}

	@Override
	public void delete(int id) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(id);
		em.getTransaction().commit();
		em.close();
	}
}
