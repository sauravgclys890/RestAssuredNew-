package com.restAssured.testing;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.restapi.JavaBean.Employee;
import com.restapi.genricLib.BaseClass;
import com.restapi.genricLib.FileLib;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.internal.util.IOUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;



public class SimpleGetTest extends BaseClass{
	
	
	FileLib flib= new FileLib();
	
	@Test(enabled = false)
	public void testSimpleGetRestAPI() {
		
		extent.createTest("Simple GET Rest API");
		
		RequestSpecification httpsRequest= RestAssured.given();
		
		Response res=httpsRequest.request(Method.GET, "/employees");
		
		logger.log(Status.INFO, "Recevied response");
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
		
			logger.log(Status.PASS, "testSimpleGetRestAPI");
	}
	
	
	@Test(enabled = false)
	public void testSplunkAPI() throws Exception{
		
		RestAssured.baseURI="https://splunk-ss-b.t-mobile.com:8089/services/search/jobs/export?";
		
		
		ValidatableResponse response= RestAssured.given().relaxedHTTPSValidation().auth().basic("Authorization", flib.getPropertiesData("Authorization")).param("search", "search index=genesis sourcetype=genesis_body_header_txt AuditEvent_Event=GENESIS_TRANSACTION 0a07881a-test-4a06-8333-1c3ae7315894 | sort AuditKeys_STARTTIME AuditKeys_ENDTIME AuditKeys_TRANSACTIONID AuditEvent_ApplicationId AuditEvent_Event AuditKeys_PROCESSNAME AuditKeys_STATUS AuditKeys_MSISDN AuditKeys_PARTNERID AuditKeys_ERRORCODE AuditKeys_ERRORMESSAGE AuditKeys_TIMEDURATION event_id").param("output_mode", "raw").param("decod", "urldecode(_raw)").when().get().then();
        
        System.out.println(response.toString());
        
        System.out.println(response.statusCode(200));
		
        
	}
	
	@Test(enabled = false)
	public void getWheatherInfo() throws Exception {
		
		extent.createTest("Test Get API with param");
		
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		
		Reporter.log(flib.getExcelTestData("RestAPI", 0, 0), true);
		
		Reporter.log(flib.getExcelTestData("RestAPI", 0, 1), true);
		
		 ValidatableResponse response=RestAssured.given().param("q", flib.getExcelTestData("RestAPI", 0, 0)).param("appid", flib.getExcelTestData("RestAPI", 0, 1))
		.when().get().then();
		 
		 Reporter.log("Response is= "+response.extract().asString(), true);
		 
		 
		 int statuscode=response.extract().statusCode();
		 SoftAssert softAssertion= new SoftAssert();
		 softAssertion.assertEquals(statuscode, 200);
		 Reporter.log("Verified the status code successfully", true);
		 
		 String contentType= response.extract().contentType();
		 Reporter.log("Contect Type is = "+contentType, true);
		 softAssertion.assertEquals(contentType, "application/json; charset=utf-8");
		 Reporter.log("Content type verified", true);
		 
		 String countryCode= response.extract().response().path("sys.country");
		 softAssertion.assertEquals(countryCode, "IN");
		 Reporter.log("Country code verifed successfully", true);
		 
		 String countryName= response.extract().response().path("name");
		 softAssertion.assertEquals(countryName, "Delhi");
		 Reporter.log("Country code verifed successfully", true);
		 
		 softAssertion.assertAll();
		
		
	}
	
	@Test(enabled = false)
	public void getWheatherInfoOfBangaloreWithValidData() {
		
		extent.createTest("Test Get API with Valid data");
		
		RestAssured.baseURI="http://api.openweathermap.org/data/2.5/weather";
		
		String response=RestAssured.given().param("q", "Bangalore").param("appid", "17e5c69afcef0f16365a6c3b0cba4400")
		.when().get().then().extract().asString();
		
		System.out.println("Response is :- " + response);

		Reporter.log("Response is: " + response, true);
		
		ValidatableResponse res=RestAssured.given().param("q", "Bangalore").param("appid", "17e5c69afcef0f16365a6c3b0cba4400")
		.when().get().then();
		
	    res.statusCode(200);
	    Reporter.log("Verified Success code Successfully", true);
	    
	    res.contentType(ContentType.JSON);
	    
	    String countryName=res.extract().response().path("sys.country");
	    Reporter.log("Country name is verified= "+countryName, true);
	    
	    JsonPath path= new JsonPath(response);
	    System.out.println("Country Name= "+path.get("sys.country"));
	    
	}
	
