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
		this.list = new ArrayList<Role>();
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

		list.add(role1);
		list.add(role2);
		list.add(role3);
		list.add(role4);
		list.add(role5);
		list.add(role6);
		
		this.seeds.put("Role", list);
		
	}

}
