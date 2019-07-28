package com.employeeproject.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeproject.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC001_Get_All_Employee_Data extends TestBase {
	
	
	
	@BeforeClass
	void getAllEmployees() throws InterruptedException {
		
		logger.info( "*******************Starting TC001_Get_All_Employee_Data *************************************");
		RestAssured.baseURI ="http://dummy.restapiexample.com/api/v1";
		
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees");
		
		Thread.sleep(3000);
					
	}
	
	@Test
	void verifyResponse() {
		logger.info( "*********************verify response is not null*******************");
		
		responsebody = response.getBody().asString();
		logger.info("Response body is ==>"+responsebody);
		Assert.assertTrue(responsebody!=null);
			
	}
	
	@Test
	void verifyStatusCode() {
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
		
	}
	
	@Test
	public void verifyResponseTime() {
		
		logger.info( "*********************verify Response Time*******************");
		long responseTime = response.getTime();
		logger.info("Response time is  ==>"+responseTime);
		
		if (responseTime>2000) {
			logger.warn("Response time is greater than 2000");
		}
		Assert.assertTrue(responseTime<10000);
		
	}
	
	@Test
	void verifyContentType() {
		
		logger.info( "*********************verify Header/Content-Type*******************");
		String contentType = response.getHeader("Content-Type");
		logger.info("Content-Type  is  ==>"+contentType);
		
		Assert.assertEquals(contentType, "text/html; charset=UTF-8");
		
	}
	
	@Test
	public void verifyTransferEncoding() {
		
		logger.info( "*********************verify Header/Transfer-Encoding*******************");
		String cONTENTEncoding = response.getHeader("Content-EncodinG");
		logger.info("TransferEncoding   is  ==>"+cONTENTEncoding);
		
		Assert.assertEquals(cONTENTEncoding, "gzip");
		
	}
	
	@Test
	public void verifyServerType() {
		
		logger.info( "*********************verify Header/Server*******************");
		String server = response.getHeader("Server");
		logger.info("Server  is  ==>"+server);
		
		Assert.assertEquals(server, "Apache");
		
	}
	
	@Test
	void verifyContentLength() {
		
		logger.info( "*********************verify Content Length*******************");
		//String ContentLength = response.header("Content-Length");
		String ContentLength= response.getHeader("Content-Length");
		logger.info("Content Length is   ==>"+ContentLength);
		
		//if(Integer.parseInt(ContentLength)<100)
			//logger.warn("Content length is less than ->"+ContentLength);
		
		//Assert.assertTrue(Integer.parseInt(ContentLength)>100);
		
	}
	
	@Test
	void verifyCookies() {
		
		logger.info( "*********************verify Cookies*******************");
		String cookies = response.cookie("PHPSESSID");
		logger.info("Cookies  ==>"+cookies);
		
		
		
	}
	
	@AfterClass
	void teardown() {
		
		logger.info( "********************Finishing TC001*******************");
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

}
