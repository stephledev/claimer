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

import ch.claimer.webservice.controller.SupervisorController;
import ch.claimer.webservice.injector.SupervisorControllerInjector;


/**
 * Defines RESTful routes for supervisor specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class SupervisorRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new SupervisorControllerInjector());
	SupervisorController controller = injector.getInstance(SupervisorController.class);
 
	/**
	 * Maps the controller to show all supervisors
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/supervisor")
	public Response getAllSupervisor() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific supervisor by his primary key
	 * 
	 * @param id primary key of the supervisor to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/supervisor/{id}")
	public Response getSupervisorById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new supervisor
	 * 
	 * @param data data of the supervisor to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/supervisor")
	public Response storeSupervisor(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific supevisor
	 * 
	 * @param data data of the supervisor to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/supervisor")
	public Response updateSupervisor(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific supervisor
	 * 
	 * @param data data of the supervisor to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/supervisor")
	public Response destroySupervisor(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}