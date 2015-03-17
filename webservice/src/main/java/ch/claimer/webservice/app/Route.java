package ch.claimer.webservice.app;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.inject.Guice;
import com.google.inject.Injector;

import ch.claimer.webservice.controller.GeneralContractorController;
import ch.claimer.webservice.injector.GeneralContractorControllerInjector;
import ch.claimer.webservice.repositories.hibernate.HibernateGeneralContractorRepository;
 
@Path("/")
public class Route {
	
	Injector injector = Guice.createInjector(new GeneralContractorControllerInjector());
    GeneralContractorController gcc = injector.getInstance(GeneralContractorController.class);
    
    HibernateGeneralContractorRepository hgcr = new HibernateGeneralContractorRepository();
 
	@GET
	@Path("/generalcontractor/{id}")
	public Response getGeneralContractorById(@PathParam("id") int id) {
		return gcc.show(id);
	} 

 
 
}