package com.app.rest.jaxrs.paramconverter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("date/{dateString}")
public class DateResource {
	
	@GET
	public String getRequestedDate(@PathParam("dateString") MyDate date) {
		return "Got: "+date;
	}

}
