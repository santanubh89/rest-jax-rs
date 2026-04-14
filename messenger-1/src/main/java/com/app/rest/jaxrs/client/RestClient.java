package com.app.rest.jaxrs.client;

import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.rest.messenger.model.Message;

public class RestClient {

	public static void main(String[] args) {

		String baseUri = "http://localhost:8080/messenger/webapi/";

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(baseUri);
		WebTarget messagetarget = target.path("messages");
		WebTarget singleMessageTarget = messagetarget.path("{messageId}");

		String stringmessage = singleMessageTarget.resolveTemplate("messageId", "M2").request()
				.accept(MediaType.APPLICATION_JSON).get().readEntity(String.class);
		System.out.println(stringmessage);

		Message objectmessage = singleMessageTarget.resolveTemplate("messageId", "M3").request()
				.accept(MediaType.APPLICATION_XML).get().readEntity(Message.class);
		System.out.println(objectmessage);

		Message m = new Message("Ninth Message", "New Author", new Date());

		Response response = messagetarget.request().post(Entity.entity(m, MediaType.APPLICATION_JSON));
		System.out.println("Status Code: " + response.getStatusInfo().getReasonPhrase() + " "
				+ response.getStatusInfo().getStatusCode());
		System.out.println("Created Message ID is: " + response.readEntity(Message.class).getMessageId());
	}

}
