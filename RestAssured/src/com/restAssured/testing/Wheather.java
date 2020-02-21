package com.restAssured.testing;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class Wheather {
	
	@Test
	public void testWheatherAPI() {
		
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather?";
		
		ValidatableResponse res=RestAssured.given().param("q", "Pune").param("appid", "17e5c69afcef0f16365a6c3b0cba4400").when().get().then();
		
		System.out.println(res.extract().asString());
		
		System.out.println(res.extract().statusCode());
		
		 String countryName=res.extract().response().path("sys.country");
		 
		 System.out.println(res.contentType(ContentType.XML));
		 
		 System.out.println(countryName);
		
	}

}
