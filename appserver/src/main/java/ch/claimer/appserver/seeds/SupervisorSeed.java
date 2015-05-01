package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Supervisor;

/** 
 * @author Fabio Baviera
 */

public class SupervisorSeed extends Seed<Supervisor> {
	
	public SupervisorSeed() {
		this.repository = new EclipseLinkRepository<Supervisor>(Supervisor.class);
		this.seed = new ArrayList<Supervisor>();
	}

	@Override
	public void setup() {
		Supervisor Supervisor1 = new Supervisor();
		Supervisor1.setLastname("Müller");
		Supervisor1.setFirstname("Sebastian");
		Supervisor1.setEmail("sebastian.mueller@bluewin.ch.ch");
		Supervisor1.setPhone("041 111 11 11");
		Supervisor1.setLogin((Login) seeds.get("Login").get(9));
		
		Supervisor Supervisor2 = new Supervisor();
		Supervisor2.setLastname("Ferrari");
		Supervisor2.setFirstname("Enzo");
		Supervisor2.setEmail("enzo.ferrari@bluewin.ch.ch");
		Supervisor2.setPhone("041 111 11 12");
		Supervisor2.setLogin((Login) seeds.get("Login").get(20));

		seed.add(Supervisor1);
		seed.add(Supervisor2);
			
		Seed.seeds.put("Supervisor", seed);
	}

}
