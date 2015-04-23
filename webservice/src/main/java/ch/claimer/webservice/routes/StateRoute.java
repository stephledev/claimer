//package ch.claimer.webservice.routes;
//
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.core.Response;
//
//import ch.claimer.shared.models.State;
//import ch.claimer.webservice.controller.DefaultController;
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
//public class StateRoute {	
//	
//	private DefaultController<State, Response> controller;
//
//	public StateRoute() {
//		this.controller = new DefaultController<State, Response>(State.class, new RouteResponseHandlerService());
//	}
//	
//	/**
//	 * Maps the controller to show all states
//	 * 
//	 * @return Response from the controller
//	 */
//	@GET
//	@RolesAllowed("BASIC")
//	@Path("/state")
//	public Response showStates() {
//		return controller.index();
//	}
//}