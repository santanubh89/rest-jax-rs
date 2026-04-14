package com.app.rest.messenger.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.app.rest.messenger.exception.MessageNotFoundException;
import com.app.rest.messenger.model.Message;
import com.app.rest.messenger.service.MessageService;

@Path("/messages")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResourceHandler {

	static MessageService service = new MessageService();

	@GET
	public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		if (filterBean.getAuthor() != null) {
			return service.getMessagesbyUser(filterBean.getAuthor());
		} else if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
			return service.geAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		} else {
			return service.getAllMessages();
		}
	}

	@GET
	@Path("{messageId}")
	public Message getMessage(@PathParam("messageId") String messageId, @Context UriInfo uriInfo) {
		Message message = service.getMessage(messageId);
		if (null == message) {
			throw new MessageNotFoundException("Message with ID " + messageId + " is not Present.");
		}
		message.addLink(getSelfUri(messageId, uriInfo), "self");
		message.addLink(getProfileUri(message, uriInfo), "profile");
		message.addLink(getCommentsUri(messageId, uriInfo), "comments");
		return message;
	}

	@POST
	public Response addMessage(@Context UriInfo uriInfo, Message message) {
		Message addMessage = service.addMessage(message);
		URI uri = uriInfo.getAbsolutePathBuilder().path(addMessage.getMessageId()).build();
		return Response.status(Status.CREATED).entity(addMessage).header("customKey", "customKeyValue").location(uri)
				.link("/messengerv2/webapi/messages/" + addMessage.getMessageId(), "self").build();
	}

	@POST
	@Path("batch")
	public Message addBatchMessage(List<Message> messages) {
		messages.forEach((message) -> service.addMessage(message));
		return null;
	}

	@PUT
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") String messageId, Message message) {
		message.setMessageId(messageId);
		return service.updateMessage(message);
	}

	@DELETE
	@Path("{messageId}")
	public Message deleteMessage(@PathParam("messageId") String messageId) {
		return service.deleteMessage(messageId);
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

	private String getProfileUri(Message message, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
	}

	private String getSelfUri(String messageId, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(MessageResourceHandler.class).path(messageId).build().toString();
	}

	private String getCommentsUri(String messageId, UriInfo uriInfo) {
		return uriInfo.getBaseUriBuilder().path(MessageResourceHandler.class)
				.path(MessageResourceHandler.class, "getCommentResource").path(CommentResource.class)
				.resolveTemplate("messageId", messageId).build().toString();
	}

}
