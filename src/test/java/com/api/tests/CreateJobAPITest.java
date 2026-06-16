package com.api.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import static com.api.constants.Product.*;

import static com.api.constants.Modal.*;

import static com.api.constants.Problem.*;

import static com.api.constants.OEM.*;
import static com.api.constants.Platform.*;
import static com.api.constants.ServiceLocation.*;
import static com.api.constants.Warranty_Status.*;
import com.api.request.model.CreateJobPayload;
import com.api.request.model.Customer;
import com.api.request.model.CustomerAddress;
import com.api.request.model.CustomerProduct;
import com.api.request.model.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import static com.api.utils.DateAndTime.*;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateJobAPITest {
	private CreateJobPayload createJobPayload;
	
	@BeforeMethod(description = "Create job api request payload")
	public void setUp() {
		Customer customer = new Customer("Gulshan", "Kumar", "8999895655", "8987895666", "gulu@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("453", "Aduri heights", "KSR Road", "", "Bharat nagar", "566755", "TS", "");
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(7), "16830344641311", "11830934641312", "19081114641351", getTimeWithDaysAgo(7), NEXUS_2.getCode(), NEXUS_BLUE_2.getCode());
		Problems problems = new Problems(OVERHEATING.getCode(), "Heating issue");
		List<Problems> problemList = new ArrayList<Problems>();
		problemList.add(problems);
		createJobPayload = new CreateJobPayload(SERVICE_LOCATION_A.getCode(),
				FRONT_DESK.getCode(), IN_WARRANTY.getCode(), GOOGLE.getCode(), customer,
				customerAddress, customerProduct, problemList);
	}

	@Test(description = "Verify if the create api is able to create Inwarranty job", groups = {"api","smoke","regression"})
	public void createJobAPITest() throws IOException {
		
		given().spec(SpecUtil.requestSpecWithAuthToken(FD, createJobPayload))
				.when()
				.post("/job/create")
				.then()
				.spec(SpecUtil.responseSpec_OK())
				//.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/createJobAPIResponseSchema.json"))
				.body("message",Matchers.equalTo("Job created successfully. "))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}
}
