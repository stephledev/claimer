package ch.claimer.client.proxy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 
 * @author Kevin Stadelmann
 *
 */

public interface GCEmployeeProxy {
	@GET
	@Path("/gcemployee")
	@Produces(MediaType.APPLICATION_JSON)
	String getGcemployeeAll();
	
	@GET
	@Path("/gcemployee/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getGcemployee();
	

}
