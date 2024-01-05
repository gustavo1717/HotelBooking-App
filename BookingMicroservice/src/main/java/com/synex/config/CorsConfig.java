package com.synex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/bookings/saveBooking")
                        .allowedOrigins("http://localhost:8282")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowCredentials(true);
                
                registry.addMapping("/guests/saveguest")
                .allowedOrigins("http://localhost:8282")
                .allowedMethods("POST")
                .allowCredentials(true);
                
                registry.addMapping("/findReviewsByHotelId/{hotelId}")  // Adjust the mapping to match your endpoint
                .allowedOrigins("http://localhost:8282")
                .allowedMethods("GET")  // Assuming you're only using GET for fetching reviews
                .allowCredentials(true);
            }
        };
    }
}
