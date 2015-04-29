package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Login;

/**
 * 
 * 
 * @author Fabio Baviera
 */

public class LoginSeed extends Seed<Login> {
	
	public LoginSeed() {
		this.repository = new EclipseLinkRepository<Login>(Login.class);
		this.list = new ArrayList<Login>();
	}

	@Override
	public void setup() {
	
		Login log1 = new Login();
		log1.setUsername("Fabio_Baviera");
		log1.setPassword("fabio01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log2 = new Login();
		log2.setUsername("Raoul_Ackermann");
		log2.setPassword("raoul01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log3 = new Login();
		log3.setUsername("Stephan_Beeler");
		log3.setPassword("stephan01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log4 = new Login();
		log4.setUsername("Kevin_Stadelmann");
		log4.setPassword("kevin01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log5 = new Login();
		log5.setUsername("Michael_Lötscher");
		log5.setPassword("michael01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log6 = new Login();
		log6.setUsername("Momcilo_Bekcic");
		log6.setPassword("momo01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log7 = new Login();
		log7.setUsername("Alexander_Hauck");
		log7.setPassword("alex01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		//GU
		Login log8 = new Login();
		log8.setUsername("Peter_Meier");
		log8.setPassword("peter01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(3);
		
		//SU
		Login log9 = new Login();
		log9.setUsername("Alfred_Fischer");
		log9.setPassword("alf01");
		seeds.get("Role").get(1);
		seeds.get("Role").get(4);
		
		//GUB
		Login log10 = new Login();
		log10.setUsername("Sebastian_Müller");
		log10.setPassword("sebi01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(5);
		
		//SUC
		Login log11 = new Login();
		log11.setUsername("Manfred_Schmid");
		log11.setPassword("mani01");
		seeds.get("Role").get(1);
		seeds.get("Role").get(5);
		
		//Subcontractor Meier Maurer
		Login log12 = new Login();
		log12.setUsername("Michael_Meier");
		log12.setPassword("michi02");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		//Subcontractor Müller Schreiner
		Login log13 = new Login();
		log13.setUsername("Fritz_Mueller");
		log13.setPassword("fritz01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(3);
		
		//Subcontractor Fischer Spenglerei
		Login log14 = new Login();
		log14.setUsername("Bernhard_Fischer");
		log14.setPassword("bern01");
		seeds.get("Role").get(1);
		seeds.get("Role").get(4);
		
		//Subcontractor Gubler Gibser
		Login log15 = new Login();
		log15.setUsername("Robin_Gubler");
		log15.setPassword("robi01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(5);
		
		//Subcontractor Santis Sanitär
		Login log16 = new Login();
		log16.setUsername("Dennis_Santis");
		log16.setPassword("dennis01");
		seeds.get("Role").get(1);
		seeds.get("Role").get(5);
		
		
		list.add(log1);
		list.add(log2);
		list.add(log3);
		list.add(log4);
		list.add(log5);
		list.add(log6);
		list.add(log7);
		list.add(log8);
		list.add(log9);
		list.add(log10);
		list.add(log11);
		list.add(log12);
		list.add(log13);
		list.add(log14);
		list.add(log15);
		list.add(log16);
		
		this.seeds.put("Login", list);
	}

}
