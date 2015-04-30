package ch.claimer.appserver.seeds;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.LogEntry;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class IssueSeed extends Seed<Issue> {
	
	public IssueSeed() {
		this.repository = new EclipseLinkRepository<Issue>(Issue.class);
		this.seed = new ArrayList<Issue>();
	}

	@Override
	public void setup() {
		
		Issue iss1 = new Issue();
		iss1.setDescription("Fenstereinfassung hat Risse. Standort: 2.Stock, Zimmer rechts(Osten).");
		iss1.setCreated(new GregorianCalendar());
		iss1.setSolved(new GregorianCalendar());
		iss1.getLogEntries().add((LogEntry)seeds.get("LogEntry").get(0));
		iss1.getComments().add((Comment)seeds.get("Comment").get(0));
		iss1.setProject((Project) seeds.get("Project").get(0));
		iss1.setState((State) seeds.get("State").get(2));
		iss1.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(0));
		iss1.setContact((Contact) seeds.get("Contact").get(0));
		
		
		Issue iss2 = new Issue();
		iss2.setDescription("Dachleisten auf der Ostseite sind versplittert.");
		iss2.setCreated(new GregorianCalendar());
		iss2.setSolved(new GregorianCalendar());
		iss2.getLogEntries().add((LogEntry)seeds.get("LogEntry").get(2));
		iss1.setProject((Project)seeds.get("Project").get(1));
		iss1.setState((State)seeds.get("State").get(4));
		iss1.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(1));
		iss1.setContact((Contact)seeds.get("Contact").get(1));
		
		Issue iss3 = new Issue();
		iss3.setDescription("Fassade auf der Nordseite weisst Schäden auf.");
		iss3.setCreated(new GregorianCalendar());
		iss3.setSolved(new GregorianCalendar());
		iss3.getLogEntries().add((LogEntry)seeds.get("LogEntry").get(2));
		iss1.setProject((Project)seeds.get("Project").get(1));
		iss1.setState((State)seeds.get("State").get(4));
		iss1.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(2));
		iss1.setContact((Contact)seeds.get("Contact").get(2));
		
		Issue iss4 = new Issue();
		iss4.setDescription("Schäden im Badezimmer im 1.Stock.");
		iss4.setCreated(new GregorianCalendar());
		iss4.setSolved(new GregorianCalendar());
		iss1.getLogEntries().add((LogEntry)seeds.get("LogEntry").get(2));
		iss1.setProject((Project)seeds.get("Project").get(0));
		iss1.setState((State)seeds.get("State").get(2));
		iss1.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(3));
		iss1.setContact((Contact)seeds.get("Contact").get(3));
		
		Issue iss5 = new Issue();
		iss5.setDescription("Das Wasser in Wohung B im 3.Stock läuft nicht.");
		iss5.setCreated(new GregorianCalendar());
		iss5.setSolved(new GregorianCalendar());
		iss5.getLogEntries().add((LogEntry)seeds.get("LogEntry").get(2));
		iss5.setProject((Project)seeds.get("Project").get(1));
		iss5.setState((State)seeds.get("State").get(2));
		iss5.setSubcontractor((Subcontractor) seeds.get("Subcontractor").get(3));
		iss5.setContact((Contact)seeds.get("Contact").get(4));
		
			
		
		seed.add(iss1);
		seed.add(iss2);
		seed.add(iss3);
		seed.add(iss4);
		seed.add(iss5);
		Seed.seeds.put("Issue", seed);
	}

}

