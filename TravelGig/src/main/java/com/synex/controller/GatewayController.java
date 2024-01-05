package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.HotelComponent;

@RestController
public class GatewayController {
	
	@Autowired HotelComponent hotelComponent;
	
	
	@RequestMapping(value = "/findHotel/{searchString}", method = RequestMethod.GET)
	public JsonNode findHotel(@PathVariable String searchString) {
		return hotelComponent.findHotel(searchString);
	}
	
	/*@RequestMapping(value = "/roomTypes", method = RequestMethod.GET)
	public JsonNode getAllRoomTypes() {
	    return hotelComponent.getAllRoomTypes();
	}*/
	
	@RequestMapping(value = "/findHotelByName/{searchString}", method = RequestMethod.GET)
	public JsonNode findHotelByName(@PathVariable String searchString) {
		return hotelComponent.findHotelByName(searchString);
	}
	
	@RequestMapping(value = "/saveBooking", method= RequestMethod.POST)
	public JsonNode saveBooking(@RequestBody JsonNode json) {
		return hotelComponent.saveBooking(json);
	}
	
	@PostMapping(value = "/saveguest")
    public void saveGuest(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String gender,
            @RequestParam int age) {
        hotelComponent.saveGuest(firstName, lastName, gender, age);
    }
	
	@PostMapping("/saveReview/")
    public JsonNode saveReview(@RequestBody JsonNode node) {
        System.out.println("test");
        return hotelComponent.saveReview(node);
    }
    
	@PostMapping("/findReviews/")
    public JsonNode findReviews() {

        return hotelComponent.findReviews();
    }
	

}
