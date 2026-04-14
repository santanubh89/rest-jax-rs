package com.app.rest.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.app.rest.messenger.model.Message;

public class MessageService {
	
	private Map<String, Message> messages = Database.getMessages();

	public MessageService() {
	}

	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}
	
	public Message getMessage(String messageId) {
		return messages.get(messageId);
	}
	
	public List<Message> geAllMessagesPaginated(int start, int size) {
		List<Message> allmessages = new ArrayList<Message>(messages.values());
		if ((start + size) > allmessages.size()) return new ArrayList<Message>();
		return allmessages.subList(start, start+size);
	}

	public List<Message> getMessagesbyUser(String username) {
		List<Message> allmessages = new ArrayList<Message>(messages.values());
		List<Message> filteredMessages = new ArrayList<Message>();
		allmessages.stream().filter((m) -> m.getAuthor().equals(username)).forEach(m -> filteredMessages.add(m));
		return filteredMessages;
	}
	
	public Message addMessage(Message message) {
		message.setMessageId("M"+(messages.size()+1));
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message updateMessage(Message message) {
		messages.put(message.getMessageId(), message);
		return message;
	}
	
	public Message deleteMessage(String messageId) {
		return messages.remove(messageId);
	}

}
