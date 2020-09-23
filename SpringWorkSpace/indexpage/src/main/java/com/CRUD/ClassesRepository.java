package com.CRUD;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.AmazonDynamoDBException;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.config.DynamoDBConfig;
import com.model.Classes;

@Repository
public class ClassesRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(ClassesRepository.class);

	//@Autowired
	private DynamoDBMapper dynamoDBMapper;
	
	public ClassesRepository(DynamoDBMapper dynamoDBMapper) {
		this.dynamoDBMapper = dynamoDBMapper;
		// TODO Auto-generated constructor stub
	}

	public Classes getSectionsForClass(String targetClass) {
		Table table = dbconfig();
		GetItemSpec spec = new GetItemSpec().withPrimaryKey("ClassName",targetClass);
		Item outcome = table.getItem(spec);
		System.out.println(outcome);
		outcome.get("ClassName");
		outcome.get("Level");
		Classes record = new Classes();
		record.setClassName(outcome.get("ClassName").toString());
		record.setLevel(Integer.parseInt(outcome.get("Level").toString()));
		record.setSection((ArrayList<String>) outcome.get("Section"));
				//new Classes(outcome.get("ClassName").toString(), Integer.parseInt(outcome.get("Level").toString()), outcome.get("Section"));
//		DynamoDBQueryExpression<Classes> queryExpressionn = new DynamoDBQueryExpression<Classes>()
//			.withKeyConditionExpression("ClassName="+targetClass);	
		return record;//dynamoDBMapper.load(Classes.class, outcome);
	}

	private Table dbconfig() {
		DynamoDBConfig DynamoDBConfig = new com.config.DynamoDBConfig();
		AmazonDynamoDB AdynamoDB = DynamoDBConfig.amazonDynamoDBConfig();
		DynamoDB dynamoDB = new DynamoDB(AdynamoDB);
		Table table = dynamoDB.getTable("Classes");
		return table;
	}
	public void setSectionForClass(String targetClass, String section) {
		Table classesTable = dbconfig();
		Classes clas = getSectionsForClass(targetClass);
		ArrayList<String> prev = clas.getSection();
		 prev.add(section);
		UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("ClassName",targetClass)
				.withUpdateExpression("set Cato = :s")
				.withValueMap(new ValueMap().withStringSet(":s", prev.toArray().toString()))
				  .withReturnValues(ReturnValue.UPDATED_NEW);
		
		try{
			 System.out.println("UPDATING...... ... .. .");
			UpdateItemOutcome outcome = classesTable.updateItem(updateItemSpec);
			 System.out.println("UpdateItem succeeded:\n" + outcome.getItem().toJSONPretty());
			}
			catch(AmazonDynamoDBException e){
			 System.out.println("UpdateItemFailure:\n" + targetClass);
			 System.out.println("EXCEPTION : " + e.getErrorCode());
			 System.out.println("MESSG : "+ e.getErrorMessage());
			 System.out.println(e.getStatusCode());
			}
		
	}

}
