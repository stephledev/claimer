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
		role1.setName("intern");
		
		Role role2 = new Role();
		role2.setName("extern");
		
		Role role3 = new Role();
		role3.setName("superadmin");
		
		Role role4 = new Role();
		role4.setName("admin");
		
		Role role5 = new Role();
		role5.setName("power");
		
		Role role6 = new Role();
		role6.setName("editor");

		seed.add(role1);
		seed.add(role2);
		seed.add(role3);
		seed.add(role4);
		seed.add(role5);
		seed.add(role6);
		
		Seed.seeds.put("Role", seed);
		
	}

}
