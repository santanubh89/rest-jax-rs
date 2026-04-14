package com.jaxrs.messenger.database;

import java.util.HashMap;
import java.util.Map;

import com.jaxrs.messenger.model.Message;
import com.jaxrs.messenger.model.Profile;

public class Database {

	private static Map<Integer, Message> messages = new HashMap<Integer, Message>();
	private static Map<String, Profile> profiles = new HashMap<String, Profile>();

	public static Map<Integer, Message> getMessages() {
		return messages;
	}

	public static Map<String, Profile> getProfiles() {
		return profiles;
	}
	
	
}
