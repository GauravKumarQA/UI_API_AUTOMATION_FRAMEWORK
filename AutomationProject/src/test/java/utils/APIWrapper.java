package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.*;

public class APIWrapper {

    public Map<String, String> sendGetRequest(String endpoint) {
        RequestSpecification getRequest = RestAssured.given();
        Response getResponse;

        getResponse = getRequest.get(endpoint);

        return createResponseMap(getResponse);
    }
    
    private Map<String, String> createResponseMap(Response response) {
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("response", response.getBody().asString());
        responseMap.put("statusCode", String.valueOf(response.getStatusCode()));

        return responseMap;
    }  
}
