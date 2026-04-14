package com.jaxrs.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jaxrs.messenger.model.Comment;
import com.jaxrs.messenger.service.CommentService;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {
	
	CommentService service = new CommentService();

	@GET
	public List<Comment> getAllComments(@PathParam("messageId") int messageId){
		return service.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getComment(@PathParam("messageId") int messageId, 
							@PathParam("commentId") int commentId){
		return service.getComment(messageId, commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") int messageId, Comment comment){
		return service.addComment(messageId, comment);
	}
	
	@PUT
	public Comment updateComment(@PathParam("messageId") int messageId, Comment comment){
		return service.addComment(messageId, comment);
	}
	
	@DELETE
	@Path("/{commentId}")
	public void deleteComment(@PathParam("messageId") int messageId,
							@PathParam("commentId") Integer commentId){
		service.deleteComment(messageId, commentId);
	}
}
