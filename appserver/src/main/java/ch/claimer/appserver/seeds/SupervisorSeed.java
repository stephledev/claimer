package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
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
		seeds.get("Login").get(9);

		seed.add(Supervisor1);
			
		Seed.seeds.put("Supervisor", seed);
	}

}
