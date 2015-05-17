package ch.claimer.client.proxy;

import static org.junit.Assert.*;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.client.util.ResteasyClientUtil;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.Project;
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
	private static CommentProxy commentProxy;
	private static SupervisorProxy supervisorProxy;
	private static IssueProxy issueProxy;
	private static GCEmployeeProxy gceemployeeProxy;
	private static ObjectMapper mapper;
	
	
	@BeforeClass
	public static void oneTimeSetUp() {
		
		projectProxy = ResteasyClientUtil.getTarget().proxy(ProjectProxy.class);
		supervisorProxy = ResteasyClientUtil.getTarget().proxy(SupervisorProxy.class);
		commentProxy = ResteasyClientUtil.getTarget().proxy(CommentProxy.class);
		gceemployeeProxy = ResteasyClientUtil.getTarget().proxy(GCEmployeeProxy.class);
		issueProxy = ResteasyClientUtil.getTarget().proxy(IssueProxy.class);
		mapper = new ObjectMapper();
		
		//List<Category> categoryList = mapper.readValue(categoryProxy.getAll(), new TypeReference<List<Category>>(){});
		
		
		
		Comment comment1 = new Comment();
		comment1.setContent("hallo");
		commentProxy.create(comment1);
		
		Comment comment2 = new Comment();
		comment2.setContent("New Comment");
		commentProxy.create(comment2);
		
		Supervisor sup1 = new Supervisor();
		sup1.setLastname("Pfaffen");
		sup1.setFirstname("Eva");
		supervisorProxy.create(sup1);
		
		GCEmployee gce1 = new GCEmployee();
		gce1.setLastname("Karlsson");
		gce1.setFirstname("Jeannine");
		gceemployeeProxy.create(gce1);
		
		GCEmployee gce2 = new GCEmployee();
		gce2.setLastname("Hunter");
		gce2.setFirstname("Steve");
		gceemployeeProxy.create(gce1);
		
		Project project = new Project();
		project.setName("Projekt_1");
		project.setSupervisor(sup1);
		projectProxy.create(project);
		
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
		Project project = null;
		GCEmployee gce1 = null;
		try {
			project = mapper.readValue(projectProxy.getById(1), new TypeReference<Project>(){});
			gce1 = mapper.readValue(gceemployeeProxy.getById(1), new TypeReference<GCEmployee>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertEquals("Projekt_1", project.getName());
		assertEquals("Karlsson", gce1.getLastname());
    	
    }
	
//	@Test
//	public void testGetBySupervisor(){
//		Project project = null;
//		Supervisor sup1 = null;
//		
//		try {
//			sup1 = mapper.readValue(supervisorProxy.getById(1), new TypeReference<Supervisor>(){});
//			project = mapper.readValue(projectProxy.getBySupervisor(), new TypeReference<Project>(){});
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		assertEquals("Eva", projectProxy.getBySupervisor("Pfaffen"));
//	}
	
//	@Test
//	public void testGetAll(){
//		GCEmployee gce1 = null;
//		GCEmployee gce2 = null;
//		
//		try {
//			gce1 = mapper.readValue(gceemployeeProxy.getAll(), new TypeReference<GCEmployee>(){});
//			gce2 = mapper.readValue(gceemployeeProxy.getAll(), new TypeReference<GCEmployee>(){});
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//		
//		assertEquals()
//	}
//	
	@Test
	public void testCreate(){
		Issue iss2 = new Issue();
		iss2.setDescription("Neuer Mangel2");
		issueProxy.create(iss2);	
		
		assertEquals("Neuer Mangel2", iss2.getDescription());
	}
	
}
