package ch.claimer.webservice.routes;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.core.ResourceMethodInvoker;
 
@Provider
public class AuthenticationFilter implements ClientRequestFilter {

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		
	}

}