package com.app.rest.messenger.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.app.rest.messenger.model.Message;
import com.app.rest.messenger.model.Profile;

public class Database {
	
	private static Map<String, Message> messages = new HashMap<String, Message>();
	
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();
	
	static {
		messages.put("M1", new Message("M1", "First Message", "Santanu", new Date()));
		messages.put("M2", new Message("M2", "Second Message", "Taniya", new Date()));
		messages.put("M3", new Message("M3", "Third Message", "Ratul", new Date()));
	}
	
	public static Map<String, Message> getMessages() {
		return messages;
	}
	
	public static Map<String, Profile> getProfiles() {
		return profiles;
	}

}
