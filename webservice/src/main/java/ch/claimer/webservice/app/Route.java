package ch.claimer.webservice.app;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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

import ch.claimer.webservice.controller.GeneralContractorController;
import ch.claimer.webservice.injector.GeneralContractorControllerInjector;
 
@Path("/")
public class Route {
	
	Injector injector = Guice.createInjector(new GeneralContractorControllerInjector());
    GeneralContractorController gcc = injector.getInstance(GeneralContractorController.class);
 
    @RolesAllowed("Assistant")
    @GET
	@Path("/generalcontractor")
	public Response getAllGeneralContractor() {
		return gcc.index();
	} 
    
	@GET
	@Path("/generalcontractor/{id}")
	public Response getGeneralContractorById(@PathParam("id") int id) {
		return gcc.show(id);
	}
	
	@POST
	@Path("/generalcontractor")
	public Response storeGeneralContractor(@FormParam("data") String data) {
		return gcc.store(data);
	} 
	
	@PUT
	@Path("/generalcontractor")
	public Response updateGeneralContractor(@FormParam("data") String data) {
		return gcc.update(data);
	} 
	
	@DELETE
	@Path("/generalcontractor")
	public Response destroyGeneralContractor(@FormParam("data") String data) {
		return gcc.destroy(data);
	} 
	
	
	
	
 
}