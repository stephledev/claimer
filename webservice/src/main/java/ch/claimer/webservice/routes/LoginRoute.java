package ch.claimer.webservice.routes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.LoginController;
import ch.claimer.webservice.injector.LoginControllerInjector;


/**
 * Defines RESTful routes for login specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class LoginRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new LoginControllerInjector());
	LoginController controller = injector.getInstance(LoginController.class);
 
	/**
	 * Maps the controller to show all login
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/login")
	public Response getAllLogin() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific login by his primary key
	 * 
	 * @param id primary key of the login to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/login/{id}")
	public Response getLoginById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new login
	 * 
	 * @param data data of the login to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/login")
	public Response storeLogin(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific login
	 * 
	 * @param data data of the login to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/login")
	public Response updateLogin(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific login
	 * 
	 * @param data data of the login to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/login")
	public Response destroyLogin(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}