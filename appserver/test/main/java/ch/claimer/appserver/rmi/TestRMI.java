package ch.claimer.appserver.rmi;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.shared.models.Supervisor;

/**
 * 
 * @author Raoul Ackermann
 * @author Stephan Beeler
 * 
 * @version 1.0
 * @since 1.0
 *
 */

public class TestRMI {
	
	private static Method<Project> projectMethod;
	private static Method<GCEmployee> gcEmployeeMethod;
	private static Method<Issue> issueMethod;
	private static Method<SCEmployee> scEmployeeMethod;
	private static Method<Supervisor> supervisorMethod;
	private static Method<State> stateMethod;
	private static Method<Subcontractor> subcontractorMethod;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void oneTimeSetUp() {
		Config config = ConfigFactory.load();
		try {
			projectMethod = (Method<Project>) Naming.lookup(config.getString("rmi.url") + "Project");
			gcEmployeeMethod = (Method<GCEmployee>) Naming.lookup(config.getString("rmi.url") + "GCEmployee");
			issueMethod = (Method<Issue>) Naming.lookup(config.getString("rmi.url") + "Issue");
			stateMethod = (Method<State>) Naming.lookup(config.getString("rmi.url") + "State");
			supervisorMethod = (Method<Supervisor>) Naming.lookup(config.getString("rmi.url") + "Supervisor");
			scEmployeeMethod = (Method<SCEmployee>) Naming.lookup(config.getString("rmi.url") + "SCEmployee");
			subcontractorMethod = (Method<Subcontractor>) Naming.lookup(config.getString("rmi.url") + "Subcontractor");
			
			State s1 = new State();
			s1.setName("Status1");
			stateMethod.create(s1);
			
			Subcontractor subc1 = new Subcontractor();
			subc1.setName("Subunternehmen1");
			subcontractorMethod.create(subc1);
			
			GCEmployee g1 = new GCEmployee();
			g1.setLastname("Nachname1");
			gcEmployeeMethod.create(g1);
			
			GCEmployee g2 = new GCEmployee();
			g2.setLastname("Nachname2");
			g2.setFirstname("Firstname2");
			gcEmployeeMethod.create(g2);
			
			SCEmployee sce1 = new SCEmployee();
			sce1.setLastname("sceNachname1");
			sce1.setFirstname("sceVorname1");
			sce1.setSubcontractor(subcontractorMethod.getById(1));
			scEmployeeMethod.create(sce1);
			
			Supervisor sup1 = new Supervisor();
			sup1.setLastname("SupName1");
			supervisorMethod.create(sup1);
			
			Project p1 = new Project();
			p1.setName("Projectname1");
			p1.setPlace("Luzern");
			p1.setStreet("Zentralstrasse 9");
			projectMethod.create(p1);
			
			Project p2 = new Project();
			p2.setName("Projectname2");
			p2.setStreet("Street2");
			p2.setSupervisor(supervisorMethod.getById(1));
			projectMethod.create(p2);
			
			Project p3 = new Project();
			p3.setName("Projectname3");
			p3.setPlace("Zürich");
			p3.setSupervisor(supervisorMethod.getById(1));
			projectMethod.create(p3);
			
			Issue iss1 = new Issue();
			iss1.setDescription("Issue1");
			iss1.setProject(projectMethod.getById(1));
			issueMethod.create(iss1);
			
			Issue iss2 = new Issue();
			iss2.setDescription("Issue2");
			iss2.setProject(projectMethod.getById(2));
			issueMethod.create(iss2);
			
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
    public void testGetById() {
    	try {
			assertEquals("Status1", stateMethod.getById(1).getName());
			assertEquals("Nachname1", gcEmployeeMethod.getById(1).getLastname());
			assertEquals("Projectname2", projectMethod.getById(2).getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testGetAll() {
    	try {
			assertEquals(2, gcEmployeeMethod.getAll().size());
			assertEquals("Projectname2", projectMethod.getAll().get(1).getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	      	
    }
    
    @Test
    public void testGetByProperty() {
    	try {
			assertEquals("Projectname2", projectMethod.getByProperty("street", "Street2").get(0).getName());
			assertEquals("Nachname2", gcEmployeeMethod.getByProperty("firstname", "Firstname2").get(0).getLastname());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
  
    @Test
    public void testGetByRelation(){
    	try {
			assertEquals("Issue2", issueMethod.getByRelation(Project.class, 2).get(0).getDescription());
			assertEquals("sceNachname1", scEmployeeMethod.getByRelation(Subcontractor.class, 1).get(0).getLastname());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @Test
    public void testCreate(){
     Project p4 = new Project();
     p4.setName("Projectname4");
     try {
		projectMethod.create(p4);
		assertEquals("Projectname4", projectMethod.getById(4).getName());
	} catch (RemoteException e) {
		e.printStackTrace();
		}     
    }
    
    @Test
    public void testUpdate(){
		try {
			Project p1 = new Project();
			p1 = projectMethod.getById(2);
			p1.setName("Projectname1");
			projectMethod.update(p1);
			assertEquals("Projectname1", projectMethod.getById(1).getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testDelete(){
    	try {
			stateMethod.delete(1);
			assertEquals(null, stateMethod.getById(1));
		} catch (RemoteException e) {
			e.printStackTrace();
		}	
    }
}
