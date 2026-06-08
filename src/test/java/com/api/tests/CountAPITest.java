package com.api.tests;


import static com.api.constants.Role.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import java.io.IOException;

import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

public class CountAPITest {

	@Test
	public void verifyCountAPITest() throws IOException {
		given()
		.spec(SpecUtil.requestSpecWithAuthToken(FD))		
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", not(blankOrNullString()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema.json"));
		
	}
	
	@Test
	public void countAPITest_MissingAuthToken() throws IOException {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));
	}
}
