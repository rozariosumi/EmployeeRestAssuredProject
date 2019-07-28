package com.employeeproject.testcases;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeproject.Base.TestBase;
import com.employeeproject.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class TC004_Update_Employee_record extends TestBase {
	

	public static String empSalary_update;
	
	@BeforeClass
	public void updateEmployeeRecord() throws InterruptedException {
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		
		
		//set parameter with JSONObject
		
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSal);
		requestParams.put("age", empAge);
		
		
		//Add header
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString());
		
		//POST request
		response = httpRequest.request(Method.POST,"/create");
		
		
		Thread.sleep(7000);
		JsonPath JsonPathEvaluator = response.jsonPath();
		
		String empID = JsonPathEvaluator.get("[0].id");
		empSalary_update =empSal+"10";
	
			
			//update employee record
			RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
			
			httpRequest = RestAssured.given();
			JSONObject requestparams = new JSONObject();
			requestParams.put("name", empName); 
			requestparams.put("salary",empSalary_update);
			requestParams.put("age", empAge);
			
			logger.info("Employee name is ==>"+empName);
			
			logger.info("Employee age is ==>"+empAge);
		
			
			httpRequest.header("Content-Type","application/json");
			
			httpRequest.body(requestparams.toJSONString());
			
			//PUT request
			logger.info("********************Fetching all the employee records*****************************");
			response = httpRequest.request(Method.PUT,"/update"+"/"+empID);
			
			Thread.sleep(3000);
		
	}
	
	@Test
	public void validateResponseBody() {
		
		responsebody = response.getBody().asString();

		logger.info("Employee updated Salary is ==>"+empSalary_update);				
		Assert.assertEquals(responsebody.contains(empSalary_update), true);
		Assert.assertSame(empName, empName);
		
	}
	
	@Test
	public void verifyStatusCode()
	{
		logger.info("*************************verifying Status code*********************");
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>"+statusCode);
		Assert.assertEquals(statusCode, 200);
	}
	
	
	@Test
	public void verifyStatusLine() {
		logger.info("**************************verifying Status line*************************");
		String Statusline = response.getStatusLine();
		logger.info("Status line is ==>"+Statusline);
		Assert.assertEquals(Statusline,"HTTP/1.1 200 OK");
	}
	
	@Test
	public void verifyContentType() {
		
		logger.info("**************************verifying header/Content type***************************");
		String contentType = response.header("Content-Type");
		logger.info("Server Type is ==> "+contentType);
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	@Test
	public void verifyContentLength() {
		
		logger.info("**************************verifying header/content length***************************");
		String contentlength = response.header("Content-Length");
		logger.info("Content length is ==> "+contentlength);
		if(Integer.parseInt(contentlength)<60)
			logger.warn("Content length is less than 60");
		
		Assert.assertTrue(Integer.parseInt(contentlength)<100);
		
	}
	@Test
	public void verifyServerType() {
		
		logger.info("**************************verifying header/server type*********************************");
		String serverType = response.header("Server");
		logger.info("Server Type is ==> "+serverType);
		Assert.assertEquals(serverType, "Apache");
		
		
	}
	
	@AfterClass
	public void teardown() {
		logger.info("****************************Finishing up TC004_Update_Employee_record*****************");
		
	
	
	
	}
	
	
	
	
	
	

}
