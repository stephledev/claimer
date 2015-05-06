import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import ch.claimer.shared.models.Category;
import ch.claimer.webservice.routes.CategoryRoute;
import ch.claimer.webservice.routes.CommentRoute;
import ch.claimer.webservice.routes.ContactRoute;
import ch.claimer.webservice.routes.GCEmployeeRoute;
import ch.claimer.webservice.routes.IssueRoute;
import ch.claimer.webservice.routes.LoginRoute;
import ch.claimer.webservice.routes.PrincipalRoute;
import ch.claimer.webservice.routes.ProjectRoute;
import ch.claimer.webservice.routes.RoleRoute;
import ch.claimer.webservice.routes.SCEmployeeRoute;
import ch.claimer.webservice.routes.StateRoute;
import ch.claimer.webservice.routes.SubcontractorRoute;
import ch.claimer.webservice.routes.SupervisorRoute;
import ch.claimer.webservice.routes.TypeRoute;

/**
 * Testet die RESTRoutes des Webservices
 * 
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 *
 */
public class TestRoutes {
	
	CategoryRoute categoryRoute;
	CommentRoute commentRoute;
	ContactRoute contactRoute;
	GCEmployeeRoute gcEmployeeRoute;
	IssueRoute issueRoute;
	LoginRoute loginRoute;
	PrincipalRoute principalRoute;
	ProjectRoute projectRoute;
	RoleRoute roleRoute;
	SCEmployeeRoute scEmployee;
	StateRoute stateRoute;
	SubcontractorRoute subcontractorRoute;
	SupervisorRoute supervisorRoute;
	TypeRoute typeRoute;
	
	
	/**
	 * Diese Methode initalisiert Objekte der Routes
	 */
	@BeforeClass
	public static void oneTimeSetUP () {
		Category cat1 = new Category();
		cat1.setId(0);
		cat1.setName("Einfamilienhaus");
		
		Category cat2 = new Category();
		cat2.setId(1);
		cat2.setName("Wohnung");
		
	}

	@Test
	public void testCategoryRoute() {
		assertEquals("Einfamilienhaus", categoryRoute.showById(0));
		
	}

}
