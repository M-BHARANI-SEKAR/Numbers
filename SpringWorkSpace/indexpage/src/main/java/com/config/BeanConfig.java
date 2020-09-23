package com.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.CRUD.ClassesRepository;
import com.CRUD.dynamocrud.DynamicKeyCrud;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;

@Configuration
public class BeanConfig {
	
	ApplicationContext ctx = new AnnotationConfigApplicationContext(DynamoDBConfig.class);
	
//	@Autowired
	DynamoDBMapper dynamoDBMapper = ctx.getBean(DynamoDBMapper.class);
	
	@Bean
	public ClassesRepository classesRepository() {
		return new ClassesRepository(dynamoDBMapper);
		}
	
	@Bean
	public DynamicKeyCrud dkrep() {
		return new DynamicKeyCrud(dynamoDBMapper, dbconfig("DYNAMIC_KEY"));
	}

	private Table dbconfig(String tableName) {
		DynamoDBConfig DynamoDBConfig = new com.config.DynamoDBConfig();
		AmazonDynamoDB AdynamoDB = DynamoDBConfig.amazonDynamoDBConfig();
		DynamoDB dynamoDB = new DynamoDB(AdynamoDB);
		Table table = dynamoDB.getTable(tableName);
		return table;
	}
}
