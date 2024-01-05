package com.synex.repository;

import com.synex.domain.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelAmenityRepository extends JpaRepository<Amenities, Long> {
    // You can define custom queries here if needed
}
