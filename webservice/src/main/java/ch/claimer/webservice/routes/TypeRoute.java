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

import ch.claimer.webservice.controller.TypeController;
import ch.claimer.webservice.injector.TypeControllerInjector;


/**
 * Defines RESTful routes for type specific 
 * interactions. Maps the controller according to the URL pattern
 * 
 * @author Raoul Ackermann, Momcilo Bekcic 
 */
@Path("/")
public class TypeRoute {
	
	//Get an injected instance of the controller
	Injector injector = Guice.createInjector(new TypeControllerInjector());
	TypeController controller = injector.getInstance(TypeController.class);
 
	/**
	 * Maps the controller to show all logins
	 * 
	 * @return Response from the controller
	 */
    @GET
	@Path("/type")
	public Response getAllType() {
		return controller.index();
	} 
    
    /**
	 * Maps the controller to get a specific type by his primary key
	 * 
	 * @param id primary key of the type to show
	 * supplied by the URL
	 * 		  
	 * @return Response from the controller
	 */
	@GET
	@Path("/type/{id}")
	public Response getTypeById(@PathParam("id") int id) {
		return controller.show(id);
	}
	
	/**
	 * Maps the controller to save a new type
	 * 
	 * @param data data of the type to store
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@POST
	@Path("/type")
	public Response storeType(@FormParam("data") String data) {
		return controller.store(data);
	} 
	
	/**
	 * Maps the controller to update a specific type
	 * 
	 * @param data data of the type to update
	 * supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@PUT
	@Path("/type")
	public Response updateType(@FormParam("data") String data) {
		return controller.update(data);
	} 
	
	/**
	 * Maps the controller to delete a specific type
	 * 
	 * @param data data of the type to destroy supplied by a form
	 * 		  
	 * @return Response from the controller
	 */
	@DELETE
	@Path("/type")
	public Response destroyType(@FormParam("data") String data) {
		return controller.destroy(data);
	}  
}