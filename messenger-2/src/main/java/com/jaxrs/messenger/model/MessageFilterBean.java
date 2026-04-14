package com.jaxrs.messenger.model;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

public class MessageFilterBean {

	private @QueryParam("year") int year;
	private @QueryParam("start") int startLocation;
	private @QueryParam("size") int size;
	private @Context UriInfo uriInfo;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public UriInfo getUriInfo() {
		return uriInfo;
	}
	public void setUriInfo(UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}
	
	
	
}
