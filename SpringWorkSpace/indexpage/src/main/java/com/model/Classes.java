package com.model;

import java.util.ArrayList;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Classes")
public class Classes {
	
	private String ClassName;
	private int Level;
	private ArrayList<String> Section;
	
	public Classes() {
	}
	
	public Classes(String ClassName, int Level, ArrayList<String> Section) {
		this.ClassName = ClassName;
		this.Level = Level;
		this.Section = Section;
	}
	
	@DynamoDBHashKey(attributeName = "ClassName")
	public String getClassName() {
		return ClassName;
	}
	
	public void setClassName(String ClassName){
		this.ClassName = ClassName;
	}
	
	@DynamoDBAttribute(attributeName = "Level")
	public int getLevel() {
		return Level;
	}
	
	public void setLevel(int Level){
		this.Level = Level;
	}
	
	@DynamoDBAttribute(attributeName = "Section")
	public ArrayList<String> getSection() {
		return Section;
	}
	
	public void setSection(ArrayList<String> sections) {
		this.Section = sections;
	}
	
	public void addSection(String section) {
		this.Section.add(section);
	}
	

}
