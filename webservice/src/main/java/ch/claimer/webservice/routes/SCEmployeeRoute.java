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

import ch.claimer.webservice.controller.SCEmployeeController;
import ch.claimer.webservice.injector.SCEmployeeControllerInjector;


/**
 * Defines RESTful routes for subcontractor employee specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Stephan Beeler
 */
@Path("/")
public class SCEmployeeRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new SCEmployeeControllerInjector());
	SCEmployeeController sec = injector.getInstance(SCEmployeeController.class);
 
	/**
	 * Maps the controller to show all subcontractor employees
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/scemployee")
	public Response getAllSCEmployees() {
		return sec.index();
	} 
    
    /**
	 * Maps the controller to get a specific subcontractor 
	 * employee by his primary key
	 * 
	 * @param id primary key of the subcontractor employee to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/scemployee/{id}")
	public Response getSCEmployeeById(@PathParam("id") int id) {
		return sec.show(id);
	}
	
	/**
	 * Maps the controller to save a new subcontractor 
	 * employee
	 * 
	 * @param data data of the subcontractor employee to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/scemployee")
	public Response storeSCEmployee(@FormParam("data") String data) {
		return sec.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific subcontractor 
	 * employee
	 * 
	 * @param data data of the subcontractor employee to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/scemployee")
	public Response updateSCEmployee(@FormParam("data") String data) {
		return sec.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific subcontractor 
	 * employee
	 * 
	 * @param data data of the subcontractor employee to destroy
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/scemployee")
	public Response destroySCEmployee(@FormParam("data") String data) {
		return sec.destroy(data);
	}  
}