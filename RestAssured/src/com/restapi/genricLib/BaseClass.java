package com.restapi.genricLib;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ConfigurableReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.restapi.JavaBean.Employee;

import io.restassured.RestAssured;

public class BaseClass {
	
	public ExtentReports extent;
	public ExtentTest logger;
	public Employee eObj;  
	ExtentHtmlReporter reporter;

	@Parameters({ "API", "Jar"})
	@BeforeTest
	public void setupDataandReport(String api, String jar){
		
       reporter=new ExtentHtmlReporter("./Reports/RestAPIAutomation_SOAPANDRESTTest.html");
		
	    extent = new ExtentReports();
	    
	    extent.setSystemInfo("API", api);
        extent.setSystemInfo("Jar", jar);
        
        reporter.config().setAutoCreateRelativePathMedia(true);
        reporter.config().setDocumentTitle("Extent Report Demo");
        reporter.config().setReportName("Test Report");
        reporter.config().setTheme(Theme.STANDARD);
        
	    extent.attachReporter(reporter);
	    
	    logger=extent.createTest("Setup data Test");
		
	    
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee("",500, 20));
		list.add(new Employee("Chandru",500, 20));
		list.add(new Employee("Mohan",500, 20));
		list.add(new Employee("Mohanga",500, 20));
	 eObj= new Employee();
	 
	 
		
	 eObj.setEmployeeObject(list);
	 
	 logger.log(Status.INFO, "Test data set up is done");
	 
	 logger.log(Status.PASS, "Before Test executed");
		
	}
	
	@BeforeMethod
	public void setupBaseURI(){
		
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
		logger.log(Status.INFO, "Base URI set is done");
		
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) {
		
        if(result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            logger.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" PASSED ", ExtentColor.GREEN));
        }
        else {
            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            logger.skip(result.getThrowable());
        }
    }
	
	@AfterTest
    public void tearDown() {
    
        extent.flush();
    }

}
