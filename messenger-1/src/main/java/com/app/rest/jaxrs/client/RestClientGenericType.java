package com.app.rest.jaxrs.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.rest.messenger.model.Message;

public class RestClientGenericType {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		List<Message> messages = client.target("http://localhost:8080/messenger/webapi/").path("messages")
				.queryParam("authorname", "Santanu").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Message>>() {
				});

		System.out.println("Returned Message is: " + messages);
	}

}
