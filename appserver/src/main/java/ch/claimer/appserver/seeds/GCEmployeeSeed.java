package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GCEmployee;

/** 
 * @author Fabio Baviera
 */

public class GCEmployeeSeed extends Seed<GCEmployee> {
	
	public GCEmployeeSeed() {
		this.repository = new EclipseLinkRepository<GCEmployee>(GCEmployee.class);
		
	}

	@Override
	public void setup() {
		GCEmployee GCEmployee1 = new GCEmployee();
		GCEmployee1.setLastname("Baviera");
		GCEmployee1.setFirstname("Fabio");
		GCEmployee1.setEmail("fabio.baviera@stud.hslu.ch");
		GCEmployee1.setPhone("041 111 11 11");
		seeds.get("Login").get(0);
		
		GCEmployee GCEmployee2 = new GCEmployee();
		GCEmployee2.setLastname("Ackermann");
		GCEmployee2.setFirstname("Raoul");
		GCEmployee2.setEmail("raoul.ackermann@stud.hslu.ch");
		GCEmployee2.setPhone("0416457865");
		seeds.get("Login").get(1);
		
		GCEmployee GCEmployee3 = new GCEmployee();
		GCEmployee3.setLastname("Beeler");
		GCEmployee3.setFirstname("Stephan");
		GCEmployee3.setEmail("stephan.beeler@stud.hslu.ch");
		GCEmployee3.setPhone("0443214657");
		seeds.get("Login").get(2);
		
		GCEmployee GCEmployee4 = new GCEmployee();
		GCEmployee4.setLastname("Stadelmann");
		GCEmployee4.setFirstname("Kevin");
		GCEmployee4.setEmail("kevin.stadelmann@stud.hslu.ch");
		GCEmployee4.setPhone("0433219324");
		seeds.get("Login").get(3);
		
		GCEmployee GCEmployee5 = new GCEmployee();
		GCEmployee5.setLastname("Lötscher");
		GCEmployee5.setFirstname("Michael");
		GCEmployee5.setEmail("michael.loetscher@stud.hslu.ch");
		GCEmployee5.setPhone("0446754354");
		seeds.get("Login").get(4);
		
		GCEmployee GCEmployee6 = new GCEmployee();
		GCEmployee6.setLastname("Bekcic");
		GCEmployee6.setFirstname("Momcilo");
		GCEmployee6.setEmail("momcilo.bekcic@stud.hslu.ch");
		GCEmployee6.setPhone("0446754354");
		seeds.get("Login").get(5);
		
		GCEmployee GCEmployee7 = new GCEmployee();
		GCEmployee7.setLastname("Hauck");
		GCEmployee7.setFirstname("Alexander");
		GCEmployee7.setEmail("alexander.hauck@stud.hslu.ch");
		GCEmployee7.setPhone("0446754354");
		seeds.get("Login").get(6);
		
		list.add(GCEmployee1);
		list.add(GCEmployee2);
		list.add(GCEmployee3);
		list.add(GCEmployee4);
		list.add(GCEmployee5);
		list.add(GCEmployee6);
		list.add(GCEmployee7);
		
		this.seeds.put("GCEmployee", list);
	}

}
