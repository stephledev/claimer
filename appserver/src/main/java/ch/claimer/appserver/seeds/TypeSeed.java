package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Type;

/**
 * 
 * @author Fabio Baviera
 * @author Raoul Ackermann
 */

public class TypeSeed extends Seed<Type> {
	
	public TypeSeed() {
		this.repository = new EclipseLinkRepository<Type>(Type.class);
		this.seed = new ArrayList<Type>();
	}

	@Override
	public void setup() {
		Type t1 = new Type();
		t1.setName("Neubau");
		Type t2 = new Type();
		t2.setName("Rennovation");
		Type t3 = new Type();
		t3.setName("Umbau");
	
		seed.add(t1);
		seed.add(t2);
		seed.add(t3);
		
		Seed.seeds.put("Type", seed);
	}

}
