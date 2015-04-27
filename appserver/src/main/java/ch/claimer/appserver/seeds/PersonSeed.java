package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Person;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class PersonSeed extends Seed<Person> {
	
	public PersonSeed() {
		this.repository = new EclipseLinkRepository<Person>(Person.class);
		this.list = new ArrayList<Person>();
	}

	@Override
	public void setup() {
		Person pers1 = new GCEmployee();
		pers1.setLastname("Beeler");
		pers1.setFirstname("Stephan");
		pers1.setEmail("stephan.beeler@stud.hslu.ch");
		pers1.setPhone("0041 79 234 56 78");
		
		list.add(pers1);
		
	}

}
