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
		log1.setUsername("Stephan_Beeler");
		log1.setPassword("stephan01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log4 = new Login();
		log2.setUsername("Kevin_Stadelmann");
		log2.setPassword("kevin01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log5 = new Login();
		log1.setUsername("Michael_Lötscher");
		log1.setPassword("michael01");
		seeds.get("Role").get(4);
		
		Login log6 = new Login();
		log2.setUsername("Momcilo_Bekcic");
		log2.setPassword("momo01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		Login log7 = new Login();
		log2.setUsername("Alexander_Hauck");
		log2.setPassword("alex01");
		seeds.get("Role").get(0);
		seeds.get("Role").get(2);
		
		
		list.add(log1);
		list.add(log2);
		list.add(log3);
		list.add(log4);
		list.add(log5);
		list.add(log6);
		list.add(log7);
		
		this.seeds.put("Login", list);
	}

}
