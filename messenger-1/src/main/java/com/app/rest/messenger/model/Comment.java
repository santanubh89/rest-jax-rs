package com.app.rest.messenger.model;

public class Comment {

	private String commentId;

	private String comment;

	private String author;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", comment=" + comment + ", author=" + author + "]";
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String commentId, String comment, String author) {
		super();
		this.commentId = commentId;
		this.comment = comment;
		this.author = author;
	}

}
