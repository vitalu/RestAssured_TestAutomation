package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {
	
	private UserCredentials userCredentials;
	@BeforeMethod(description = "Create the payload for login API")
	public void setUp() {
		userCredentials = new UserCredentials("iamfd", "password");
	}
	@Test(description = "Verify if login api is working for FD user", groups= {"api","smoke","regression"})
	public void loginAPITest() throws IOException {
		
		
		given().spec(SpecUtil.requestSpec(userCredentials))
				.when()
				.post("login")
				.then().spec(SpecUtil.responseSpec_OK())
				.and()
				.body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
