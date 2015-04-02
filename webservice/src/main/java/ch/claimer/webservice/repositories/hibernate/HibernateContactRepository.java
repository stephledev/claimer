package ch.claimer.webservice.repositories.hibernate;

import java.util.List;

import org.hibernate.Query;

import ch.claimer.shared.models.Contact;
import ch.claimer.webservice.repositories.ContactRepository;
import ch.claimer.webservice.services.HibernateService;

public class HibernateContactRepository implements ContactRepository {
	
	private final HibernateService hibernate;
	
	public HibernateContactRepository() {
        this.hibernate = new HibernateService();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getBySubcontractor(Integer id) {
		Query query = hibernate.openSession().createQuery("select c from Contact c inner join c.subcontractor s where s.id = "+ id);
		List<Contact> list = query.list();
		hibernate.closeSession();
		return list;
	}

}
