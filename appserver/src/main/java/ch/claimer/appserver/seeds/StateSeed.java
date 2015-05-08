package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.State;

/**
 * In der StateSeed-Klasse werden die möglichen Status der Projekte
 * in die Datenbank geschrieben.
 * Die Klasse erbt von der Klasse Seed und setzt State als Generic.
 * 
 * @author Fabio Baviera
 * @author Raoul Ackermann
 * @version 1.0
 * @since 1.0
 */

public class StateSeed extends Seed<State> {
	
	public StateSeed() {
		this.repository = new EclipseLinkRepository<State>(State.class);
		this.seed = new ArrayList<State>();
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

		seeds.get("Category").get(0);

		seed.add(s1);
		seed.add(s2);
		seed.add(s3);
		seed.add(s4);
		seed.add(s5);
		
		Seed.seeds.put("State", seed);
	}

}
