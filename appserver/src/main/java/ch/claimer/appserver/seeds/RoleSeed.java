package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Role;

/**
 * 
 * 
 * @author Fabio Baviera
 */

public class RoleSeed extends Seed<Role> {
	
	public RoleSeed() {
		this.repository = new EclipseLinkRepository<Role>(Role.class);
		this.seed = new ArrayList<Role>();
	}

	@Override
	public void setup() {
		
		Role role1 = new Role();
		role1.setName("superadmin");
		role1.setValue(25);
		
		Role role2 = new Role();
		role2.setName("admin");
		role2.setValue(20);
		
		Role role3 = new Role();
		role3.setName("power");
		role3.setValue(15);
		
		Role role4 = new Role();
		role4.setName("editor-intern");
		role4.setValue(10);
		
		Role role5 = new Role();
		role5.setName("editor-extern");
		role5.setValue(5);

		seed.add(role1);
		seed.add(role2);
		seed.add(role3);
		seed.add(role4);
		seed.add(role5);
		
		Seed.seeds.put("Role", seed);
		
	}

}
