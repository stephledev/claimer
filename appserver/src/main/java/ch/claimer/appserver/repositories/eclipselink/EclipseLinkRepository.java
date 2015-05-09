package ch.claimer.appserver.repositories.eclipselink;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.pmw.tinylog.Logger;

import ch.claimer.appserver.repositories.Repository;
import ch.claimer.appserver.util.EclipseLinkUtil;
import ch.claimer.shared.models.Model;

/**
 * Implementiert das Repository-Interface mithilfe EclipseLink
 * @see <a href="http://www.eclipse.org/eclipselink/api/2.5/">EclipseLink</a>
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entität, die behandlet werden soll
 */
public class EclipseLinkRepository<T extends Model> implements Repository<T> {

	private final Class<T> clazz;
	private final EntityManagerFactory factory;

	public EclipseLinkRepository(Class<T> clazz) {
		this.clazz = clazz;
		this.factory = EclipseLinkUtil.getEntityManagerFactory();
	}

	@Override
	public T getById(int id) {
		T t = null;
		try {
			EntityManager em = factory.createEntityManager();
			t = em.find(clazz, id);
			em.close();

		} catch (Exception e) {
			Logger.error(e, "Probleme mit der Verbindung zur Datenbank");
		}
		return t;

	}

	@Override
	public List<T> getAll() {
		EntityManager em = factory.createEntityManager();
		List<T> list = em.createQuery(
				"SELECT t FROM " + clazz.getName() + " t", clazz)
				.getResultList();
		em.close();
		return list;
	}

	@Override
	public List<T> getByProperty(String name, Object value) {
		EntityManager em = factory.createEntityManager();
		List<T> list = em
				.createQuery(
						"SELECT t FROM " + clazz.getName() + " t WHERE t."
								+ name + " = :value", clazz)
				.setParameter("value", value).getResultList();
		em.close();
		return list;
	}

	@Override
	public List<T> getByRelation(Class<?> relation, int id) {
		EntityManager em = factory.createEntityManager();
		List<T> list = em
				.createQuery(
						"SELECT t FROM " + clazz.getName() + " t WHERE t."
								+ relation.getSimpleName().toLowerCase()
								+ " = :value", clazz)
				.setParameter("value", em.find(relation, id)).getResultList();
		em.close();
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
		em.remove(em.find(this.clazz, id));
		em.getTransaction().commit();
		em.close();
	}
}
