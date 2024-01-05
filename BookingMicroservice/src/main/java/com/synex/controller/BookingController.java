package com.synex.controller;

import com.synex.domain.Booking;
import com.synex.domain.EmailDetails;
import com.synex.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8282")
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable int bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
    
    @PostMapping("/saveBooking")
    public Booking createBooking(@RequestBody Booking booking) {
    	var ret = bookingService.saveBooking(booking);
    	RestTemplate restTemplate = new RestTemplate();
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("gustahurtado10@gmail.com");
        emailDetails.setSubject("Confirmation for booking at " + booking.getHotelName());
        emailDetails.setMsgBody("Thank you " + booking.getUserName() + ", you just made your reservation!\n" +
        "Confirmation details:\n"+ "Hotel: " + booking.getHotelName() + "\nDate: " + booking.getCheckInDate() + "\n"
        +"Room Type: " + booking.getRoomType() + "\nPrice: " + booking.getPrice());
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8484/email/send", emailDetails, String.class);
         return ret;
    }
    
    @RequestMapping(value="/findBookings", method=RequestMethod.GET)
    public List<Booking> findBookings(){
        return bookingService.findBookings();
    }
    
    @DeleteMapping("/deleteBooking")
    public void deleteBooking(@RequestParam int id) {
        var booking = bookingService.getBookingById(id);
        if(booking != null) {
            booking.setStatus("CANCELLED");
            bookingService.saveBooking(booking);
        }
    }

}
