package ch.claimer.appserver.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import ch.claimer.appserver.repositories.ContactRepository;
import ch.claimer.appserver.util.HibernateUtil;
import ch.claimer.shared.models.Contact;

public class HibernateContactRepository implements ContactRepository {
	
	private Session session;
	
	public HibernateContactRepository() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getBySubcontractor(Integer id) {
		Query query = session.createQuery("select c from Contact c inner join c.subcontractor s where s.id = "+ id);
		List<Contact> list = query.list();
		return list;
	}

}
