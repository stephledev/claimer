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
import ch.claimer.shared.models.Supervisor;

public class TestRMI {
	
	private static Method<Project> projectMethod;
	private static Method<GCEmployee> gcEmployeeMethod;
	private static Method<Issue> issueMethod;
	private static Method<SCEmployee> scEmployeeMethod;
	private static Method<Supervisor> supervisorMethod;
	private static Method<State> stateMethod;

	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void oneTimeSetUp() {
		Config config = ConfigFactory.load();
		try {
			projectMethod = (Method<Project>) Naming.lookup(config.getString("rmi.url") + "Project");
		} catch (MalformedURLException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (RemoteException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		} catch (NotBoundException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
		try {
			gcEmployeeMethod = (Method<GCEmployee>) Naming.lookup(config.getString("rmi.url") + "GCEmployee");
		} catch (MalformedURLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (RemoteException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (NotBoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		try {
			issueMethod = (Method<Issue>) Naming.lookup(config.getString("rmi.url") + "Issue");
		} catch (MalformedURLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (NotBoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			stateMethod = (Method<State>) Naming.lookup(config.getString("rmi.url") + "Status");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			supervisorMethod = (Method<Supervisor>) Naming.lookup(config.getString("rmi.url") + "Supervisor");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			scEmployeeMethod = (Method<SCEmployee>) Naming.lookup(config.getString("rmi.url") + "SCEmployee");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		State s1 = new State();
		s1.setName("Status1");
		try {
			stateMethod.create(s1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GCEmployee g1 = new GCEmployee();
		g1.setLastname("Nachname1");
		try {
			gcEmployeeMethod.create(g1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GCEmployee g2 = new GCEmployee();
		g2.setLastname("Nachname2");
		g2.setFirstname("Firstname2");
		try {
			gcEmployeeMethod.create(g2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SCEmployee sce1 = new SCEmployee();
		sce1.setLastname("sceNachname1");
		sce1.setFirstname("sceVorname1");
		try {
			scEmployeeMethod.create(sce1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Supervisor sup1 = new Supervisor();
		sup1.setLastname("SupName1");
		try {
			supervisorMethod.create(sup1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Project p1 = new Project();
		p1.setName("Projectname1");
		p1.setPlace("Luzern");
		p1.setStreet("Zentralstrasse 9");
		try {
			projectMethod.create(p1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Project p2 = new Project();
		p2.setName("Projectname2");
		p2.setStreet("Street2");
		p2.setSupervisor(sup1);
		try {
			projectMethod.create(p2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Issue iss1 = new Issue();
		iss1.setDescription("Issue1");
		iss1.setProject(p1);
		try {
			issueMethod.create(iss1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
    public void testGetById() {
    	try {
			assertEquals("Status1", stateMethod.getById(1).getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals("Nachname1", gcEmployeeMethod.getById(1).getLastname());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals("Projectname1", projectMethod.getById(1).getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void testGetAll() {
    	try {
			assertEquals(2, gcEmployeeMethod.getAll().size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals("Projectname2", projectMethod.getAll().get(1).getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
    }
    
    @Test
    public void testGetByProperty() {
    	try {
			assertEquals("Projectname1", projectMethod.getByProperty("place", "Luzern").get(0).getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals("Nachname2", gcEmployeeMethod.getByProperty("firstname", "Firstname2").get(0).getLastname());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
  
    @Test
    public void testGetByRelation(){
    	try {
			assertEquals("Issue1", issueMethod.getByRelation(Project.class, 1).get(0).getDescription());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals("Projectname2", projectMethod.getByRelation(Supervisor.class, 4).get(0).getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    @Test
    public void testCreate(){
     Project p3 = new Project();
     p3.setName("Projectname3");
     try {
		projectMethod.create(p3);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     try {
		assertEquals("Projectname3", projectMethod.getById(3).getName());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    }
    
    @Test
    public void testUpdate(){
     Project p1 = new Project();
	try {
		p1 = projectMethod.getById(1);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     p1.setName("ProjectX");
     try {
		projectMethod.update(p1);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     try {
		assertEquals("ProjectX", projectMethod.getById(1).getName());
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }

    @Test
    public void testDelete(){
    	try {
			stateMethod.delete(1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	try {
			assertEquals(null, stateMethod.getById(1));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	
}
