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
		Contact1.setEmail("manfred.schmid@bluewin.ch");
		Contact1.setPhone("041 111 11 11");
		seeds.get("Login").get(10);
		seeds.get("Subcontractor").get(0);
		
		Contact Contact2 = new Contact();
		Contact2.setLastname("Fankhauser");
		Contact2.setFirstname("Toni");
		Contact2.setEmail("toni.fankhauser@bluewin.ch");
		Contact2.setPhone("041 111 11 99");
		seeds.get("Login").get(16);
		seeds.get("Subcontractor").get(1);
		
		Contact Contact3 = new Contact();
		Contact3.setLastname("Huber");
		Contact3.setFirstname("Ueli");
		Contact3.setEmail("ueli.huber@bluewin.ch");
		Contact3.setPhone("041 111 11 98");
		seeds.get("Login").get(17);
		seeds.get("Subcontractor").get(2);
		
		Contact Contact4 = new Contact();
		Contact4.setLastname("Rodel");
		Contact4.setFirstname("Anna");
		Contact4.setEmail("anna.rodel@bluewin.ch");
		Contact4.setPhone("041 111 11 97");
		seeds.get("Login").get(18);
		seeds.get("Subcontractor").get(3);
		
		Contact Contact5 = new Contact();
		Contact5.setLastname("Häbeli");
		Contact5.setFirstname("Kurt");
		Contact5.setEmail("kurt.häberli@bluewin.ch.ch");
		Contact5.setPhone("041 111 11 95");
		seeds.get("Login").get(19);
		seeds.get("Subcontractor").get(4);
	
		
		list.add(Contact1);
		list.add(Contact2);
		list.add(Contact3);
		list.add(Contact4);
		list.add(Contact5);
		
		
		this.seeds.put("Contact", list);
	}

}
