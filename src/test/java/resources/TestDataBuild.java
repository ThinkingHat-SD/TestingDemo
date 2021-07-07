package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.DeletePlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {

		AddPlace addPlace = new AddPlace();
		Location lt = new Location();
		List<String> Types = new ArrayList<>() ;
		
		//Adding Request details
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress(address);
		
		Types.add("shoe park");
		Types.add("shop");
		addPlace.setTypes(Types);
		
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(language);
		
		lt.setLat(-38.383494);
		lt.setLng(33.427362);
		addPlace.setLocation(lt);
		
		return addPlace;

	}
	public DeletePlace deletePlacePayload(String placeid)
	{
		DeletePlace delPlace = new DeletePlace();
		delPlace.setPlace_id(placeid);
		return delPlace;
	}
}
