package com.revature.repos;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.revature.models.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodeRepository {

    private final DynamoDBMapper dbReader;

    public NodeRepository(){ dbReader = new DynamoDBMapper(AmazonDynamoDBClientBuilder.defaultClient());}

    public List<Node> getAllSubforums() {
        Map<String, AttributeValue> queryInputs = new HashMap<>();
        queryInputs.put(":ancestors", new AttributeValue().withS(null));

        DynamoDBQueryExpression query = new DynamoDBQueryExpression()
                .withFilterExpression("ancestors = :ancestors")
                .withExpressionAttributeValues(queryInputs);

        return dbReader.query(Node.class,query);

    }

}
