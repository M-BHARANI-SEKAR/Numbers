package com.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "USER_REGISTRATION")
public class UserRegistration {
	
	public UserRegistration(String userName, int contactNumber) {
		super();
		this.userName = userName;
		this.contactNumber = contactNumber;
	}
	private String userName;
	private int contactNumber;
	private int pot;
	private String conf;
	private String role;
	
	@DynamoDBHashKey(attributeName = "contact_number")
	public int getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@DynamoDBAttribute(attributeName = "user_name")
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@DynamoDBAttribute(attributeName = "pot")
	public int getPot() {
		return pot;
	}
	public void setPot(int pot) {
		this.pot = pot;
	}
	
	@DynamoDBAttribute(attributeName = "conf")
	public String getConf() {
		return conf;
	}
	public void setConf(String conf) {
		this.conf = conf;
	}
	
	@DynamoDBAttribute(attributeName = "role")
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
