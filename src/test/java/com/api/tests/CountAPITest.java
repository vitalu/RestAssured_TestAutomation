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

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CountAPITest {

	@Test(description = "Verify if the count api is giving correct response", groups = {"api","smoke","regression"})
	public void verifyCountAPITest() throws IOException {
		given()
		.spec(requestSpecWithAuthToken(FD))		
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", not(blankOrNullString()))
		.body(matchesJsonSchemaInClasspath("response-schema/countAPIResponseSchema.json"));
		
	}
	
	@Test(description = "Verify if the count api is giving correct status code for invalid token", groups = {"api","smoke","regression","negative"})
	public void countAPITest_MissingAuthToken() throws IOException {
		given()
		.spec(requestSpec())
		.when()
		.get("/dashboard/count")
		.then()
		.spec(responseSpec_TEXT(401));
	}
}
