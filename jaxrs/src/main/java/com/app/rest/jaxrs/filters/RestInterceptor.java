package com.app.rest.jaxrs.filters;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

@Provider
public class RestInterceptor implements ReaderInterceptor, WriterInterceptor {

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
		System.out.println("Inside RestInterceptor::aroundReadFrom "+context);
		return null;
	}

	@Override
	public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
		System.out.println("Inside RestInterceptor::aroundWriteTo "+context);
		context.proceed();
	}

}
