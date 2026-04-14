package com.jaxrs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jaxrs.messenger.database.Database;
import com.jaxrs.messenger.model.Comment;
import com.jaxrs.messenger.model.ErrorMessage;
import com.jaxrs.messenger.model.Message;

public class CommentService {

	private Map<Integer, Message> messages = Database.getMessages();

	public CommentService() {
		new MessageService();
		Message message = messages.get(2);
		Comment comment1 = new Comment(1, "First Comment", "First Commentator");
		Comment comment2 = new Comment(2, "Second Comment",
				"Second Commentator");
		message.getComments().put(comment1.getCommentId(), comment1);
		message.getComments().put(comment2.getCommentId(), comment2);
	}

	public List<Comment> getAllComments(int messageId) {
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}

	public Comment getComment(Integer messageId, Integer commentId) {
		Message message = messages.get(messageId);
		ErrorMessage error_message = new ErrorMessage("Not found!", 406,
				"New kind of error!!!!");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(error_message).build();
		if (message == null) {
			throw new WebApplicationException(response);
		}
		Map<Integer, Comment> comments = message.getComments();
		Comment comment = comments.get(commentId);
		if (null == comment) {
			throw new NotFoundException(response);
		}
		return comment;
	}

	public Comment addComment(Integer messageId, Comment comment) {
		Message message = messages.get(messageId);
		comment.setCommentId(message.getComments().size() + 1);
		message.getComments().put(comment.getCommentId(), comment);
		return comment;
	}

	public Comment updateComment(Integer messageId, Comment comment) {
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		if (comment.getCommentId() < 0) {
			return null;
		}
		comments.put(comment.getCommentId(), comment);
		return comment;
	}

	public void deleteComment(Integer messageId, Integer commentId) {
		Map<Integer, Comment> comments = messages.get(messageId).getComments();
		comments.remove(commentId);
	}

}
