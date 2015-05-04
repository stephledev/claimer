package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Subcontractor;

/**
 * 
 * @author Kevin Stadelmann
 * @author Momcilo Bekcic
 * @author Stephan Beeler
 * @version 1.1
 * @since 1.0
 *
 */

public interface SubcontractorProxy {
	@GET
	@Path("subcontractor")
	@Produces(MediaType.APPLICATION_JSON)
	String getAll();
	
	@GET
	@Path("subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getById(@PathParam("id")int id);
	
    @POST
    @Path("subcontractor")
    @Consumes(MediaType.APPLICATION_JSON)
    void create(Subcontractor subcontractor);
    
    @PUT
    @Path("subcontractor")
    @Consumes(MediaType.APPLICATION_JSON)
    void update(Subcontractor subcontractor);

	

}
