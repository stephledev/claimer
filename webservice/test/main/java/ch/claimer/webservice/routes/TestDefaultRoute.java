package ch.claimer.webservice.routes;

import org.hibernate.Session;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.*;

import ch.claimer.shared.models.Type;
import ch.claimer.webservice.services.DataProcessorService;
import ch.claimer.webservice.services.HibernateService;
import ch.claimer.webservice.services.JsonDataProcessorService;

import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

 
/**
 * @author Stephan Beeler
 */
public class TestDefaultRoute {
	
	private static Dispatcher dispatcher;
	private static MockHttpResponse response;
	private static MockHttpRequest request;
	private static DataProcessorService<Type> processor;
	private static Type type;
	
	private static void setUpDatabase() {
		HibernateService hibernate = new HibernateService();
        Session session = hibernate.openSessionwithTransaction();
        session.save(type);
        hibernate.closeSessionwithTransaction();
	}
    
 
    @BeforeClass
    public static void oneTimeSetUp() {
    	dispatcher = MockDispatcherFactory.createDispatcher();

        POJOResourceFactory noDefaults = new POJOResourceFactory(DefaultRoute.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
        
        processor = new JsonDataProcessorService<Type>();
        
        type = new Type();
        type.setName("Neubau");
        
        setUpDatabase();
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
    	request = MockHttpRequest.get("/type");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus()); 
    }
    
    @Test
    public void testShowRoute() throws URISyntaxException {
    	request = MockHttpRequest.get("/type/1");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus()); 
    }
    
    @Test
    public void testUpdateRoute() throws URISyntaxException {
    	request = MockHttpRequest.put("/type");
    	type.setId(2);
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus()); 
    }
    
    @Test
    public void testStoreRoute() throws URISyntaxException {
    	request = MockHttpRequest.post("/type");
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus()); 
    }
    
    @Test
    public void testDeleteRoute() throws URISyntaxException {
    	request = MockHttpRequest.delete("/type");
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        setUpDatabase();
    }
    
    
    
    @Test
    public void testIndexRouteNotFound() throws URISyntaxException {
    	request = MockHttpRequest.get("/doesnotexist");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    
    @Test
    public void testShowRouteNotFound() throws URISyntaxException {
    	request = MockHttpRequest.get("/doesnotexist/0");
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    
    @Test
    public void testStoreRouteNotFound() throws URISyntaxException {
    	request = MockHttpRequest.post("/doesnotexist");
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    
    @Test
    public void testUpdateRouteNotFound() throws URISyntaxException {
    	request = MockHttpRequest.put("/doesnotexist");
    	type.setId(1);
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    
    @Test
    public void testDeleteRouteNotFound() throws URISyntaxException {
    	request = MockHttpRequest.delete("/doesnotexist");
    	type.setId(1);
    	request.addFormHeader("data", processor.write(type));
        
        dispatcher.invoke(request, response);
        
        Assert.assertEquals(HttpServletResponse.SC_NOT_FOUND, response.getStatus()); 
    }
    

}