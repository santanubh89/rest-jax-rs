package com.jaxrs.messenger.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int messageId;
	private String messageValue;
	private String author;
	private Date createdDate;
	private Map<Integer, Comment> comments = new HashMap<Integer, Comment>();
	private List<Link> links = new ArrayList<Link>();

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageValue() {
		return messageValue;
	}

	public void setMessageValue(String messageValue) {
		this.messageValue = messageValue;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Message(int messageId, String messageValue, String author) {
		super();
		this.messageId = messageId;
		this.messageValue = messageValue;
		this.author = author;
		this.createdDate = new Date();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageValue="
				+ messageValue + ", author=" + author + ", createdDate="
				+ createdDate + ", comments=" + comments + ", links=" + links
				+ "]";
	}

	@XmlTransient
	public Map<Integer, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Integer, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String rel, String url){
		Link link = new Link();
		link.setRel(rel);
		link.setLink(url);
		links.add(link);
	}

}
