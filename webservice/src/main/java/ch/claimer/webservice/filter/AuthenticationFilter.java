package ch.claimer.webservice.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;

import ch.claimer.webservice.services.AuthenticationService;
import ch.claimer.webservice.services.ResponseHandlerService;

/**
 * Filter eingehende HTTP-Anfragen auf einen Authentication-Header und
 * authentifiziert den Benutzer. Prüft ob der Benutzer die nötigen Rollen hat,
 * die von den Routes verlangt werden. Gibt entsprechende HTTP-Status-Codes
 * zurück.
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 */
@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {

		ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext
				.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
		Method method = methodInvoker.getMethod();

		if (method.isAnnotationPresent(PermitAll.class)) {
			return;
		}
		if (requestContext.getHeaders().get("Authorization") == null) {
			requestContext.abortWith(ResponseHandlerService
					.badRequest("Authorization header not found"));
			return;
		}
		String basic = requestContext.getHeaders().get("Authorization").get(0);

		AuthenticationService authentication = new AuthenticationService();
		if (!authentication.authenticate(basic)) {
			requestContext.abortWith(ResponseHandlerService
					.unauthorized("Login not valid"));
			return;
		}

		if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = method
					.getAnnotation(RolesAllowed.class);
			List<String> roles = Arrays.asList(rolesAnnotation.value());
			if (!authentication.authorize(roles)) {
				requestContext.abortWith(ResponseHandlerService
						.unauthorized("Not granted the required roles"));
			}
		}
	}

}