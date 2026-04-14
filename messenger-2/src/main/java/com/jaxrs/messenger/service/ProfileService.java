package com.jaxrs.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaxrs.messenger.database.Database;
import com.jaxrs.messenger.model.Profile;

public class ProfileService {
	
	private Map<String, Profile> profiles = Database.getProfiles();
	
	public ProfileService(){
		profiles.put("Author1", new Profile(1, "Author1", "First Author"));
		profiles.put("Author2", new Profile(2, "Author2", "Second Author"));
	}

	public List<Profile> getAllProfiles(){
		List<Profile> profileList = new ArrayList<Profile>(profiles.values());
		return profileList;
	}
	
	public Profile getProfile(String username){
		Profile profile = profiles.get(username);
		return profile;
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size()+1);
		profiles.put(profile.getUserName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getUserName().isEmpty()){
			return null;
		}
		profiles.put(profile.getUserName(), profile);
		return profile;
	}
	
	public Profile removepProfile(String username){
		if(username == null){
			return null;
		}
		return profiles.remove(username);
	}
}
