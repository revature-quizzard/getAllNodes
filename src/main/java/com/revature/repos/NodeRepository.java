package com.revature.repos;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.revature.models.Node;

import java.util.*;

public class NodeRepository {

    private final DynamoDBMapper dbReader;

    public NodeRepository(){ dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());}

    public List<Node> getAllSubForums() {
//        Map<String, AttributeValue> queryInputs = new HashMap<>();
//        queryInputs.put(":parent", new AttributeValue().withS(""));
//
//        DynamoDBScanExpression query = new DynamoDBScanExpression()
//                .withFilterExpression("parent = :parent")
//                .withExpressionAttributeValues(queryInputs);
//
//        return dbReader.scan(Node.class,query);
        return dbReader.scan(Node.class, new DynamoDBScanExpression());
    }

}
