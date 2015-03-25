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

import ch.claimer.webservice.controller.GCEmployeeController;
import ch.claimer.webservice.injector.GCEmployeeControllerInjector;


/**
 * Defines RESTful routes for general contractor employee specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class GCEmployeeRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new GCEmployeeControllerInjector());
	GCEmployeeController controller = injector.getInstance(GCEmployeeController.class);
 
	/**
	 * Maps the controller to show all general contractor employees
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/gcemployee")
	public Response getAllSCEmployees() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific general contractor 
	 * employee by his primary key
	 * 
	 * @param id primary key of the general contractor employee to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/gcemployee/{id}")
	public Response getGCEmployeeById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new general contractor 
	 * employee
	 * 
	 * @param data data of the general contractor employee to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/gcemployee")
	public Response storeGCEmployee(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific general contractor 
	 * employee
	 * 
	 * @param data data of the general contractor employee to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/gcemployee")
	public Response updateGCEmployee(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific general contractor 
	 * employee
	 * 
	 * @param data data of the general contractor employee to destroy
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/gcemployee")
	public Response destroyGCEmployee(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}