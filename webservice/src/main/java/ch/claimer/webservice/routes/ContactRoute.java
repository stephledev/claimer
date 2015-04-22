//package ch.claimer.webservice.routes;
//
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.core.Response;
//
//import ch.claimer.webservice.controller.ContactController;
//import ch.claimer.webservice.services.RouteResponseHandlerService;
//
//
///**
// * Defines RESTful routes for comment specific interactions. Maps the controller according to the URL pattern
// * 
// * @author Stephan Beeler
// */
//@Path("/")
//public class ContactRoute {
//	
//	private ContactController<Response> controller;
//
//	public ContactRoute() {
//		this.controller = new ContactController<Response>(new RouteResponseHandlerService());
//	}
//	
//	/**
//	 * Maps the controller to show contact(s) of a subcontractor
//	 * 
//	 * @param id subcontractor identifier of contact(s) to show supplied by the URL
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed({"EDITOR","EXTERN"})
//	@Path("/contact/supervisor/{id}")
//	public Response showContactsBySubcontractor(@PathParam("id") int id) {
//		return controller.showBySubcontractor(id);
//	}
//}