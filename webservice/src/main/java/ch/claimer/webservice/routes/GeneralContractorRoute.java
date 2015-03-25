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

import ch.claimer.webservice.controller.GeneralContractor;
import ch.claimer.webservice.injector.GeneralContractor;


/**
 * Defines RESTful routes for general contractor specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class GeneralContractorRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new GeneralContractorControllerInjector());
	GeneralContractorController controller = injector.getInstance(GeneralContractorController.class);
 
	/**
	 * Maps the controller to show all general contractors
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/generalcontractor")
	public Response getAllGeneralContractor() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific general contractor by his primary key
	 * 
	 * @param id primary key of the general contractor to show supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/generalcontractor/{id}")
	public Response getGeneralContractorById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new general contractor
	 * 
	 * @param data data of the general contractor to store supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/generalcontractor")
	public Response generalContractor(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific general contractor employee
	 * 
	 * @param data data of the general contractor to update supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/generalcontractor")
	public Response updateGeneralContractor(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific general contractor
	 * 
	 * @param data data of the general contractor to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/generalcontractor")
	public Response destroyGeneralContractor(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}