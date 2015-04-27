package ch.claimer.webservice.routes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;

import ch.claimer.webservice.services.AuthenticationService;
import ch.claimer.webservice.services.ResponseHandlerService;
 
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {
    	
    	if(requestContext.getHeaders().get("Authorization") == null) {
    		requestContext.abortWith(ResponseHandlerService.badRequest("Authorization header not found"));
    		return;
    	} 
    	System.out.println(requestContext.getHeaders().get("Authorization"));
    	
    	String basic = requestContext.getHeaders().get("Authorization").get(0);
    	
    	AuthenticationService authentication = new AuthenticationService();
    	if(!authentication.authenticate(basic)) {
    		requestContext.abortWith(ResponseHandlerService.unauthorized("Login not valid"));
    		return;
    	}
    	
    	ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
    	Method method = methodInvoker.getMethod();
    	
    	if (method.isAnnotationPresent(RolesAllowed.class)) {
			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
			List<String> roles = Arrays.asList(rolesAnnotation.value());
			if(!authentication.authorize(roles)) {
				requestContext.abortWith(ResponseHandlerService.unauthorized("Not granted the required roles"));
			}
    	}
    }

}