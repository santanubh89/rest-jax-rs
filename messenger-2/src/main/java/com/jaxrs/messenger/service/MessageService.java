package com.jaxrs.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.jaxrs.messenger.database.Database;
import com.jaxrs.messenger.exception.InvalidMessageIdException;
import com.jaxrs.messenger.model.Message;

public class MessageService {

	private Map<Integer, Message> messages = Database.getMessages();

	public MessageService() {
		messages.put(1, new Message(1, "First Message", "Author1"));
		messages.put(2, new Message(2, "Second Message", "Author2"));
	}

	public List<Message> getAllMessage() {
		List<Message> messageList = new ArrayList<Message>(messages.values());
		return messageList;
	}

	public List<Message> getAllMessagesForYear(int year) {
		List<Message> messageList = new ArrayList<Message>();
		Calendar cal = Calendar.getInstance();
		for (Message message : messages.values()) {
			cal.setTime(message.getCreatedDate());
			if (cal.get(Calendar.YEAR) == year) {
				messageList.add(message);
			}
		}
		return messageList;
	}

	public List<Message> getPaginatedMessageList(int startLocation, int size) {
		List<Message> paginatedMessageList = new ArrayList<Message>();
		List<Message> allMessages = new ArrayList<Message>(messages.values());
		if (startLocation >= messages.size()) {
			return new ArrayList<Message>();
		} else if ((startLocation + size) >= messages.size()) {
			size = messages.size() - startLocation;
		}
		paginatedMessageList = allMessages.subList(startLocation, startLocation
				+ size);
		return paginatedMessageList;

	}

	public Message getMessage(int messageId) {
		if (messageId < 1) {
			throw new InvalidMessageIdException("Message ID " + messageId
					+ " is not a valid ID.");
		}
		if (messageId > 10) {
			throw new RuntimeException("Message ID is too long!");
		}
		Message message = messages.get(messageId);
		return message;
	}

	public Message addMessage(Message message) {
		message.setMessageId(messages.size() + 1);
		messages.put(message.getMessageId(), message);
		return message;
	}

	public Message updateMessage(Message message) {
		if (message.getMessageId() <= 0) {
			return null;
		}
		messages.put(message.getMessageId(), message);
		return message;

	}

	public Message removeMessage(int messageId) {
		if (messageId <= 0) {
			return null;
		}
		if(null!=messages.get(messageId)){
			return messages.remove(messageId);
		}else{
			throw new RuntimeException("Nothing to delete!");
		}
		

	}
}
