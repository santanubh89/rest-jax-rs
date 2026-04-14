package com.jaxrs.messenger.resource;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.jaxrs.messenger.exception.NoMessageFoundException;
import com.jaxrs.messenger.model.ErrorMessage;
import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.model.MessageFilterBean;
import com.jaxrs.messenger.service.MessageService;

@Path(value = "/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//Set the "Accept" header as "application/json" or "application/xml" for different op formats.
public class MessageResource {

	MessageService service = new MessageService();

	/*
	 * @GET public List<Message> getAllMessages(@QueryParam("year") int year,
	 * 
	 * @QueryParam("start") int startLocation,
	 * 
	 * @QueryParam("size") int size) { if (year != 0) { return
	 * service.getAllMessagesForYear(year); } else if (size != 0) { return
	 * service.getPaginatedMessageList(startLocation, size); } return
	 * service.getAllMessage(); }
	 */

	@GET
	public Response getAllMessages(
			@BeanParam MessageFilterBean messageFilterBean)
			throws NoMessageFoundException, UnknownHostException {
		List<Message> messageList = new ArrayList<Message>();
		if (messageFilterBean.getYear() != 0) {
			messageList = service.getAllMessagesForYear(messageFilterBean
					.getYear());
		} else if (messageFilterBean.getSize() != 0) {
			messageList = service.getPaginatedMessageList(
					messageFilterBean.getStartLocation(),
					messageFilterBean.getSize());
		} else {
			messageList = service.getAllMessage();
		}
		for (Message message : messageList) {
			UriInfo uriInfo = messageFilterBean.getUriInfo();
			if (message.getLinks().size() == 0) {
				setResourceLinks(message, message.getMessageId(), uriInfo);
			}

		}
		GenericEntity<List<Message>> messageListEntity = new GenericEntity<List<Message>>(
				messageList) {
		};
		if (null != messageList && messageList.size() > 0) {
			Response response = Response
					.status(Status.FOUND)
					.entity(messageListEntity)
					.header("server time", new Date())
					.header("server address",
							InetAddress.getLocalHost().getHostAddress())
					.build();
			return response;
		} else {
			throw new NoMessageFoundException(
					"Message for the given criteria not found!");
		}
	}

	@GET
	@Path("/{messageId}")
	public Response getMessage(@PathParam("messageId") int messageId,
			@Context UriInfo uriInfo) {
		Message message = service.getMessage(messageId);
		if (null == message) {
			//return Response.status(Status.NOT_ACCEPTABLE).build();
			throw new RuntimeException("What the hell!!");
			// return null;
		} else {
			if (message.getLinks().size() == 0) {
				setResourceLinks(message, messageId, uriInfo);
			}

			return Response.status(Status.FOUND).entity(message).build();
		}

	}

	@POST
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message createdMessage = service.addMessage(message);
		String newId = String.valueOf(createdMessage.getMessageId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		setResourceLinks(createdMessage, Integer.parseInt(newId), uriInfo);
		return Response.created(uri).entity(createdMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Response updateMessage(@PathParam("messageId") int messageId,
			Message message, @Context UriInfo uriInfo) {
		message.setMessageId(messageId);
		setResourceLinks(message, messageId, uriInfo);
		return Response.status(Status.ACCEPTED)
				.entity(service.updateMessage(message)).build();
	}

	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") int messageId) {
		try{
			return Response.status(Status.OK).entity(service.removeMessage(messageId)).build();
		}catch (Exception e){
			ErrorMessage error = new ErrorMessage(e.getMessage(), 410, "Don't delete what's not there");
			return Response.status(Status.GONE).entity(error).build();
		}
	}

	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}

	private void setResourceLinks(Message message, int messageId,
			UriInfo uriInfo) {

		message.addLink("allMessage", getAllMessagesUri(uriInfo));
		message.addLink("self", getSelfUri(uriInfo, message.getMessageId()));
		message.addLink("author", getAuthorUri(uriInfo, message));
		message.addLink("comments", getCommentsUri(uriInfo, message));

	}

	private String getAllMessagesUri(UriInfo uriInfo) {
		String messagesPath = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class).build().toString();
		return messagesPath;
	}

	private String getSelfUri(UriInfo uriInfo, int messageId) {
		String messageUri = "";
		messageUri = uriInfo.getBaseUriBuilder().path(getClass())
				.path(String.valueOf(messageId)).build().toString();
		return messageUri;
	}

	private String getAuthorUri(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(ProfileResource.class)
				.path(message.getAuthor()).build().toString();
	}

	private String getCommentsUri(UriInfo uriInfo, Message message) {
		return uriInfo.getBaseUriBuilder().path(MessageResource.class)
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getMessageId()).build()
				.toString();
	}

}