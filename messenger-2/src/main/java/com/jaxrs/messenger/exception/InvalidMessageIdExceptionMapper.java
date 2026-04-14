package com.jaxrs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jaxrs.messenger.model.ErrorMessage;

@Provider
//Also register the package in web xml
public class InvalidMessageIdExceptionMapper implements ExceptionMapper<InvalidMessageIdException> {

	@Override
	public Response toResponse(InvalidMessageIdException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "My Website" );
		return Response.status(Status.BAD_REQUEST).entity(errorMessage).build();
	}

}
