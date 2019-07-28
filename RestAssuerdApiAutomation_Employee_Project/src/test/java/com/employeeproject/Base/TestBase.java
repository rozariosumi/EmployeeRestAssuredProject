package com.employeeproject.Base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import com.employeeproject.utilities.RestUtils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	
	public static RequestSpecification httpRequest;
	
	public static Response response;
	public static String responsebody;
	public String empName = RestUtils.employeeName();
	public String empSal =RestUtils.empSalary();
	public String empAge =RestUtils.employeeAge();
	public String salary ="2000";
	
	public static String empID;
	
	
	
	public Logger logger;
	
	@BeforeClass
	public void setUP() {
		logger = Logger.getLogger("EmployeeAPI");
		PropertyConfigurator.configure("Log4j.properties");
		logger.setLevel(Level.DEBUG);
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
