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

import ch.claimer.webservice.controller.SubcontractorController;
import ch.claimer.webservice.injector.SubcontractorInjector;


/**
 * Defines RESTful routes for subcontractor specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Momcilo Bekcic, Raoul Ackermann
 */
@Path("/")
public class SubcontractorRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new SubcontractorControllerInjector());
	SubcontractorController controller = injector.getInstance(SubcontractorController.class);
 
	/**
	 * Maps the controller to show all subcontractor 
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/subcontractor")
	public Response getAllSubcontractor() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific subcontractor 
	 * by his primary key
	 * 
	 * @param id primary key of the subcontractor to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/subcontractor/{id}")
	public Response getSubcontractorById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new subcontractor 
	 * 
	 * @param data data of the subcontractor  to store supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/subcontractor")
	public Response storeSubcontractor(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific subcontractor 
	 * 
	 * @param data data of the subcontractor to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/subcontractor")
	public Response updateSubcontractor(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific subcontractor
	 * 
	 * @param data data of the subcontractor to destroy
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/subcontractor")
	public Response destroySubcontractor(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}