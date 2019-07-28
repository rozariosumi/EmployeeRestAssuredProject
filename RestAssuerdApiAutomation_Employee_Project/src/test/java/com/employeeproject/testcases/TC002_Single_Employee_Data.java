package com.employeeproject.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeproject.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC002_Single_Employee_Data extends TestBase{
	
	
	@BeforeClass
	public void getSingleEmployee() throws InterruptedException {
		
		logger.info( "*******************Starting TC002_Single_Employee_Data *************************************");
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);
		
		JsonPath JsonPathEvaluator = response.jsonPath();
		
		String empID = JsonPathEvaluator.get("[1].id");
		
		logger.info( "*******************Getting single emloyee ID from response  *************************************");
		logger.info("Employee id is ==>"+empID);
		
		
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employee"+"/"+empID);

		//return empID;
	}
	
	
	@Test
	public void verifyResponsebody() {
		logger.info( "*******************verify response body  *************************************");
		responsebody =response.getBody().asString();
		logger.info("Response body is ==>"+responsebody);
		Assert.assertTrue(responsebody!=null);
		//Assert.assertEquals(responsebody.contains(this.empID), true);
	}

	@Test
	public void verifyStatusCode() {
		logger.info( "*********************verify Status Code*******************");
		
		int statuscode = response.getStatusCode();
		logger.info("Status code is ==>"+statuscode);
		Assert.assertEquals(statuscode, 200);
		
	}
	
	
	@Test
	public void verifyStatusLine() {
		
		logger.info( "*********************verify Status Line*******************");
		String statusline = response.getStatusLine();
		logger.info("Status Line is ==>"+statusline);
		Assert.assertEquals(statusline,"HTTP/1.1 200 OK");
		
	}
	@Test
	public void verifyResponseTime() {
		
		logger.info( "*********************verify Response Time*******************");
		long responseTime = response.getTime();
		logger.info("Response time is  ==>"+responseTime);
		System.out.println(responseTime);
		
		if (responseTime>2000) {
			logger.warn("Response time is greater than 2000");
		}
		Assert.assertTrue(responseTime<10000);
		
	}
	
	@Test
	public void verifyContentType() {
		
		logger.info( "*********************verify Header/Content-Type*******************");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content-Type  is  ==>"+contentType);
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	public void verifyTransferEncoding() {
		
		logger.info( "*********************verify Header/Transfer-Encoding*******************");
		String ContentEncoding = response.getHeader("Content-Encoding");
		logger.info("Content-Type  is  ==>"+ContentEncoding);
		
		Assert.assertEquals(ContentEncoding, "gzip");
		
	}
	
	@Test
	public void verifyServerType() {
		
		logger.info( "*********************verify Header/Server*******************");
		String server = response.getHeader("Server");
		logger.info("Server  is  ==>"+server);
		
		Assert.assertEquals(server, "Apache");
		
	}
	
	@Test
	public void verifyContentLength() {
		
		logger.info( "*********************verify Content Length*******************");
		String ContentLength = response.header("Content-Length");
		logger.info("Content Length is  ==>"+ContentLength);
		if(Integer.parseInt(ContentLength)<90)
			
			logger.warn("Content Length is less than ->"+ContentLength);
		
		Assert.assertTrue(Integer.parseInt(ContentLength)<100);
		
		
		
	}
	
	@AfterClass
	public void teardown() {
		logger.info( "*********************Finishing TC002******************");
		
		
	}
	

	
	
	
	
	
	
	
	

}
