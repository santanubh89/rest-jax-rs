package com.app.rest.jaxrs.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.rest.messenger.model.Message;

public class InvocationClient {

	public static void main(String[] args) {

		InvocationClient client = new InvocationClient();
		Invocation invocation = client.prepareRequestMessageByAuthor("Santanu");
		Response response = invocation.invoke();
		System.out.println(response.getStatusInfo());

	}

	private Invocation prepareRequestMessageByAuthor(String string) {
		Client client = ClientBuilder.newClient();
		Invocation invocation = client.target("http://localhost:8080/messenger/webapi/").path("messages")
				.queryParam("authorname", string).request(MediaType.APPLICATION_JSON).buildGet();
		return invocation;

	}

}
