package com.jaxrs.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jaxrs.messenger.model.ErrorMessage;

@Provider
public class NoMessageFoundExceptionMapper implements
		ExceptionMapper<NoMessageFoundException> {

	@Override
	public Response toResponse(NoMessageFoundException e) {
		ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), 404, "No Docs Needed!");
		return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
	}
}