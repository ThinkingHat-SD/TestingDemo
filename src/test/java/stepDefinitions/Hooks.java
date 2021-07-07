package stepDefinitions;

import java.io.IOException;
import io.cucumber.java.Before;


public class Hooks {

	
	
	@Before("@DeletePlaceTest")
	public void preConDeleteAPITest() throws IOException
		{
		
			StepDefinition sdef = new StepDefinition();
			
			if(StepDefinition.place_id==null)
			{
			sdef.add_place_payload_with("NewPurchase", "Marathi", "Near London Bridge");
			sdef.user_calls_api_using_http_request("AddPlaceResource", "POST");
			sdef.verify_that_place_id_created_maps_to_using("NewPurchase", "GetPlaceResource");
			}
		}
}
