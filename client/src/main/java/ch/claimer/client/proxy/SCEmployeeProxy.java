package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.SCEmployee;

/**
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @version 1.0
 * @since 1.0
 *
 */

public interface SCEmployeeProxy {
	@GET
	@Path("scemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("scemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById();
	
	@GET
	@Path("scemployee/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySubcontractor();
	
    @POST
    @Path("scemployee/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void create();
    
    @PUT
    @Path("scemployee/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(SCEmployee se);

	

}
