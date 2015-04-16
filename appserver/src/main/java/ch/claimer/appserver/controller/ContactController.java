package ch.claimer.appserver.controller;

import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.appserver.methods.ContactMethod;
import ch.claimer.appserver.repositories.ContactRepository;
import ch.claimer.appserver.repositories.hibernate.HibernateContactRepository;
import ch.claimer.shared.models.Contact;

public class ContactController extends Controller<Contact> implements ContactMethod {

	private static final long serialVersionUID = 8151372517404937611L;
	private final ContactRepository contactRepository;
	
	public ContactController() throws RemoteException {
		super(Contact.class);
		this.contactRepository = new HibernateContactRepository();
	}
	
	public List<Contact> getByAll() {
		session.beginTransaction();
		List<Contact> contacts = repository.getAll();
		session.getTransaction().commit();
		return contacts;
	}
	
	public Contact getById(Integer id) {
		session.beginTransaction();
		Contact contact = repository.getById(id);
		session.getTransaction().commit();
		return contact;
	}
	
	public List<Contact> getBySubcontractor(Integer id) {
		session.beginTransaction();
		List<Contact> contacts = contactRepository.getBySubcontractor(id);
		session.getTransaction().commit();
		return contacts;
	}
	public Contact create(Contact contact) {
		session.beginTransaction();
		repository.create(contact);
		session.getTransaction().commit();
		return contact;
	}
	public Contact update(Contact contact) {
		session.beginTransaction();
		repository.update(contact);
		session.getTransaction().commit();
		return contact;
	}
}
