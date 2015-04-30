package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.SCEmployee;

/** 
 * @author Fabio Baviera
 */

public class SCEmployeeSeed extends Seed<SCEmployee> {
	
	public SCEmployeeSeed() {
		this.repository = new EclipseLinkRepository<SCEmployee>(SCEmployee.class);
		this.seed = new ArrayList<SCEmployee>();
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
		SCEmployee2.setLastname("M�ller");
		SCEmployee2.setFirstname("Fritz");
		SCEmployee2.setEmail("m�ller_schreiner@bluewin.ch");
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

		
		seed.add(SCEmployee1);
		seed.add(SCEmployee2);
		seed.add(SCEmployee3);
		seed.add(SCEmployee4);
		seed.add(SCEmployee5);
		
		Seed.seeds.put("SCEmployee", seed);
	}

}