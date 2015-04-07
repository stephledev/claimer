package ch.claimer.webservice.routes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;
import org.jboss.resteasy.util.Base64;
 
@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
    @Override
    public void filter( ContainerRequestContext requestContext ) throws IOException {
    	ResourceMethodInvoker methodInvoker = (ResourceMethodInvoker) requestContext.getProperty("org.jboss.resteasy.core.ResourceMethodInvoker");
        System.out.println(methodInvoker.toString());
    	Method method = methodInvoker.getMethod();

        
        if(method.isAnnotationPresent(PermitAll.class))
        {
        	return;
        }

        if(method.isAnnotationPresent(DenyAll.class))
        {
        	requestContext.abortWith(Response.status(Status.FORBIDDEN).build());
            return;
        }
        
        final MultivaluedMap<String, String> headers = requestContext.getHeaders();
		final List<String> authorization = headers.get("Authorization");

//		if (authorization == null || authorization.isEmpty()) {
//			requestContext.abortWith(Response.status(Status.UNAUTHORIZED).build());
//			return;
//		}
//
//		String credentials = authorization.get(0).replaceFirst("Basic" + " ", "");
//
//		try {
//			credentials = new String(Base64.decode(credentials));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		
//		final String username = credentials.split(":")[0];
//		final String password = credentials.split(":")[1];
//
//		if (method.isAnnotationPresent(RolesAllowed.class)) {
//			RolesAllowed rolesAnnotation = method.getAnnotation(RolesAllowed.class);
//			Set<String> rolesSet = new HashSet<String>(Arrays.asList(rolesAnnotation.value()));
//
//			if (!isUserAllowed(username, password, rolesSet)) {
//				requestContext.abortWith(Response.status(Status.UNAUTHORIZED).entity("Later loooser").build());
//				return;
//			}
//		}
    }
    
    private boolean isUserAllowed(final String username, final String password,	final Set<String> rolesSet) {
		boolean isAllowed = true;

		return isAllowed;
	}

}