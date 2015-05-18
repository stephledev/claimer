package ch.claimer.client.proxy;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.claimer.shared.models.Comment;

/**
 * @author Kevin Stadelmann
 * @author Stephan Beeler
 * @since 1.0
 * @version 1.1
 */

public interface CommentProxy {
	@GET
	@Path("comment/supervisor/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getBySupervisor(@PathParam("id")int id);
	
	@GET
	@Path("comment/contact/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByContact(@PathParam("id")int id);
	
	@GET
	@Path("comment/issue/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	String getByIssue(@PathParam("id")int id);
	
}
