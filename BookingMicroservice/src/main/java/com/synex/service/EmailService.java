package com.synex.service;

import com.synex.domain.Booking;
import com.synex.domain.EmailDetails;


public interface EmailService {
	String sendSimpleMail(EmailDetails emailDetails);
	
	
}
