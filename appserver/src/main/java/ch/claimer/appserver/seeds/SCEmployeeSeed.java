package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.SCEmployee;

/** 
 * @author Fabio Baviera
 */

public class SCEmployeeSeed extends Seed<SCEmployee> {
	
	public SCEmployeeSeed() {
		this.repository = new EclipseLinkRepository<SCEmployee>(SCEmployee.class);
		
	}

	@Override
	public void setup() {
		SCEmployee SCEmployee1 = new SCEmployee();
		SCEmployee1.setLastname("Meier");
		SCEmployee1.setFirstname("Michael");
		SCEmployee1.setEmail("meier_maurer@bluewin.ch");
		SCEmployee1.setPhone("0413214354");
		seeds.get("Subcontractor").get(0);
		seeds.get("Login").get(11);
		
		SCEmployee SCEmployee2 = new SCEmployee();
		SCEmployee2.setLastname("Müller");
		SCEmployee2.setFirstname("Fritz");
		SCEmployee2.setEmail("müller_schreiner@bluewin.ch");
		SCEmployee2.setPhone("0416457865");
		seeds.get("Subcontractor").get(1);
		seeds.get("Login").get(12);
		
		SCEmployee SCEmployee3 = new SCEmployee();
		SCEmployee3.setLastname("Fischer");
		SCEmployee3.setFirstname("Bernhard");
		SCEmployee3.setEmail("spenglerei_fischer@bluewin.ch");
		SCEmployee3.setPhone("0443214657");
		seeds.get("Subcontractor").get(2);
		seeds.get("Login").get(13);
		
		SCEmployee SCEmployee4 = new SCEmployee();
		SCEmployee4.setLastname("Gubler");
		SCEmployee4.setFirstname("Robin");
		SCEmployee4.setEmail("gubler@bluewin.ch");
		SCEmployee4.setPhone("0433219324");
		seeds.get("Subcontractor").get(3);
		seeds.get("Login").get(14);
		
		SCEmployee SCEmployee5 = new SCEmployee();
		SCEmployee5.setLastname("Santis");
		SCEmployee5.setFirstname("Dennis");
		SCEmployee5.setEmail("santis_sanitaer@hotmail.ch");
		SCEmployee5.setPhone("0446754354");
		seeds.get("Subcontractor").get(4);
		seeds.get("Login").get(15);

		
		list.add(SCEmployee1);
		list.add(SCEmployee2);
		list.add(SCEmployee3);
		list.add(SCEmployee4);
		list.add(SCEmployee5);
		
		this.seeds.put("SCEmployee", list);
	}

}
