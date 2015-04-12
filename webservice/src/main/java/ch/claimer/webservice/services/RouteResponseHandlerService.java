package ch.claimer.webservice.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

public class RouteResponseHandlerService implements ResponseHandlerService<Response> {
	
	private ResponseBuilder builder;
	protected final String mediaType = MediaType.APPLICATION_JSON;
	
	@Override
	public RouteResponseHandlerService success() {
		builder = Response.status(Status.OK);
		return this;
	}

	@Override
	public RouteResponseHandlerService entity(String model) {
		builder.entity(model).type(mediaType);
		return this;
	}

	@Override
	public Response build() {
		return builder.build();
	}

}
