package com.app.rest.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class MessageNotFoundExceptionMapper implements ExceptionMapper<MessageNotFoundException> {

	@Override
	public Response toResponse(MessageNotFoundException exception) {
		Response response = Response.status(Status.BAD_GATEWAY).entity(new ErrorMessage(exception.getMessage(), 3000, "Documentation Link")).build();
		return response;
	}

}
