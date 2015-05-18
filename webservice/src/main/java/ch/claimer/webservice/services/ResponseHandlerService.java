package ch.claimer.webservice.services;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 * Hilft eine HTTP-Antwort mit Statuscode und Nachricht an den Client zurückzuliefern.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 */
public class ResponseHandlerService {
	
	protected final static String mediaType = MediaType.APPLICATION_JSON;
	
	/**
	 * Liefert eine erfolgreiche HTTP-Antwort mit Entität(en) zurück.
	 * 
	 * @param model zurückzulieferende Entität(en)
	 * @return Response HTTP-Antwort mit Statuscode und Entität(en)
	 */
	public static Response success(Object model) {
		return Response.status(Status.OK).entity(model).type(mediaType).build();
	}
	
	/**
	 * Liefert eine erfolgreiche HTTP-Antwort zurück.
	 * 
	 * @return Response HTTP-Antwort mit Statuscode
	 */
	public static Response success() {
		return Response.status(Status.OK).build();
	}

	/**
	 * Liefert eine nicht-authetifiziert HTTP-Antwort mit Nachricht zurück.
	 * 
	 * @param message zurückzulieferende Nachricht
	 * @return Response HTTP-Antwort mit Statuscode und Nachricht
	 */
	public static Response unauthorized(String message) {
		return Response.status(Status.UNAUTHORIZED).entity(message).build();
	}

	/**
	 * Liefert eine nicht-gefunden HTTP-Antwort mit Nachricht zurück.
	 * 
	 * @param message zurückzulieferende Nachricht
	 * @return Response HTTP-Antwort mit Statuscode und Nachricht
	 */
	public static Response notFound(String message) {
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

	/**
	 * Liefert eine inkorrekte Anfrage HTTP-Antwort mit Nachricht zurück.
	 * 
	 * @param message zurückzulieferende Nachricht
	 * @return Response HTTP-Antwort mit Statuscode und Nachricht
	 */
	public static Response badRequest(String message) {
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}
	
	/**
	 * Liefert eine interner Server-Fehler HTTP-Antwort mit Nachricht zurück.
	 * 
	 * @param message zurückzulieferende Nachricht
	 * @return Response HTTP-Antwort mit Statuscode und Nachricht
	 */
	public static Response internalServerError(String message) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(message).build();
	}

}