package com.revature.repos;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.revature.models.Node;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<Node> getSubforumById(String id) {

        Map<String, AttributeValue> queryInputs = new HashMap<>();
        queryInputs.put(":id", new AttributeValue().withS(id));

        DynamoDBScanExpression query = new DynamoDBScanExpression()
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(queryInputs);

        List<Node> queryResult = dbReader.scan(Node.class, query);

        return queryResult.stream().findFirst();

    }

    public List<Node> getAllThreads(String id) throws RuntimeException, ValidationException {
        if(!getSubforumById(id).isPresent())
            throw new ValidationException("Subforum ID does not exist");

        Map<String, AttributeValue> queryInputs = new HashMap<>();
        queryInputs.put(":id", new AttributeValue().withS(id));

        DynamoDBQueryExpression query = new DynamoDBQueryExpression()
                .withFilterExpression("id = :id")
                .withExpressionAttributeValues(queryInputs);

        return dbReader.query(Node.class, query);
    }

}
