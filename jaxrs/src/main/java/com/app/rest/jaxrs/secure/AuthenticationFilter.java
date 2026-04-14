package com.app.rest.jaxrs.secure;

import java.io.IOException;
import java.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains("secure")) {
			authenticate(requestContext);
		} else {
			return;
		}
		
	}

	private void authenticate(ContainerRequestContext requestContext) {
		String authorization = requestContext.getHeaderString("Authorization");
		authorization = authorization.replace("Basic ", "");
		String authString[] = new String(Base64.getDecoder().decode(authorization)).split(":");
		String username = authString[0];
		String password = authString[1];
		System.out.println("Username: " + username + ", Password: " + password);
		if (username.equals("Santanu") && password.equals("Bhattacharyya")) {
			System.out.println("Authentication successful");
		} else {
			Response unauthorized = Response.status(Status.UNAUTHORIZED).entity("You are not authorized!").build();
			requestContext.abortWith(unauthorized);
		}
	}

}
