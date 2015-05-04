package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Principal;

/** 
 * @author Fabio Baviera
 */

public class PrincipalSeed extends Seed<Principal> {
	
	public PrincipalSeed() {
		this.repository = new EclipseLinkRepository<Principal>(Principal.class);
		this.seed = new ArrayList<Principal>();
	}

	@Override
	public void setup() {
		Principal Principal1 = new Principal();
		Principal1.setLastname("Markus");
		Principal1.setFirstname("Baumgartner");
		
		Principal Principal2 = new Principal();
		Principal2.setLastname("Tobias");
		Principal2.setFirstname("Spengler");
		
		Principal Principal3 = new Principal();
		Principal3.setCompany("Exon AG");
		
		Principal Principal4 = new Principal();
		Principal4.setCompany("Eberli");
				
		
		seed.add(Principal1);
		seed.add(Principal2);
		seed.add(Principal3);
		seed.add(Principal4);
		
		Seed.seeds.put("Principal", seed);
	}
}