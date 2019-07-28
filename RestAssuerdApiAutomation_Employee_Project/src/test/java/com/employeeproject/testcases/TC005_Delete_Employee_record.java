package com.employeeproject.testcases;

import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeproject.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delete_Employee_record extends TestBase{
	
	
	
	@BeforeClass
	public void deleteteEmployeeRecord() throws InterruptedException {
		
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
		
		
		Thread.sleep(2000);
		
		
		//Delete the employee record
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JsonPath JsonPathEvaluator = response.jsonPath();
		empID = JsonPathEvaluator.get("[0].id");
		response = httpRequest.request(Method.DELETE, "/delete/"+empID);
		
		Thread.sleep(3000);
		
	
	}
	
	@Test
	public void validateResponseBody() {
		
		responsebody = response.getBody().asString();
		logger.info("Response body is ==>"+responsebody);
		Assert.assertEquals(responsebody.contains("successfully! deleted Records"), true);
		
		
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
		logger.info("****************************Finishing up TC005_Delete_Employee_record*****************");
			
	
	}
	
	
	

}
