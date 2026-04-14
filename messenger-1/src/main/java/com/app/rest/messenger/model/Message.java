package com.app.rest.messenger.model;

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

	private static final long serialVersionUID = 1L;

	private String messageId;

	private String messageValue;

	private String author;

	private Date createdDate;
	
	@XmlTransient
	private Map<String, Comment> comments = new HashMap<>();
	
	@XmlTransient
	private List<Link> links = new ArrayList<>();

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Message [messageId=" + messageId + ", messageValue=" + messageValue + ", author=" + author
				+ ", createdDate=" + createdDate + "]";
	}

	public Message(String messageId, String messageValue, String author, Date createdDate) {
		super();
		this.messageId = messageId;
		this.messageValue = messageValue;
		this.author = author;
		this.createdDate = createdDate;
	}

	public Message(String messageValue, String author, Date createdDate) {
		super();
		this.messageValue = messageValue;
		this.author = author;
		this.createdDate = createdDate;
	}

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	@XmlTransient
	public Map<String, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<String, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel) {
		Link link = new Link(url, rel);
		this.links.add(link);
	}

}
