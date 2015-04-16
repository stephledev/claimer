package ch.claimer.appserver.methods;

import java.util.List;

import ch.claimer.shared.models.Contact;

public interface ContactMethod extends Method<Contact> {
	public List<Contact> getBySubcontractor(Integer id);
}
