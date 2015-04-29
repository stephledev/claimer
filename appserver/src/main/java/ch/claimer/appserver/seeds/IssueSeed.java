package ch.claimer.appserver.seeds;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Issue;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class IssueSeed extends Seed<Issue> {
	
	public IssueSeed() {
		this.repository = new EclipseLinkRepository<Issue>(Issue.class);
		this.list = new ArrayList<Issue>();
	}

	@Override
	public void setup() {
		
		Issue iss1 = new Issue();
		iss1.setDescription("Fenstereinfassung hat Risse. Standort: 2.Stock, Zimmer rechts(Osten).");
		iss1.setCreated(new GregorianCalendar());
		iss1.setSolved(new GregorianCalendar());
		seeds.get("LogEntry").get(0);
		seeds.get("Project").get(0);
		seeds.get("State").get(2);
		seeds.get("Supcontractor").get(0);
		seeds.get("Contact").get(0);
		
		Issue iss2 = new Issue();
		iss2.setDescription("Dachleisten auf der Ostseite sind versplittert.");
		iss2.setCreated(new GregorianCalendar());
		iss2.setSolved(new GregorianCalendar());
		seeds.get("LogEntry").get(2);
		seeds.get("Project").get(1);
		seeds.get("State").get(4);
		seeds.get("Supcontractor").get(1);
		seeds.get("Contact").get(0);
		
		Issue iss3 = new Issue();
		iss3.setDescription("Fassade auf der Nordseite weisst Schäden auf.");
		iss3.setCreated(new GregorianCalendar());
		iss3.setSolved(new GregorianCalendar());
		seeds.get("LogEntry").get(2);
		seeds.get("Project").get(1);
		seeds.get("State").get(4);
		seeds.get("Supcontractor").get(2);
		seeds.get("Contact").get(0);
		
		Issue iss4 = new Issue();
		iss4.setDescription("Schäden im Badezimmer im 1.Stock.");
		iss4.setCreated(new GregorianCalendar());
		iss4.setSolved(new GregorianCalendar());
		seeds.get("LogEntry").get(2);
		seeds.get("Project").get(0);
		seeds.get("State").get(2);
		seeds.get("Supcontractor").get(3);
		seeds.get("Contact").get(0);
		
		Issue iss5 = new Issue();
		iss5.setDescription("Das Wasser in Wohung B im 3.Stock läuft nicht.");
		iss5.setCreated(new GregorianCalendar());
		iss5.setSolved(new GregorianCalendar());
		seeds.get("LogEntry").get(2);
		seeds.get("Project").get(1);
		seeds.get("State").get(2);
		seeds.get("Supcontractor").get(3);
		seeds.get("Contact").get(0);
		
			
		
		list.add(iss1);
		list.add(iss2);
		list.add(iss3);
		list.add(iss4);
		list.add(iss5);
		this.seeds.put("Issue", list);
	}

}

