package ch.claimer.webservice.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseHandlerService {
	
	protected final static String mediaType = MediaType.APPLICATION_JSON;
	
	public static Response success(Object model) {
		return Response.status(Status.OK).entity(model).type(mediaType).build();
	}

	public static Response unauthorized(String message) {
		return Response.status(Status.UNAUTHORIZED).entity(message).build();
	}

	public static Response notFound(String message) {
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

	public static Response badRequest(String message) {
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}

}