package com.synex.controller;

import com.synex.domain.Amenities;
import com.synex.service.HotelAmenityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel-amenities")
public class HotelAmenityController {

    private final HotelAmenityService hotelAmenityService;

    @Autowired
    public HotelAmenityController(HotelAmenityService hotelAmenityService) {
        this.hotelAmenityService = hotelAmenityService;
    }

    // Endpoint to retrieve hotel amenities by hotelId
    @GetMapping("/ame/{hotelId}")
    public List<Amenities> getHotelAmenities(@PathVariable int hotelId) {
        return hotelAmenityService.getHotelAmenities(hotelId);
    }
}
