package com.api.tests;

import org.testng.annotations.Test;

import static com.api.constants.Role.*;

import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.AuthTokenProvider;
import com.api.utils.ConfigManager;
import com.api.utils.SpecUtil;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.IOException;

public class CreateJobAPITest {
	

	@Test
	public void createJobAPITest() throws IOException {
		Customer customer = new Customer("Gulshan", "Kumar", "8999895655", "8987895666", "gulu@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("453", "Aduri heights", "KSR Road", "", "Bharat nagar", "566755", "TS", "");
		CustomerProduct customerProduct = new CustomerProduct("2026-04-06T18:30:00.000Z", "19002134641388", "19007000641351", "19081114641351", "2026-04-06T18:30:00.000Z", 1, 1);
		Problems problems = new Problems(1, "Heating issue");
		Problems[] problemsArray = new Problems[1];
		problemsArray[0]=problems;
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerProduct, problemsArray);
		
		given().spec(SpecUtil.requestSpecWithAuthToken(FD, createJobPayload))
				.when()
				.post("/job/create")
				.then()
				.spec(SpecUtil.responseSpec_OK());
	}
}
