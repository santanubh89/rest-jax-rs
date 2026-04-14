package com.app.rest.messenger.resource;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.app.rest.messenger.model.Comment;
import com.app.rest.messenger.service.CommentService;

@Path("/")
public class CommentResource {
	
	static CommentService service = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") String messageId) {
		return service.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") String messageId, @PathParam("commentId") String commentId) {
		return service.getComment(messageId, commentId);
	}

	@POST
	public Comment addComment(@PathParam("messageId") String messageId, Comment comment) {
		return service.addComment(messageId, comment);
	}

	@PUT
	@Path("/{commentId}")
	public Comment updateComment(@PathParam("messageId") String messageId, @PathParam("commentId") String commentId, Comment comment) {
		comment.setCommentId(commentId);
		return service.updateComment(messageId, comment);
	}

	@DELETE
	@Path("/{commentId}")
	public Comment deleteComment(@PathParam("messageId") String messageId, @PathParam("commentId") String commentId) {
		return service.deleteComment(messageId, commentId);
	}

}
