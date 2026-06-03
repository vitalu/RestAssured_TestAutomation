package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization",getToken(FD))
		.contentType("")
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(200)
		.time(lessThan(1500L))
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));
		
	}
	@Test
	public void invalidTokenMasterAPITest() throws IOException {
		given()
		.baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization", "")
		.contentType("")
		.log().all()
		.when()
		.post("master")
		.then()
		.log().all()
		.statusCode(401);

	}

}
