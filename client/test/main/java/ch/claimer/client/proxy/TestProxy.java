package ch.claimer.client.proxy;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Contact;
import ch.claimer.shared.models.Principal;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.shared.models.Supervisor;

/**
 * Testet die Proxies
 * 
 * @author Raoul Ackermann
 * @author Stephan Beeler
 * 
 * @version 1.1
 * @since 1.0
 *
 */
public class TestProxy {
	
	
	private static PrincipalProxy principalProxy;
	private static ProjectProxy projectProxy;
	private static SupervisorProxy supervisorProxy;
	private static ContactProxy contactProxy;
	private static SubcontractorProxy subcontractorProxy;
	
	private static ObjectMapper mapper;
	
	
	@BeforeClass
	public static void oneTimeSetUp() {
		
		mapper = new ObjectMapper();
		
		principalProxy = ResteasyClientUtil.getTarget().proxy(PrincipalProxy.class);
		
		Principal pri1 = new Principal();
		pri1.setFirstname("Peter");
		pri1.setLastname("Muster");
		pri1.setPlace("Beispiel");
		
		Principal pri2 = new Principal();
		pri2.setCompany("Vorlage AG");
		pri2.setPlace("Entwurf");
		
		principalProxy.create(pri1);
		principalProxy.create(pri2);	
		
		supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		
		Supervisor s1 = new Supervisor();
		s1.setFirstname("David");
		s1.setLastname("Leitbild");
		
		Supervisor s2 = new Supervisor();
		s2.setFirstname("Elisabeth");
		s2.setLastname("Marti");
		
		supervisorProxy.create(s1);
		supervisorProxy.create(s2);
		
		contactProxy = ResteasyClientUtil.getTarget().proxy(ContactProxy.class);
		
		Contact c1 = new Contact();
		c1.setFirstname("Markus");
		c1.setLastname("Polo");
		
		Contact c2 = new Contact();
		c2.setFirstname("Dwayne");
		c2.setLastname("Josi");
		
		contactProxy.create(c1);
		contactProxy.create(c2);
		
		projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
		
		
		try {
			Project pro1 = new Project();
			pro1.setName("Referenz");
			pro1.setStreet("Vorbildstrasse 1");
			pro1.setSupervisor(mapper.readValue(supervisorProxy.getById(1), new TypeReference<Supervisor>(){}));
			pro1.getContacts().add(mapper.readValue(contactProxy.getById(3), new TypeReference<Contact>(){}));
			
			Project pro2 = new Project();
			pro2.setName("Musterstück");
			pro2.setStreet("Beispielstrasse 4");
			pro2.setSupervisor(mapper.readValue(supervisorProxy.getById(2), new TypeReference<Supervisor>(){}));
			pro2.getContacts().add(mapper.readValue(contactProxy.getById(3), new TypeReference<Contact>(){}));
			pro2.getContacts().add(mapper.readValue(contactProxy.getById(4), new TypeReference<Contact>(){}));
			
			projectProxy.create(pro1);
			projectProxy.create(pro2);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		subcontractorProxy = ResteasyClientUtil.getTarget().proxy(SubcontractorProxy.class);
	}
	
	@Test
    public void testGetById() {	
		try {
			Principal p1 = mapper.readValue(principalProxy.getById(2), new TypeReference<Principal>(){});
			assertEquals("Vorlage AG", p1.getCompany());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Test
    public void testGetAll() {	
		try {
			List<Principal> ps = mapper.readValue(principalProxy.getAll(), new TypeReference<List<Principal>>(){});
			assertEquals(2, ps.size());
			assertEquals("Peter", ps.get(0).getFirstname());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testGetByRelation() {
		try {
			List<Project> ps = mapper.readValue(projectProxy.getBySupervisor(1), new TypeReference<List<Project>>(){});
			assertEquals(1, ps.size());
			assertEquals("Referenz", ps.get(0).getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetByRelations() {
		try {
			List<Project> ps = mapper.readValue(projectProxy.getByContact(3), new TypeReference<List<Project>>(){});
			assertEquals(2, ps.size());
			assertEquals("Markus", ps.get(1).getContacts().get(0).getFirstname());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreate() {
		try {
			Subcontractor s1 = new Subcontractor();
			s1.setName("Testfirma");
			s1.setStreet("Defaultweg 5");
			
			subcontractorProxy.create(s1);
			
			Subcontractor s2 = mapper.readValue(subcontractorProxy.getById(1), new TypeReference<Subcontractor>(){});
			assertEquals("Testfirma", s2.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		try {
			Project p1 = mapper.readValue(projectProxy.getById(2), new TypeReference<Project>(){});
			p1.setName("Big Ben");
			projectProxy.update(p1);
			
			Project p2 = mapper.readValue(projectProxy.getById(2), new TypeReference<Project>(){});
			assertEquals("Big Ben", p2.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
