//package ch.claimer.webservice.routes;
//
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//
//import ch.claimer.webservice.controller.IssueController;
//import ch.claimer.webservice.services.RouteResponseHandlerService;
//
//
///**
// * Defines RESTful routes for issue specific interactions. 
// * Maps the controller according to the URL pattern
// * 
// * @author Raoul Ackermann
// */
//@Path("/")
//public class IssueRoute {	
//	
//	private IssueController<Response> controller;
//
//	public IssueRoute() {
//		this.controller = new IssueController<Response>(new RouteResponseHandlerService());
//	}
//	
//	/**
//	 * Maps the controller to show issue(s) of a project
//	 * 
//	 * @param id project identifier of issue(s) to show supplied by the URL
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed({"EDITOR","INTERN"})
//	@Path("/issue/project/{id}")
//	public Response showIssuesByProject(@PathParam("id") int id) {
//		return controller.showByProject(id);
//	}
//	
//	/**
//	 * Maps the controller to show issue(s) of a contact
//	 * 
//	 * @param id contact identifier of issue(s) to show supplied by the URL
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed({"EDITOR","EXTERN"})
//	@Path("/issue/contact/{id}")
//	public Response showIssuesByContact(@PathParam("id") int id) {
//		return controller.showByContact(id);
//	}
//	
//	/**
//	 * Maps the controller to show issue(s) of a subcontractor
//	 * 
//	 * @param id subcontractor identifier of issue(s) to show supplied by the URL
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed({"POWER","EXTERN"})
//	@Path("/issue/subcontractor/{id}")
//	public Response showIssuesBySubcontractor(@PathParam("id") int id) {
//		return controller.showBySubcontractor(id);
//	}
//	
//	/**
//	 * Maps the controller to store the specified issue instance
//	 * 
//	 * @param data data of the issue instance to store supplied by a form
//	 * 
//	 * @return Response from the controller
//	 */
//	@POST
//	@RolesAllowed({"EDITOR","INTERN"})
//	@Path("issue")
//	public Response storeIssue(@FormParam("data") String data) {
//		return controller.store(data);
//	} 
//	
//	/**
//	 * Maps the controller to update the specified issue instance
//	 * 
//	 * @param data data of the issue instance to update supplied by a form
//	 * 		  
//	 * @return Response from the controller
//	 */
//	@PUT
//	@RolesAllowed({"EDITOR","INTERN"})
//	@Path("issue")
//	public Response updateIssue(@FormParam("data") String data) {
//		return controller.update(data);
//	} 
//}