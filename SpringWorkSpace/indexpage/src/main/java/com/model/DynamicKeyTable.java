package com.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "DYNAMIC_KEY")
public class DynamicKeyTable {
	private int contactNumber;
	private String dpin;
	
	@DynamoDBHashKey(attributeName = "contact_number")
	public int getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	@DynamoDBAttribute(attributeName = "dpin")
	public String getDpin() {
		return dpin;
	}
	public void setDpin(String dpin) {
		this.dpin = dpin;
	}

}
