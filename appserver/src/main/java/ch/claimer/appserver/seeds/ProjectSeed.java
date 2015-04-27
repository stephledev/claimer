package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Project;

/**
 * 
 * 
 * @author Raoul Ackermann
 */

public class ProjectSeed extends Seed<Project> {
	
	public ProjectSeed() {
		this.repository = new EclipseLinkRepository<Project>(Project.class);
		this.list = new ArrayList<Project>();
	}

	@Override
	public void setup() {
		Project p1 = new Project();
		p1.setName("Grossbaustelle Nordring");
		p1.setStreet("Schlossgasse 2");
		p1.setZip("5600");
		p1.setPlace("Lenzburg");
		p1.setStart(new);
		p1.setEnd(null);
		p1.setCategory(null);
		
		
		EclipseLinkRepository<State> stateRepo = new EclipseLinkRepository<T>(State.class);
		List<State> stateList = stateRepo.getAll();
		stateList.get(0);
		
		p1.setState(stateList.get(0));
		
		Project p2 = new Project();
		p2.setName("Umbau Bleicherain");
		p2.setStreet("Bleicherain 14");
		p2.setZip("5000");
		p2.setPlace("Aarau");
		p2.setStart(null);
		p2.setEnd(null);
		p2.setCategory(null);
		
		
		
		list.add(p1);
		list.add(p2);
		
	}

}
