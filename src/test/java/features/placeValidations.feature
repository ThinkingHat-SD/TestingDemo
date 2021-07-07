Feature: Place APIs Validations

@AddPlaceTest
Scenario Outline: Verify if place is added sucessfully using AddPlace Api
Given Add Place Payload with "<name>" "<language>" "<address>"
When User calls "AddPlaceResource" API using "POST" http request
Then The API call is success with status code is 200
And "status" in response body is "OK" 
And "scope" in response body is "APP"
And verify that place_id created maps to "<name>" using "GetPlaceResource"

Examples:
   |name       |language   |address                   |
   |SnehVilla  |English-en |A1, Beautiful Town, Europe|
  |SnehMansion|English-en |A100, Bhurj Khalifa, Dubai|

@DeletePlaceTest
Scenario: Verify if DeletePlace functionality works correctly
Given Delete Place Payload
When User calls "DeletePlaceResource" API using "POST" http request
Then The API call is success with status code is 200
And "status" in response body is "OK" 