package com.synex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synex.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	List<Review> findByHotelId(int hotelId);
}
