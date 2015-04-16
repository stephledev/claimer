package ch.claimer.appserver.repositories;

import java.util.List;

import ch.claimer.shared.models.Contact;

public interface ContactRepository {
	
	List<Contact> getBySubcontractor(Integer id);
	
}
