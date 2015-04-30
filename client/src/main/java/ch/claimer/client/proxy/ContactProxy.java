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

public interface ContactProxy {
	@GET
	@Path("/contact/subcontractor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getSubcontractorById();
}
