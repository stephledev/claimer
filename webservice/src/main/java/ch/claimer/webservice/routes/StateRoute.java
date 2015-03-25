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

import ch.claimer.webservice.controller.StateController;
import ch.claimer.webservice.injector.StateControllerInjector;


/**
 * Defines RESTful routes for state specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class StateRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new StateControllerInjector());
	StateController controller = injector.getInstance(StateController.class);
 
	/**
	 * Maps the controller to show all state
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/state")
	public Response getAllstate() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific state by his primary key
	 * 
	 * @param id primary key of the state to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/state/{id}")
	public Response getStateById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new state
	 * 
	 * @param data data of the state to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/state")
	public Response storeState(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific state
	 * 
	 * @param data data of the state to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/state")
	public Response updateState(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific state
	 * 
	 * @param data data of the state to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/state")
	public Response destroyState(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}