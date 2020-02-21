package com.restAssured.testing;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.restapi.genricLib.BaseClass;
import com.restapi.genricLib.FileLib;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class Training extends BaseClass{
	
	@Test
	public void testAPI() throws Exception {
		
		logger=extent.createTest("Rest API Testing using rest Assured");
		
		FileLib flib= new FileLib();
		
        RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		
		ValidatableResponse res=RestAssured.given().param("q", flib.getExcelTestData("RestAPI", 0, 0)).param("appid", "17e5c69afcef0f16365a6c3b0cba4400")
		.when().get().then();
		
	    res.statusCode(200);
	    
	    logger.log(Status.PASS, "Status code successfully verified");
	    
	    System.out.println(res.extract().asString());
	    
	    System.out.println(res.extract().statusCode());
	    Reporter.log("Verified Success code Successfully", true);
	    
	    res.contentType(ContentType.JSON);
	    
	    String countryName=res.extract().response().path("sys.country");
	    Reporter.log("Country name is verified= "+countryName, true);
	    
	    JsonPath path= new JsonPath(res.extract().asString());
	    System.out.println("Country Name= "+path.get("sys.country"));
	    
	    String name= res.extract().response().path("name");
	    System.out.println(name);
	    
	    logger.log(Status.INFO, "name is ="+ name, MediaEntityBuilder.createScreenCaptureFromPath("screen.png").build());
	}
	
	

}
