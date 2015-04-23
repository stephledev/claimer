//package ch.claimer.webservice.routes;
//
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//
//import ch.claimer.webservice.controller.ProjectController;
//import ch.claimer.webservice.services.RouteResponseHandlerService;
//
//
///**
// * Defines RESTful routes for project specific interactions. 
// * Maps the controller according to the URL pattern
// * 
// * @author Momcilo Bekcic 
// */
//@Path("/")
//public class ProjectRoute {
//	
//	private ProjectController<Response> controller;
//
//	public ProjectRoute() {
//		this.controller = new ProjectController<Response>(new RouteResponseHandlerService());
//	}
//	
//	/**
//	 * Maps the controller to show project(s) of a supervisor
//	 * 
//	 * @param id supervisor identifier of project(s) to show supplied by the URL
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed({"EDITOR","INTERN"})
//	@Path("/project/supervisor/{id}")
//	public Response showProjectsBySupervisor(@PathParam("id") int id) {
//		return controller.showBySupervisor(id);
//	}
//	
//	/**
//	 * Maps the controller to update the specified project instance
//	 * 
//	 * @param data data of the project instance to update supplied by a form
//	 * 		  
//	 * @return Response from the controller
//	 */
//	@PUT
//	@RolesAllowed({"EDITOR","INTERN"})
//	@Path("project")
//	public Response updateProject(@FormParam("data") String data) {
//		return controller.update(data);
//	} 
//	
//}