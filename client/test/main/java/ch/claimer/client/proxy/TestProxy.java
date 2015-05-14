package ch.claimer.client.proxy;

import static org.junit.Assert.*;

import java.rmi.Naming;

import org.eclipse.persistence.sessions.Project;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import ch.claimer.shared.methods.Method;
import ch.claimer.shared.models.Category;
import ch.claimer.shared.models.Comment;
import ch.claimer.shared.models.GCEmployee;
import ch.claimer.shared.models.Issue;
import ch.claimer.shared.models.SCEmployee;
import ch.claimer.shared.models.State;
import ch.claimer.shared.models.Subcontractor;
import ch.claimer.shared.models.Supervisor;

/**
 * Testet die Proxies
 * 
 * @author Momcilo Bekcic
 * 
 * @version 1.0
 * @since 1.0
 *
 */
public class TestProxy {
	
	
	//Proxy-Objekte laden
	private static CategoryProxy categoryProxy;
	private static ProjectProxy projectProxy;
	private static CommentProxy commentProxy;
	
	@BeforeClass
	public static void oneTimeSetUp() {
		
		Config config = ConfigFactory.load();
		try {
			categoryProxy = (Method<Project>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "project");
			gcEmployeeMethod = (Method<GCEmployee>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "gcemployee");
			issueMethod = (Method<Issue>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "issue");
			stateMethod = (Method<State>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "state");
			supervisorMethod = (Method<Supervisor>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "supervisor");
			scEmployeeMethod = (Method<SCEmployee>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "scemployee");
			subcontractorMethod = (Method<Subcontractor>) Naming.lookup("rmi://"
					+ config.getString("rmi.host") + ":"
					+ config.getString("rmi.port") + "/"
					+ "subcontractor");
		
		categoryProxy = new CategoryProxy();
		projectProxy = new ProjectProxy();
		commentProxy = new CommentProxy();
		
		Category category = new Category();
		category.setName("Einfamilienhaus");
		categoryProxy.create(category);
		
		Project project = new Project();
		project.setName("B1-21, Rischstrasse");
		projectProxy.create(project);
		
		Comment comment = new Comment();
		comment.setContent("hallo");
		commentProxy.create("hallo");

	}
	

	@Before
	public void setUp() throws Exception {
	}

	//die Objekte Testen
	@Test
    public void testGetById() {
    	assertEquals("Einfamilienhaus", category.getById(1).getName());
    	assertEquals("B1-21, Rischstrasse", project.getById(1).getName());
    	assertEquals("hallo", comment.getById(1).getName());
    }

}
