package com.app.rest.jaxrs.bodywriter;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.TEXT_PLAIN)
public class DateMessageBodyWriter implements MessageBodyWriter<Date> {

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return Date.class.isAssignableFrom(type);
	}

	@Override
	public void writeTo(Date date, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {
		/**
		System.out.println("Media Type: "+mediaType);
		if (mediaType.equals(MediaType.TEXT_PLAIN)) {
			entityStream.write(date.toString().getBytes());
		}
		if (mediaType.equals(MediaType.APPLICATION_JSON)) {
			String response = "{\"date\":\"Date!!\"}";
			entityStream.write(response.getBytes());
		}
		*/
		entityStream.write(date.toString().getBytes());
	}

}
