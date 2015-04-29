package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Supervisor;

/** 
 * @author Fabio Baviera
 */

public class SupervisorSeed extends Seed<Supervisor> {
	
	public SupervisorSeed() {
		this.repository = new EclipseLinkRepository<Supervisor>(Supervisor.class);
		
	}

	@Override
	public void setup() {
		Supervisor Supervisor1 = new Supervisor();
		Supervisor1.setLastname("M�ller");
		Supervisor1.setFirstname("Sebastian");
		Supervisor1.setEmail("sebastian.mueller@bluewin.ch.ch");
		Supervisor1.setPhone("041 111 11 11");
		seeds.get("Login").get(9);
		
		
		
		list.add(Supervisor1);
		
		
		this.seeds.put("Supervisor", list);
	}

}
