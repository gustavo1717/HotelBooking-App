package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.synex.domain.Guest;
import com.synex.service.GuestService;

import java.util.List;


@RestController
@RequestMapping("/guests")
public class GuestController {

    @Autowired
    private GuestService guestService;

    @RequestMapping(value = "/saveguest", method = RequestMethod.POST)
    public Guest saveGuest(@RequestBody Guest guest) {
        return guestService.saveGuest(guest);
    }

    /*@GetMapping
    public List<Guest> getAllGuests() {
        return guestService.getAllGuests();
    }*/

}