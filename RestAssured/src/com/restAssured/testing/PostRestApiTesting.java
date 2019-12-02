package com.restAssured.testing;


import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PostRestApiTesting {
	
	private static String payload="{\"name\":\"test\",\"salary\":\"456\",\"age\":\"23\"}";
	
	@Test
	public void Posttesting() {
		
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpsRequest= RestAssured.given();
		
		/*JSONObject requestParams = new JSONObject();
		
		requestParams.put("name", "test");
		requestParams.put("salary", "123");
		requestParams.put("age", "123");*/
		
		
		
		httpsRequest.body(payload);
		
		Response res= httpsRequest.post("/create");
		
		System.out.println(res.getStatusCode());
		
		System.out.println(res.asString());
		
		
	}

}
