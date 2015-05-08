package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Subcontractor;

/** 
 * In der SCEployeeSeed-Klasse werden die Mitarbeiter der Subunternehmer
 * erstellt und in die Datenbank geschrieben.
 * Die Klasse erbt von der Klasse Seed und setzt SCEmployee als Generic.
 * 
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
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
		SCEmployee1.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(0));
		SCEmployee1.setLogin((Login) seeds.get("Login").get(11));
		
		SCEmployee SCEmployee2 = new SCEmployee();
		SCEmployee2.setLastname("Müller");
		SCEmployee2.setFirstname("Fritz");
		SCEmployee2.setEmail("müller_schreiner@bluewin.ch");
		SCEmployee2.setPhone("0416457865");
		SCEmployee2.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(1));
		SCEmployee2.setLogin((Login) seeds.get("Login").get(12));
		
		SCEmployee SCEmployee3 = new SCEmployee();
		SCEmployee3.setLastname("Fischer");
		SCEmployee3.setFirstname("Bernhard");
		SCEmployee3.setEmail("spenglerei_fischer@bluewin.ch");
		SCEmployee3.setPhone("0443214657");
		SCEmployee3.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(2));
		SCEmployee3.setLogin((Login) seeds.get("Login").get(13));
	
		SCEmployee SCEmployee4 = new SCEmployee();
		SCEmployee4.setLastname("Gubler");
		SCEmployee4.setFirstname("Robin");
		SCEmployee4.setEmail("gubler@bluewin.ch");
		SCEmployee4.setPhone("0433219324");
		SCEmployee4.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(3));
		SCEmployee4.setLogin((Login) seeds.get("Login").get(14));
		
		SCEmployee SCEmployee5 = new SCEmployee();
		SCEmployee5.setLastname("Santis");
		SCEmployee5.setFirstname("Dennis");
		SCEmployee5.setEmail("santis_sanitaer@hotmail.ch");
		SCEmployee5.setPhone("0446754354");
		SCEmployee5.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(4));
		SCEmployee5.setLogin((Login) seeds.get("Login").get(15));

		
		seed.add(SCEmployee1);
		seed.add(SCEmployee2);
		seed.add(SCEmployee3);
		seed.add(SCEmployee4);
		seed.add(SCEmployee5);
		
		Seed.seeds.put("SCEmployee", seed);
	}

}
