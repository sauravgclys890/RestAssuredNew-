package com.restAssured.testing;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestWithoutTestNG {

	public static void main(String[] args) {
		
        RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpsRequest= RestAssured.given();
		
		Response res=httpsRequest.request(Method.GET, "/employees");
		
		String resbody= res.getBody().asString();
		
		System.out.println(res.asString());
		
		System.out.println(res.getStatusCode());
		
		Assert.assertEquals(res.getStatusCode(), 200, "correct response code return");
		
		String contenttype= res.header("Content-Type");
		
		
		
		Headers allheader= res.headers();
		
		for (Header header: allheader) {
			
			System.out.println("Key: "+ header.getName()+ "Value: "+ header.getValue());
		}
		
		JsonPath jsoanpathevalutor= res.jsonPath();
		
		System.out.println("Id recived from response= "+jsoanpathevalutor.get("employee_name"));

	}

}
