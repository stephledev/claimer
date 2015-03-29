package ch.claimer.webservice.routes;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.*;

import ch.claimer.shared.models.GeneralContractor;
import static org.junit.Assert.*;

import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

 
/**
 * @author Stephan Beeler
 *
 */
public class TestDefaultRoute {
	
	private static Dispatcher dispatcher;
	private static MockHttpResponse response;
	private static MockHttpRequest request;
    
 
    @BeforeClass
    public static void oneTimeSetUp() {
    	dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(DefaultRoute.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        
    	System.out.println(dispatcher);
    }
 
    @Before
    public void setUp() {
    	response = new MockHttpResponse();
    }
 
    @After
    public void tearDown() {
    	request = null;
        response = null;
    }
 
    @Test
    public void testIndexRoute() throws URISyntaxException {
    	request = MockHttpRequest.get("/doesnotexist");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    
    @Test
    public void testShowRoute() throws URISyntaxException {
    	request = MockHttpRequest.get("/scemployee/1");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus()); 
    }
    

 

}