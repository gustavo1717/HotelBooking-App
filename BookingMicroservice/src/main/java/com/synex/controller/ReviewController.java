package com.synex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synex.domain.Review;
import com.synex.service.ReviewService;

@CrossOrigin(origins = "http://localhost:8282")
@RestController
public class ReviewController {

	@Autowired ReviewService reviewService;
	
@PostMapping("/saveReview")
    public JsonNode saveReview(@RequestBody JsonNode node) {
        ObjectMapper mapper = new ObjectMapper();
        var rev = mapper.convertValue(node, Review.class);
        var review = reviewService.saveReview(rev);
        var jsonNode = mapper.convertValue(review, JsonNode.class);
        return jsonNode;

    }
    @PostMapping("/findReviews")
    public JsonNode findReviews() {
        List<Review> reviews = reviewService.findReviews();
        ObjectMapper mapper = new ObjectMapper();
        var rev = mapper.convertValue(reviews, JsonNode.class);
        return rev;

    }
    
    @GetMapping("/findReviewsByHotelId/{hotelId}")
    public List<Review> findReviewsByHotelId(@PathVariable int hotelId) {
        return reviewService.findReviewsByHotelId(hotelId);
    }
}
