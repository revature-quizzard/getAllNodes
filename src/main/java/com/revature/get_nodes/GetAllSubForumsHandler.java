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
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class GetAllSubForumsHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final Gson mapper = new GsonBuilder().setPrettyPrinting().create();
    private final NodeRepository nodeRepo = new NodeRepository();

    @SneakyThrows
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent requestEvent, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("RECEIVED EVENT: " + requestEvent);

        String subforumId = null;
        if (requestEvent.getPathParameters() != null) {
            subforumId = requestEvent.getPathParameters().get("subforumId");
        }
        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();

        if (subforumId == null || subforumId.trim().equals("")) {
            List<Node> subForums = nodeRepo.getAllSubForums();
            responseEvent.setBody(mapper.toJson(subForums));
        } else {
            List<Node> threads = nodeRepo.getAllThreads(subforumId);
            responseEvent.setBody(mapper.toJson(threads));
        }
        responseEvent.setStatusCode(200);

        Map<String, String> headers = new HashMap<>();
        headers.put("Access-Control-Allow-Headers", "Content-Type,X-Amz-Date,Authorization");
        headers.put("Access-Control-Allow-Origin", "*");
        responseEvent.setHeaders(headers);

        return responseEvent;
    }

}
