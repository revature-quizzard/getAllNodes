package com.revature.get_nodes;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.revature.models.Node;
import com.revature.repos.NodeRepository;

import java.util.List;

public class GetAllSubForumsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final NodeRepository nodeRepo = new NodeRepository();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("RECEIVED EVENT: " + requestEvent);

        List<Node> subForums = nodeRepo.getAllSubForums();
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody(mapper.toJson(subForums));

        responseEvent.setStatusCode(200);
        return responseEvent;
    }

}
