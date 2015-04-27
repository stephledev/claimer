package ch.claimer.webservice.routes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;

import ch.claimer.webservice.services.AuthenticationService;
 
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {
    	
    	
    	String basic = requestContext.getHeaderString("Authorization");
    	System.out.print(basic);
    	
    	AuthenticationService authentication = new AuthenticationService();
    	if(!authentication.authenticate(basic)) {
    		requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
    	}
    	
    	ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
    	Method method = methodInvoker.getMethod();
    	
    	if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
			List<String> roles = Arrays.asList(rolesAnnotation.value());
			if(!authentication.authorize(roles)) {
				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
			}
    	}
    }

}