package com.app.rest.messenger.resource;

import javax.ws.rs.QueryParam;

public class MessageFilterBean {

	@QueryParam("authorname")
	private String author;
	
	@QueryParam("start")
	private int start;
	
	@QueryParam("size")
	private int size;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
}
