package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static com.api.utils.SpecUtil.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test(description = "Verify if the master api is giving correct response", groups = {"api","smoke","regression"})
	public void masterAPITest() throws IOException {
		given()
		.spec(requestSpecWithAuthToken(FD))
		.when()
		.post("master")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body(matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));
		
	}
	@Test(description = "Verify if the master api is giving correct status code for invalid token", groups = {"api","smoke","regression","negative"})
	public void invalidTokenMasterAPITest() throws IOException {
		given()
		.spec(requestSpec())
		.when()
		.post("master")
		.then()
		.spec(responseSpec_TEXT(401));

	}

}
