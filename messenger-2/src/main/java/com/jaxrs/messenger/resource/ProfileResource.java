package com.jaxrs.messenger.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jaxrs.messenger.model.Profile;
import com.jaxrs.messenger.service.ProfileService;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService service = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles() {
		return service.getAllProfiles();
	}
	
	@POST
	public Profile addUser(Profile user){
		return service.addProfile(user);
	}
	
	@GET
	@Path("/{username}")
	public Profile getUser(@PathParam("username") String username){
		return service.getProfile(username);
	}
	
	@PUT
	@Path("/{username}")
	public Profile updateMessage(@PathParam("username") String username, Profile user){
		user.setUserName(username);
		return service.updateProfile(user);
	}
	
	@DELETE
	@Path("/{username}")
	public void removepProfile(@PathParam("username") String username){
		service.removepProfile(username);
	}
	

}