	@Test(enabled = false)
	public void getWheatherInfoBangaloreWithInvalidData() {
		
		extent.createTest("Test Get API with InValid data");
		
		 ValidatableResponse res=RestAssured.given().param("q", "Bangaloree")
				.param("appid", "17e5c69afcef0f16365a6c3b0cba4400").when()
				.get().then();
		 
		 Reporter.log("Response for invalid data= "+ res.extract().asString(), true);
		 
		 res.statusCode(404);
		 Reporter.log("Status code is verified for Invalida data", true);
		 
		 int status= res.extract().statusCode();
		 Assert.assertEquals(status, 404);
		 Reporter.log("Status code is verified with invalid data", true);
		 
		 res.contentType(ContentType.JSON);
		 Reporter.log("Content is verifed ", true);
		 
	}
	
	@Test(enabled = false)
    public void PostRequestTestUsingString() {
		
		extent.createTest("Test Post API with Payload");
 
        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
 
        String requestBody = "{\n" +
            "  \"name\": \"Saurav\",\n" +
            "  \"salary\": \"5000\",\n" +
            "  \"age\": \"20\"\n" +
            "}";
        
        Response response = null;
 
        try {
        	
            response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/create");
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("Response :" + response.asString());
        System.out.println("Status Code :" + response.getStatusCode());
        System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));
 
 
    
    }
	
	@Test(enabled = false)
	public void PostRequestTestUsingJSON(){
		
		extent.createTest("Test Post API with JASONObject");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Saurav"); 
		requestParams.put("salary", "5000");
		requestParams.put("age", 20);
		
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		ResponseBody<?> response = null;
		 
        try {
        	response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString()).when()
                .post("/create").thenReturn().body();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("Response :" + response.asString());
        System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));
		
	
	}
	
	@Test(enabled = false)
	public void PostRequestTestUsingJSONwithLog(){
		
		extent.createTest("Test POst API with Log");
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Saurav"); 
		requestParams.put("salary", "5000");
		requestParams.put("age", 20);
		
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		ValidatableResponse response = null;
		 
        try {
        	response= RestAssured.given()
                .contentType(ContentType.JSON)
                .body(requestParams.toString()).when()
                .post("/create").then().statusCode(200).log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        System.out.println("Response with Log :" + response.toString());
       
	}
	
	@Test(enabled = false)
	public void PostRequestTestUsingBufferReader() throws Exception{
		
		extent.createTest("Test POst API with BufferReader");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		String fileName=System.getProperty("user.dir")+File.separator+"RestRequest"+File.separator+"Request.json";
		
		System.out.println(fileName);
		File f = new File(fileName);
		  @SuppressWarnings("resource")
		BufferedReader buf = new BufferedReader(new FileReader(f));
		  String line = null;

		  Response response = null;
		  StringBuffer request = new StringBuffer();
		  
		   while ((line = buf.readLine()) != null) {
		    System.out.println("json file line " + line); 
		    request.append(line);
		   }
		   
		   System.out.println(request.toString());
		   try {
	            response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(request.toString())
	                .post("/create");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		   System.out.println("Response :" + response.asString());
	        System.out.println("Status Code :" + response.getStatusCode());
	        System.out.println("Does Reponse contains 'tammy'? :" + response.asString().contains("tammy"));
	}
	
	
	@Test(enabled = false)
	public void PostRequestTestUsingPOJO(){
		
		extent.createTest("Test POst API with Pogo");
	
		logger = extent.createTest("PostRequestTestUsingPOJO", "PASSED test case");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		List<Employee> list=eObj.getEmployeeObject();
		System.out.println(list);
		
		for(Employee emp: list){
			
			JSONObject requestParams = new JSONObject();
			requestParams.put("name", emp.getName()); 
			requestParams.put("salary", emp.getSalary());
			requestParams.put("age", emp.getAge());
			
			Response response = null;
	        try {
	            response = RestAssured.given()
	                .contentType(ContentType.JSON)
	                .body(requestParams.toString())
	                .post("/create");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	 
	        System.out.println("Status Code :" + response.getStatusCode());
	        System.out.println("Does Reponse contains"+ emp.getName()+" ? :" + response.asString().contains(emp.getName()));
		}
		
	}
	
	@Test(enabled = false)
	public void testSOAPService() throws Exception{
		
		extent.createTest("Test SOAP Service");
		
		FileInputStream fileInputStream = new FileInputStream(new File(".\\SOAPRequest\\SoapRequestFile.xml"));
         RestAssured.baseURI="http://currencyconverter.kowabunga.net";

         Response response=RestAssured.given()
                .header("Content-Type", "text/xml")
                .and()
                .body(IOUtils.toByteArray(fileInputStream))
         .when()
            .post("/converter.asmx")
         .then()
                .statusCode(200)
                .and()
                .log().all()
                .extract().response();

        XmlPath jsXpath= new XmlPath(response.asString());//Converting string into xml path to assert
        String rate=jsXpath.getString("GetConversionRateResult");
        System.out.println("rate returned is: " +  rate);

		

	}
	
	
	
}
