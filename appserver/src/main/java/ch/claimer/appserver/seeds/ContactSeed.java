package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Contact;

/** 
 * @author Fabio Baviera
 */

public class ContactSeed extends Seed<Contact> {
	
	public ContactSeed() {
		this.repository = new EclipseLinkRepository<Contact>(Contact.class);
		
	}

	@Override
	public void setup() {
		Contact Contact1 = new Contact();
		Contact1.setLastname("Schmid");
		Contact1.setFirstname("Manfred");
		Contact1.setEmail("manfred.schmid@bluewin.ch.ch");
		Contact1.setPhone("041 111 11 11");
		seeds.get("Login").get(10);
		
		
		
		list.add(Contact1);
		
		
		this.seeds.put("Contact", list);
	}

}
