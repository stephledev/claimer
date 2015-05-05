package ch.claimer.appserver.repositories;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.State;

public class TestRepository {
	
	private static Repository<Project> projectRepo;
	private static Repository<State> stateRepo;
	private static Repository<GCEmployee> gcEmployeeRepo;
	private static Repository<SCEmployee> scEmployeeRepo;
	private static Repository<Issue> issueRepo;
	
	
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
		
		GCEmployee g2 = new GCEmployee();
		g2.setLastname("Nachname2");
		g2.setFirstname("Firstname2");
		gcEmployeeRepo.create(g2);
		
		Project p1 = new Project();
		p1.setName("Projectname1");
		p1.setPlace("Luzern");
		p1.setStreet("Zentralstrasse 9");
		projectRepo.create(p1);
		
		Project p2 = new Project();
		p2.setName("Projectname2");
		projectRepo.create(p2);
		
		Issue i1 = new Issue();
		i1.setDescription("Issue1");
		
	}
	
    @Test
    public void testGetById() {
    	assertEquals("Status1", stateRepo.getById(1).getName());
    	assertEquals("Nachname1", gcEmployeeRepo.getById(1).getLastname());
    	assertEquals("Projectname1", projectRepo.getById(1).getName());
    }
    
    @Test
    public void testGetAll() {
    	assertEquals(2, gcEmployeeRepo.getAll().size());
    	assertEquals("Projectname2", projectRepo.getAll().get(1).getName());
    	
    	
    }
    
    @Test
    public void testGetByProperty() {
    	assertEquals("Projectname1", projectRepo.getByProperty("place", "Luzern").get(0).getName());
    	//assertEquals("Nachname2", gcEmployeeRepo.getByProperty("firstname", "Firstname2").get(2).get);
    	
    	

    }
  
    
    
}
