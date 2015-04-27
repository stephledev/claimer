package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.State;

/**
 * 
 * 
 * @author Fabio Baviera
 * @author Raoul Ackermann
 */

public class StateSeed extends Seed<State> {
	
	public StateSeed() {
		this.repository = new EclipseLinkRepository<State>(State.class);
		this.list = new ArrayList<State>();
	}

	@Override
	public void setup() {
		State s1 = new State();
		s1.setName("In Bearbeitung");
		State s2 = new State();
		s2.setName("Fertig");
		State s3 = new State();
		s3.setName("Zu Bereinigen");
		State s4 = new State();
		s4.setName("Bereit für Kontrolle");
		State s5 = new State();
		s5.setName("Weitere Arbeiten vornehmen");
		
<<<<<<< HEAD
		seeds.get("Category").get(0);
=======
		
>>>>>>> origin/master
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
	}

}
