package com.api.tests;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.utils.AuthTokenProvider;
import static com.api.utils.ConfigManager.*;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPITest() throws IOException {
		given()
		.baseUri(getProperty("BASE_URI"))
		.contentType(ContentType.JSON)
		.header("Authorization",AuthTokenProvider.getToken(Role.FD))
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1500L))
		.body("message", equalTo("Success"))
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", not(blankOrNullString()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema.json"));
		
	}
	
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given()
		.baseUri(getProperty("BASE_URI"))
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}
}
