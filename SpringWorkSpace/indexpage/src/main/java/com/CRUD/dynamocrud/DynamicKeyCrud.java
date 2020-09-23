package com.CRUD.dynamocrud;

import java.util.Random;

import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.config.DynamoDBConfig;

@Repository
public class DynamicKeyCrud {
	private DynamoDBMapper dynamoDBMapper;
	
	private Table table;
	
	public DynamicKeyCrud(DynamoDBMapper dynamoDBMapper,Table table) {
		this.dynamoDBMapper = dynamoDBMapper;
		this.table = table;
	}
	
	public boolean verification(int contactNumber, String dpin) {
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("contact_number", contactNumber);
		Item outcome = table.getItem(spec);
		return outcome.get("dpin").toString().equals(dpin);
	}
	
	public void generateDpin() {
		System.out.println("Generating OTP using random() : "); 
        System.out.print("You OTP is : "); 
        int len = 4;
        // Using numeric values 
        String numbers = "0123456789"; 
        // Using random method 
        Random rndm_method = new Random(); 
        char[] otp = new char[len]; 
        for (int i = 0; i < len; i++) 
        { 
            // Use of charAt() method : to get character value 
            // Use of nextInt() as it is scanning the value as int 
            otp[i] = 
             numbers.charAt(rndm_method.nextInt(numbers.length())); 
	}
        System.out.println(" .................... " + otp.toString());
	}

}
