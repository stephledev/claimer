package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Role;

/**
 * In der LoginSeed-Klasse werden die Logins den User zugeteilt mit den
 * jeweiligen Rechten die in den Rollen definiert sind. Die Klasse erbt von der
 * Klasse Seed und setzt Login als Typ-Variable.
 * 
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */

public class LoginSeed extends Seed<Login> {

	public LoginSeed() {
		this.repository = new EclipseLinkRepository<Login>(Login.class);
		this.seed = new ArrayList<Login>();
	}

	@Override
	public void setup() {

		Login log1 = new Login();
		log1.setUsername("Fabio_Baviera");
		log1.setPassword("fabio01");
		log1.setRole((Role) seeds.get("Role").get(0));

		Login log2 = new Login();
		log2.setUsername("Raoul_Ackermann");
		log2.setPassword("raoul01");
		log2.setRole((Role) seeds.get("Role").get(0));

		Login log3 = new Login();
		log3.setUsername("Stephan_Beeler");
		log3.setPassword("stephan01");
		log3.setRole((Role) seeds.get("Role").get(0));

		Login log4 = new Login();
		log4.setUsername("Kevin_Stadelmann");
		log4.setPassword("kevin01");
		log4.setRole((Role) seeds.get("Role").get(0));

		Login log5 = new Login();
		log5.setUsername("Michael_Lötscher");
		log5.setPassword("michael01");
		log5.setRole((Role) seeds.get("Role").get(0));

		Login log6 = new Login();
		log6.setUsername("Momcilo_Bekcic");
		log6.setPassword("momo01");
		log6.setRole((Role) seeds.get("Role").get(0));

		Login log7 = new Login();
		log7.setUsername("Alexander_Hauck");
		log7.setPassword("alex01");
		log7.setRole((Role) seeds.get("Role").get(0));

		// GU
		Login log8 = new Login();
		log8.setUsername("Peter_Meier");
		log8.setPassword("peter01");
		log8.setRole((Role) seeds.get("Role").get(1));

		// SU
		Login log9 = new Login();
		log9.setUsername("Alfred_Fischer");
		log9.setPassword("alf01");
		log9.setRole((Role) seeds.get("Role").get(2));

		// GUB Supervisor Sebastian Müller
		Login log10 = new Login();
		log10.setUsername("Sebastian_Müller");
		log10.setPassword("sebi01");
		log10.setRole((Role) seeds.get("Role").get(3));

		// SUC Contact Manfred Schmid
		Login log11 = new Login();
		log11.setUsername("Manfred_Schmid");
		log11.setPassword("mani01");
		log11.setRole((Role) seeds.get("Role").get(4));

		// Subcontractor Meier Maurer
		Login log12 = new Login();
		log12.setUsername("Michael_Meier");
		log12.setPassword("michi02");
		log12.setRole((Role) seeds.get("Role").get(2));

		// Subcontractor Müller Schreiner
		Login log13 = new Login();
		log13.setUsername("Fritz_Mueller");
		log13.setPassword("fritz01");
		log13.setRole((Role) seeds.get("Role").get(2));

		// Subcontractor Fischer Spenglerei
		Login log14 = new Login();
		log14.setUsername("Bernhard_Fischer");
		log14.setPassword("bern01");
		log14.setRole((Role) seeds.get("Role").get(2));

		// Subcontractor Gubler Gibser
		Login log15 = new Login();
		log15.setUsername("Robin_Gubler");
		log15.setPassword("robi01");
		log15.setRole((Role) seeds.get("Role").get(2));

		// Subcontractor Santis Sanitär
		Login log16 = new Login();
		log16.setUsername("Dennis_Santis");
		log16.setPassword("dennis01");
		log16.setRole((Role) seeds.get("Role").get(2));

		// SUC Contact Toni Fankhauser
		Login log17 = new Login();
		log17.setUsername("Toni_Fankhauser");
		log17.setPassword("toni01");
		log17.setRole((Role) seeds.get("Role").get(4));

		// SUC Contact Ueli Huber
		Login log18 = new Login();
		log18.setUsername("Ueli_Huber");
		log18.setPassword("ueli01");
		log18.setRole((Role) seeds.get("Role").get(4));

		// SUC Contact Anna Rodel
		Login log19 = new Login();
		log19.setUsername("Anna_Rodel");
		log19.setPassword("anna01");
		log19.setRole((Role) seeds.get("Role").get(4));

		// SUC Contact Kurt Häberli
		Login log20 = new Login();
		log20.setUsername("Kurt_Häberli");
		log20.setPassword("kurt01");
		log20.setRole((Role) seeds.get("Role").get(4));

		// GUB Supervisor Ferrari
		Login log21 = new Login();
		log21.setUsername("Ferrari_Enzo");
		log21.setPassword("enzo01");
		log21.setRole((Role) seeds.get("Role").get(3));

		seed.add(log1);
		seed.add(log2);
		seed.add(log3);
		seed.add(log4);
		seed.add(log5);
		seed.add(log6);
		seed.add(log7);
		seed.add(log8);
		seed.add(log9);
		seed.add(log10);
		seed.add(log11);
		seed.add(log12);
		seed.add(log13);
		seed.add(log14);
		seed.add(log15);
		seed.add(log16);
		seed.add(log17);
		seed.add(log18);
		seed.add(log19);
		seed.add(log20);
		seed.add(log21);

		Seed.seeds.put("Login", seed);
	}

}
