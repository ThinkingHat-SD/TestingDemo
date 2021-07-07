package resources;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {
	public static RequestSpecification addPlaceReq;
	
	public RequestSpecification requestSpecification() throws IOException
	{
			if(addPlaceReq==null)
			{
			PrintStream logFile = new PrintStream(new FileOutputStream("logging.txt"));
			
			addPlaceReq = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURL")).
				addFilter(RequestLoggingFilter.logRequestTo(logFile)).
				addFilter(ResponseLoggingFilter.logResponseTo(logFile)).
				addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).
				build();
				return addPlaceReq;
			
			}
			return addPlaceReq;
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties globalProp = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Snehal\\Prep\\Rest Assured\\Projects\\APIRestAssuredFramework\\src\\test\\java\\resources\\globalProperties.properties");
		String value;
		
		globalProp.load(fis);
		value = globalProp.getProperty(key);
		
		return value;
	}
	
	public String getJsonPath(Response response, String key)
	{
		String responseBody = response.asString();
		 JsonPath js = new JsonPath(responseBody);
		 return js.get(key).toString();
	}
}
