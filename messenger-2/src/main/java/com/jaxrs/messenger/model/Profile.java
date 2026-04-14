package com.jaxrs.messenger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {

	private int id;
	private String userName;
	private String firstName;
	private Date createdDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Profile(int id, String userName, String firstName) {
		super();
		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.createdDate = new Date();
	}
	public Profile() {
		super();
	}
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", userName=" + userName
				+ ", firstName=" + firstName + ", createdDate=" + createdDate
				+ "]";
	}
	
	
}
