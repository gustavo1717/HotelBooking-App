package com.synex.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.synex.component.HotelComponent;
import com.synex.domain.User;
import com.synex.domain.UserDetailsDto;
import com.synex.service.UserService;

@Controller
public class WelcomeController {
	@Autowired
    private UserService userService;
	
	@Autowired
    private HotelComponent hotelComponent;

	@RequestMapping(value = "/home",method = RequestMethod.GET)
	public String home(Principal principal, Model model) {
		
		model.addAttribute("principalName", principal.getName());
		System.out.println(principal.getName());
		String username = principal.getName();
		User user = userService.findByUserName(username);
		
		model.addAttribute("userEmail", user.getEmail());
		return "Home";
		
	}
	
	@RequestMapping(value = "/Review",method = RequestMethod.GET)
	public String review(Model model) {
		model.addAttribute("bookings", hotelComponent.findAll());
		return "Review";
		
	}
	
	
	@GetMapping("/getUserDetails")
    public ResponseEntity<UserDetailsDto> getUserDetails(Principal principal) {
        String username = principal.getName();
        User user = userService.findByUserName(username);

        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setUsername(username);
        userDetailsDto.setEmail(user.getEmail());

        return ResponseEntity.ok(userDetailsDto);
    }
	
	@GetMapping("/deleteBooking")
    public String deleteBooking(@RequestParam long id, Model model) {
        System.out.println(id);
        hotelComponent.deleteBooking(id);
        List<JsonNode> node = hotelComponent.findAll();

        model.addAttribute("bookings", node);
        return "Review";
    }
	
}
