package com.app.rest.jaxrs.filters;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class RestResponseFilter implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		Object response = responseContext.getEntity();
		if (response instanceof String) {
			String responseString = (String) response;
			responseContext.setEntity("Filter Added to "+responseString);
		}
		responseContext.getHeaders().add("X-Powered-By", "Santanu the Developer");
	}

}
