package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Login;

/**
 * In der GCEmployeeSeed-Klasse werden die Mitarbeiter des Generalunternehmens
 * in die Datenbank geschrieben mit den jeweiligen Rechten, welche zugleich alle
 * Mitglieder der Projektgruppe sind. Die Klasse erbt von der Klasse Seed und
 * setzt GCEmployee als Typ-Variable.
 * 
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */

public class GCEmployeeSeed extends Seed<GCEmployee> {

	public GCEmployeeSeed() {
		this.repository = new EclipseLinkRepository<GCEmployee>(
				GCEmployee.class);
		this.seed = new ArrayList<GCEmployee>();
	}

	@Override
	public void setup() {
		GCEmployee GCEmployee1 = new GCEmployee();
		GCEmployee1.setLastname("Baviera");
		GCEmployee1.setFirstname("Fabio");
		GCEmployee1.setEmail("fabio.baviera@stud.hslu.ch");
		GCEmployee1.setPhone("041 111 11 11");
		GCEmployee1.setLogin((Login) Seed.seeds.get("Login").get(0));

		GCEmployee GCEmployee2 = new GCEmployee();
		GCEmployee2.setLastname("Ackermann");
		GCEmployee2.setFirstname("Raoul");
		GCEmployee2.setEmail("raoul.ackermann@stud.hslu.ch");
		GCEmployee2.setPhone("0416457865");
		GCEmployee2.setLogin((Login) Seed.seeds.get("Login").get(1));

		GCEmployee GCEmployee3 = new GCEmployee();
		GCEmployee3.setLastname("Beeler");
		GCEmployee3.setFirstname("Stephan");
		GCEmployee3.setEmail("stephan.beeler@stud.hslu.ch");
		GCEmployee3.setPhone("0443214657");
		GCEmployee3.setLogin((Login) Seed.seeds.get("Login").get(2));

		GCEmployee GCEmployee4 = new GCEmployee();
		GCEmployee4.setLastname("Stadelmann");
		GCEmployee4.setFirstname("Kevin");
		GCEmployee4.setEmail("kevin.stadelmann@stud.hslu.ch");
		GCEmployee4.setPhone("0433219324");
		GCEmployee4.setLogin((Login) Seed.seeds.get("Login").get(3));

		GCEmployee GCEmployee5 = new GCEmployee();
		GCEmployee5.setLastname("Lötscher");
		GCEmployee5.setFirstname("Michael");
		GCEmployee5.setEmail("michael.loetscher@stud.hslu.ch");
		GCEmployee5.setPhone("0446754354");
		GCEmployee5.setLogin((Login) Seed.seeds.get("Login").get(4));

		GCEmployee GCEmployee6 = new GCEmployee();
		GCEmployee6.setLastname("Bekcic");
		GCEmployee6.setFirstname("Momcilo");
		GCEmployee6.setEmail("momcilo.bekcic@stud.hslu.ch");
		GCEmployee6.setPhone("0446754354");
		GCEmployee6.setLogin((Login) Seed.seeds.get("Login").get(5));

		GCEmployee GCEmployee7 = new GCEmployee();
		GCEmployee7.setLastname("Hauck");
		GCEmployee7.setFirstname("Alexander");
		GCEmployee7.setEmail("alexander.hauck@stud.hslu.ch");
		GCEmployee7.setPhone("0446754354");
		GCEmployee7.setLogin((Login) Seed.seeds.get("Login").get(6));
		
		GCEmployee GCEmployee8 = new GCEmployee();
		GCEmployee6.setLastname("GU Admin");
		GCEmployee6.setFirstname("Standard");
		GCEmployee6.setLogin((Login) Seed.seeds.get("Login").get(21));

		GCEmployee GCEmployee9 = new GCEmployee();
		GCEmployee7.setLastname("GU");
		GCEmployee7.setFirstname("Standard");
		GCEmployee7.setLogin((Login) Seed.seeds.get("Login").get(22));

		seed.add(GCEmployee1);
		seed.add(GCEmployee2);
		seed.add(GCEmployee3);
		seed.add(GCEmployee4);
		seed.add(GCEmployee5);
		seed.add(GCEmployee6);
		seed.add(GCEmployee7);
		seed.add(GCEmployee8);
		seed.add(GCEmployee9);

		Seed.seeds.put("GCEmployee", seed);
	}

}
