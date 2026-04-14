package com.app.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.app.rest.messenger.exception.ErrorMessage;
import com.app.rest.messenger.model.Comment;
import com.app.rest.messenger.model.Message;

public class CommentService {
	
	private Map<String, Message> messages = Database.getMessages();

	public CommentService() {
	}

	public List<Comment> getAllComments(String messageId) {
		Map<String, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(String messageId, String commentId) {
		Message message = messages.get(messageId);
		if (message == null) {
			throw new WebApplicationException("Message Not Found", Status.BAD_REQUEST);
		}
		Map<String, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (comment == null) {
			throw new NotFoundException(Response.status(Status.EXPECTATION_FAILED).entity(new ErrorMessage("Comment Not Found", 5000, "No Docs")).build());
		}
		return comment;
	}
	public Comment addComment(String messageId, Comment comment) {
		Map<String, Comment> comments = messages.get(messageId).getComments();
		comment.setCommentId("C"+(comments.size()+1));
		comments.put(comment.getCommentId(), comment);
		return comment;
	}
	
	public Comment updateComment(String messageId, Comment comment) {
		Map<String, Comment> comments = messages.get(messageId).getComments();
		comments.put(comment.getCommentId(), comment);
		return comment;
	}
	
	public Comment deleteComment(String messageId, String commentId) {
		Map<String, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}

}
