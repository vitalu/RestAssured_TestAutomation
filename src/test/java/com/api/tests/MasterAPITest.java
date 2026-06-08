package com.api.tests;

import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.api.utils.SpecUtil;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constants.Role.*;
import static com.api.utils.AuthTokenProvider.*;

import static com.api.utils.ConfigManager.*;

import static io.restassured.RestAssured.*;

public class MasterAPITest {
	
	@Test
	public void masterAPITest() throws IOException {
		given()
		.spec(SpecUtil.requestSpecWithAuthToken(FD))
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/masterAPIResponseSchema.json"));
		
	}
	@Test
	public void invalidTokenMasterAPITest() throws IOException {
		given()
		.spec(SpecUtil.requestSpec())
		.when()
		.post("master")
		.then()
		.spec(SpecUtil.responseSpec_TEXT(401));

	}

}
