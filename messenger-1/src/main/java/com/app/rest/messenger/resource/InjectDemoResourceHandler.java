package com.app.rest.messenger.resource;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

@Path("injectdemo")
@Produces(MediaType.APPLICATION_JSON)
public class InjectDemoResourceHandler {

	@GET
	@Path("annotations")
	public String injectDemo(@MatrixParam("param") String matrixParam, @HeaderParam("header1") String headerParam1,
			@HeaderParam("header2") String headerParam2, @CookieParam("Cookie_3") String cookieParam) {
		String response = "Matrix Param: " + matrixParam + " \n";
		response += "Header Param: " + headerParam1 + " " + headerParam2 + "\n";
		response += "Cookie Param: " + cookieParam;
		return response;
	}

	@GET
	@Path("context")
	public String getParamsUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
		String response = "";
		for (String headername : requestHeaders.keySet()) {
			response += headername + " : " + requestHeaders.get(headername)+"\n";
		}
		return response;
	}

}