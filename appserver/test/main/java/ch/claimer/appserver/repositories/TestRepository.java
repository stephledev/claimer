package ch.claimer.appserver.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.State;

public class TestRepository {
	
	private static Repository<Project> projectRepo;
	private static Repository<State> stateRepo;
	private static Repository<GCEmployee> gcEmployeeRepo;
	
	@BeforeClass
	public static void oneTimeSetUp() {
		projectRepo = new EclipseLinkRepository<Project>(Project.class);
		stateRepo = new EclipseLinkRepository<State>(State.class);
		gcEmployeeRepo = new EclipseLinkRepository<GCEmployee>(GCEmployee.class);
		
		State s1 = new State();
		s1.setName("Status1");
		stateRepo.create(s1);
		
		GCEmployee g1 = new GCEmployee();
		g1.setLastname("Nachname1");
		gcEmployeeRepo.create(g1);
	}
	 
    @Test
    public void testGetById() {
    	assertEquals("Status1", stateRepo.getById(1).getName());
    	assertEquals("Nachname1", gcEmployeeRepo.getById(1).getLastname());
    }

}
