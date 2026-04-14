package com.app.rest.jaxrs.custommedia;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("custommedia")
public class CustomMediaDemo {
	
	@GET
	@Produces("text/shortdate")
	public Date getDate() {
		return new Date();
	}

}
