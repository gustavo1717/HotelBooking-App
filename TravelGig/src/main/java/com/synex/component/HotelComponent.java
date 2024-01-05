package com.synex.component;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HotelComponent {

	public JsonNode findHotel (String searchString) {
		
		System.out.println("Inside Rest Client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/searchHotel/"+searchString,Object.class);
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper  = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
		
	}
	
	public JsonNode saveBooking(JsonNode json) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<String>(json.toString(), headers);
		RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Object> responseEntity = restTemplate.postForEntity("http://localhost:8484/saveBooking", request, Object.class);
	    Object objects = responseEntity.getBody();
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
	    return returnObj;
		
	}
	
	public List<JsonNode> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> bookingObj = restTemplate.getForEntity("http://localhost:8484/bookings/findBookings", JsonNode.class);

        System.out.println(bookingObj);

        ObjectMapper mapper = new ObjectMapper();
        List<JsonNode> node = mapper.convertValue(bookingObj.getBody(), List.class);
        return node;
    }
	
	public JsonNode findHotelByName(String searchString) {
	    System.out.println("Inside Rest Client - Get hotel by name");
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/searchHotelByName/" + searchString, Object.class);
	    Object objects = responseEntity.getBody();

	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
	    return returnObj;
	}
	
	public void saveGuest(String firstName, String lastName, String gender, int age) {
	    System.out.println("Inside Rest Client - Save Guest");

	    // Create a JSON string representing the guest information
	    ObjectMapper mapper = new ObjectMapper();
	    JsonNode guestNode = mapper.createObjectNode()
	            .put("firstName", firstName)
	            .put("lastName", lastName)
	            .put("gender", gender)
	            .put("age", age);
	    RestTemplate restTemplate = new RestTemplate();

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);

	    HttpEntity<JsonNode> request = new HttpEntity<>(guestNode, headers);

	    // Adjust the URL to match the backend endpoint
	    ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:8484/guests/saveguest", request, Void.class);
	}
	
	public JsonNode saveReview(JsonNode node) {
        System.out.println(node);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Object> rest = restTemplate.postForEntity("http://localhost:8484/saveReview", node, Object.class);
        Object objects = rest.getBody();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode ret = mapper.convertValue(objects, JsonNode.class);
        return ret;
    }
    public JsonNode findReviews() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> reviewsObj = restTemplate.getForEntity("http://localhost:8484/findReviews", JsonNode.class);


        return reviewsObj.getBody();
    }
    
    public void deleteBooking(long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8484/bookings/deleteBooking?id=" + id);
    }
    
    /*public JsonNode findReviewsByHotelId() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> reviewsObj = restTemplate.getForEntity("http://localhost:8484/findReviewsByHotelId", JsonNode.class);


        return reviewsObj.getBody();
    }*/
	
	 
		
	
}
