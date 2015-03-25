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

import ch.claimer.webservice.controller.ManagerController;
import ch.claimer.webservice.injector.ManagerInjector;


/**
 * Defines RESTful routes for manager specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic, Raoul Ackermann
 */
@Path("/")
public class ManagerRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new ManagerControllerInjector());
	ManagerController controller = injector.getInstance(ManagerController.class);
 
	/**
	 * Maps the controller to show all manager
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/manager")
	public Response getAllManager() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific manager 
	 * by his primary key
	 * 
	 * @param id primary key of the manager to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/manager/{id}")
	public Response getManagerById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new manager 
	 * 
	 * @param data data of the manager to store supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/manager")
	public Response storeManager(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific manager 
	 * 
	 * @param data data of the manager to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/manager")
	public Response updateManager(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific manager
	 * 
	 * @param data data of the manager to destroy
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/manager")
	public Response destroyManager(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}