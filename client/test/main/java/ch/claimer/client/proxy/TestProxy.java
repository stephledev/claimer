package claimer.client.proxy;

import static org.junit.Assert.*;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.client.proxy.GCEmployeeProxy;
import ch.claimer.client.proxy.IssueProxy;
import ch.claimer.client.proxy.ProjectProxy;
import ch.claimer.client.proxy.SCEmployeeProxy;
import ch.claimer.client.proxy.SupervisorProxy;
import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Project;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.Supervisor;

/**
 * Testet die Proxies
 * 
 * @author Raoul Ackermann
 * @author Stephan Beeler
 * @author Momcilo Bekcic
 * 
 * @version 1.1
 * @since 1.0
 *
 */
public class TestProxy {
	
	
	//Proxy-Objekte laden
	private static ProjectProxy projectProxy;
	private static SupervisorProxy supervisorProxy;
	private static IssueProxy issueProxy;
	private static GCEmployeeProxy gceemployeeProxy;
	private static SCEmployeeProxy sceemployeeProxy;
	private static ObjectMapper mapper;
	
	
	@BeforeClass
	public static void oneTimeSetUp() {
		
		projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
		supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		gceemployeeProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		sceemployeeProxy = ResteasyClientUtil.getTarget().proxy(SCEmployeeProxy.class);
		issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		mapper = new ObjectMapper();
		
		//List<Category> categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
		
		Issue iss1 = new Issue();
		iss1.setDescription("Neuer Mangel1");
		issueProxy.create(iss1);
		

	}
	

	@Before
	public void setUp() throws Exception {
	}

	//Methode getById in den Proxys testen.
	@Test
    public void testGetById() {
		
		Supervisor sup1 = new Supervisor();
		sup1.setLastname("Pfaffen");
		sup1.setFirstname("Eva");
		supervisorProxy.create(sup1);
		
		Project project = new Project();
		project.setName("Projekt_1");
		project.setSupervisor(sup1);
		projectProxy.create(project);
		
		GCEmployee gce1 = new GCEmployee();
		gce1.setLastname("Karlsson");
		gce1.setFirstname("Jeannine");
		gceemployeeProxy.create(gce1);
		
		try {
			project = mapper.readValue(projectProxy.getById(1), new TypeReference<Project>(){});
			gce1 = mapper.readValue(gceemployeeProxy.getById(2), new TypeReference<GCEmployee>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Projekt_1", project.getName());
		assertEquals("Karlsson", gce1.getLastname());
    	
    }
	
	@Test
	public void testGetBySupervisor(){
		
		
		Supervisor sup2 = new Supervisor();
		sup2.setLastname("Pfaffen");
		sup2.setFirstname("Eva");
		supervisorProxy.create(sup2);
		
		Project project = new Project();
		project.setName("Projekt_1");
		project.setSupervisor(sup2);
		projectProxy.create(project);
		
		try {
			sup2 = mapper.readValue(supervisorProxy.getById(1), new TypeReference<Supervisor>(){});
			project = mapper.readValue(projectProxy.getById(1), new TypeReference<Project>(){});
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		assertEquals("[]", projectProxy.getBySupervisor(1));
	}
	
	@Test
	public void testGetAll(){
		SCEmployee sce1 = new SCEmployee();
		sce1.setLastname("Lawrence");
		sce1.setFirstname("Jennifer");
		sceemployeeProxy.create(sce1);
		
		SCEmployee sce2 = new SCEmployee();
		sce2.setLastname("Hunter");
		sce2.setFirstname("Steve");
		sceemployeeProxy.create(sce2);
		
		try {
			sce1 = mapper.readValue(gceemployeeProxy.getAll(), new TypeReference<GCEmployee>(){});
			sce2 = mapper.readValue(gceemployeeProxy.getAll(), new TypeReference<GCEmployee>(){});
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		assertEquals(2, gceemployeeProxy.getAll());
	}

	@Test
	public void testCreate(){
		Issue iss2 = new Issue();
		iss2.setDescription("Neuer Mangel2");
		issueProxy.create(iss2);	
		
		assertEquals("Neuer Mangel2", iss2.getDescription());
	}
	
}
