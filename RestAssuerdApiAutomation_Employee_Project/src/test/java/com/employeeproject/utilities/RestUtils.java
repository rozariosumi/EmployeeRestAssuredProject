package com.employeeproject.utilities;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeClass;

import com.employeeproject.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class RestUtils extends TestBase {

	public static String employeeName() {
		String generateNameString = RandomStringUtils.randomAlphabetic(2);
		
		return ("Alex"+generateNameString);

	}
	
	public static String empSalary() {
		String  generatedSalaryString = RandomStringUtils.randomNumeric(5);
		return generatedSalaryString;
			
	}
	
	public static String employeeAge() {
		String generatedAgeString = RandomStringUtils.randomNumeric(2);
		return generatedAgeString;
		
		
		
	}
	
	
	

	
	
	
}
