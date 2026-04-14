package com.app.rest.jaxrs.bodywriter;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("datewriter")
public class MessageBodyWriterDemo {
	
	@GET
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	public Date getDate() {
		return new Date();
	}

}
