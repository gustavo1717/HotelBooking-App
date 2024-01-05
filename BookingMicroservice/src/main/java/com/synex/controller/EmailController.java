package com.synex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synex.domain.EmailDetails;
import com.synex.service.EmailService;
import com.synex.domain.Booking;


@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetails emailDetails) {
        // Assuming EmailDetails contains the necessary email information
        // You can modify the structure of EmailDetails as per your requirements
        String result = emailService.sendSimpleMail(emailDetails);
        System.out.println(emailDetails.getRecipient());
        return ResponseEntity.ok(result);
    }
}
