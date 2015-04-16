package ch.claimer.appserver.methods;

import java.rmi.Remote;
import java.util.List;

import ch.claimer.shared.models.Contact;

public interface ContactMethod extends Remote {
	public List<Contact> getByAll();
	public Contact getById(Integer id);
	public List<Contact> getBySubcontractor(Integer id);
	public Contact create(Contact contact);
	public Contact update(Contact contact);
}
