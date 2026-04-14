package com.jaxrs.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Comment {

	private int commentId;
	private String commentValue;
	private String author;
	private Date createdDate;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentValue() {
		return commentValue;
	}

	public void setCommentValue(String commentValue) {
		this.commentValue = commentValue;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Comment(int commentId, String commentValue, String author) {
		super();
		this.commentId = commentId;
		this.commentValue = commentValue;
		this.author = author;
		this.createdDate = new Date();
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", commentValue="
				+ commentValue + ", author=" + author + ", createdDate="
				+ createdDate + "]";
	}

}
