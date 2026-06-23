package com.api.tests.datadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.model.UserCredentials;
import com.api.utils.SpecUtil;
import com.dataproviders.api.bean.UserPojo;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPIDataDrivenTest {
	

	@Test(description = "Verify if login api is working for FD user", 
			groups= {"api","datadriven","regression"},
			dataProviderClass = com.dataproviders.DataProviderUtils.class,
			dataProvider = "LoginAPIDataProvider"
			)
	public void loginAPITest(UserPojo userPojo) throws IOException {
		
		
		given().spec(SpecUtil.requestSpec(userPojo))
				.when()
				.post("login")
				.then().spec(SpecUtil.responseSpec_OK())
				.and()
				.body(matchesJsonSchemaInClasspath("response-schema/loginResponseSchema.json"));

	}

}
