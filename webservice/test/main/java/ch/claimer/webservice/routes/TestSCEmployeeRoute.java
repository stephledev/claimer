package ch.claimer.webservice.routes;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.*;

import ch.claimer.shared.entities.GeneralContractor;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
 
/**
 * @author Stephan Beeler
 *
 */
public class TestSCEmployeeRoute {
	
	private static Dispatcher dispatcher;
    
 
    @BeforeClass
    public static void oneTimeSetUp() {
    	dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(SCEmployeeRoute.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        
    	System.out.println(dispatcher);
    }
 
    @AfterClass
    public static void oneTimeTearDown() {
        // one-time cleanup code
    	System.out.println("@AfterClass - oneTimeTearDown");
    }
 
    @Before
    public void setUp() {
        System.out.println("@Before - setUp");
    }
 
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
 
    @Test
    public void testEmptyCollection() throws URISyntaxException {
    	MockHttpRequest request = MockHttpRequest.get("/scemployee");
        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);


        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        Assert.assertEquals("basic", response.getContentAsString());    }
 

}