package ch.claimer.appserver.seeds;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.LogEntry;
import ch.claimer.shared.models.Principal;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Supervisor;
import ch.claimer.shared.models.Type;

/**
 * In der ProjectSeed-Klasse werden die Projekte angelegt. Den Projekten werden
 * Bauherr, LogEntries, Supervisor, Kontakt, Kategorie, Typ und Status
 * zugewiesen und die Adresse der Baustelle in die Datenbank geschrieben. Die
 * Klasse erbt von der Klasse Seed und setzt Project als Typ-Variable.
 * 
 * @author Raoul Ackermann
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */

public class ProjectSeed extends Seed<Project> {

	public ProjectSeed() {
		this.repository = new EclipseLinkRepository<Project>(Project.class);
		this.seed = new ArrayList<Project>();
	}

	@Override
	public void setup() {
		Project p1 = new Project();
		p1.setName("Grossbaustelle Nordring");
		p1.setStreet("Schlossgasse 2");
		p1.setZip("5600");
		p1.setPlace("Lenzburg");
		p1.setStart(new GregorianCalendar());
		p1.setEnd(new GregorianCalendar());
		p1.getPrincipals().add((Principal) seeds.get("Principal").get(0));
		p1.getLogEntries().add((LogEntry) seeds.get("LogEntry").get(1));
		p1.setSupervisor((Supervisor) seeds.get("Supervisor").get(0));
		p1.getContacts().add((Contact) seeds.get("Contact").get(0));
		p1.setCategory((Category) seeds.get("Category").get(3));
		p1.setType((Type) seeds.get("Type").get(0));
		p1.setState((State) seeds.get("State").get(0));

		Project p2 = new Project();
		p2.setName("Umbau Bleicherain");
		p2.setStreet("Bleicherain 14");
		p2.setZip("5000");
		p2.setPlace("Aarau");
		p2.setStart(new GregorianCalendar());
		p2.setEnd(new GregorianCalendar());
		p2.getPrincipals().add((Principal) seeds.get("Principal").get(1));
		p2.setSupervisor((Supervisor) seeds.get("Supervisor").get(1));
		p2.getContacts().add((Contact) seeds.get("Contact").get(3));
		p2.setCategory((Category) seeds.get("Category").get(1));
		p2.setType((Type) seeds.get("Type").get(1));
		p2.setState((State) seeds.get("State").get(0));

		seed.add(p1);
		seed.add(p2);

		Seed.seeds.put("Project", seed);
	}

}
