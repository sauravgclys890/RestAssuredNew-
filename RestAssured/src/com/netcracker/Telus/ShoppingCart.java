package com.netcracker.Telus;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.restapi.genricLib.BaseClass;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class ShoppingCart extends BaseClass{
	
	@Test
	public void createShoppingCart() {
		
		extent.createTest("Test Telus Create SC API");
		
		Header h1= new Header("Accept", "*/*");
	    Header h2 = new Header("Accept-Language", "gzip, deflate");
	    Header h3 = new Header("Cache-Control", "no-cache");
	    Header h5 = new Header("Content-Type", "application/json");
	    Header h6 = new Header("Cookie", "JSESSIONID=F5FAE4A0852A220029942DE61705DD0F");
	    Header h7 = new Header("EligibilityParams", "distributionChannelId=\"9144413504413358531\",marketId=\"22222\",customerCategoryId=\"9134661890013196039\"");
	    Header h8 = new Header("Host", "telus1216:8910");
	   
	    
	    List<Header> list = new ArrayList<Header>();
	    list.add(h1);
	    list.add(h2);
	    list.add(h3);
	    list.add(h5);
	    list.add(h6);
	    list.add(h7);
	    list.add(h8);
	    
	    Headers header = new Headers(list);
	    
		RestAssured.baseURI="http://telus1216:8910/api/v1/shoppingCart?";
		ValidatableResponse res=RestAssured.given().headers(header).queryParam("catalogItemIds", "9142278346813160836").
				queryParam("externalAddressId", "9156240824613805290").body("{}").
				when().post().then();
		
		int status= res.extract().statusCode();
				
				System.out.println(status);
				
				//System.out.println(res.extract().asString());
				
				JsonPath jsonPathEvaluator = res.extract().jsonPath();
				
				String productName = jsonPathEvaluator.get("shoppingCart.cartItems[0].name");
				
			    System.out.println(productName);
				
				JSONArray JSONResponseBody = new   JSONArray(res.extract().body().asString());
		
				
	}
	
}