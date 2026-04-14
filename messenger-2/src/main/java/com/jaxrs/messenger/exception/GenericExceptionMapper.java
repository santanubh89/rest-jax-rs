package com.jaxrs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jaxrs.messenger.model.ErrorMessage;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable t) {
		ErrorMessage errorMessage = new ErrorMessage(t.getMessage(), 406,
				"Follow the books!");
		return Response.status(Status.NOT_ACCEPTABLE).entity(errorMessage)
				.build();
	}

}
