package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;

public class StepDefinition extends Utilities {
	
	ResponseSpecification addPlaceRes;
	RequestSpecification req;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
						
						//Creating Request 		
						req =given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
	}
	
	@When("User calls {string} API using {string} http request")
	public void user_calls_api_using_http_request(String APIResource, String methodType)
	{
		APIResources resourcesObj = APIResources.valueOf(APIResource);
		System.out.println("The API being executed is : "+resourcesObj.toString());
		addPlaceRes = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).
				expectHeader("Server", "Apache/2.4.18 (Ubuntu)").expectBody("scope", equalTo("APP")).
				build();
		
		
		//Hitting the request and Extracting Response
		if(methodType.equalsIgnoreCase("POST"))
			response = req.when().post(resourcesObj.getResource());
		else if(methodType.equalsIgnoreCase("GET"))
			response = req.when().get(resourcesObj.getResource());
	}
	
	@Then("The API call is success with status code is {int}")
	public void the_api_call_is_success_with_status_code_is(Integer statusCode) {
	   assertEquals(response.getStatusCode(),200);
	}
	
	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
	   	   assertEquals(getJsonPath(response, key),value);
	}
	
	@Then("verify that place_id created maps to {string} using {string}")
	public void verify_that_place_id_created_maps_to_using(String expectedName, String GetPlaceResource) throws IOException {
	    String actualName;
	    place_id= getJsonPath(response, "place_id");
	    req =given().spec(requestSpecification()).queryParam("place_id", place_id);
	    user_calls_api_using_http_request(GetPlaceResource,"GET");
	    actualName=getJsonPath(response, "name");
	    
	    assertEquals(expectedName,actualName);
	    
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		//Creating Request 		
		
		req =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}

}
