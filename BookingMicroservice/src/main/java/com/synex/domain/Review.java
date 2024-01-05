package com.synex.domain;


import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="review")
public class Review {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int reviewId;
		
		private String text;
		private double overallRating;
		
		private int hotelId;

		public int getHotelId() {
			return hotelId;
		}

		public void setHotelId(int hotelId) {
			this.hotelId = hotelId;
		}

		public Review() {}

		public int getReviewId() {
			return reviewId;
		}

		public void setReviewId(int reviewId) {
			this.reviewId = reviewId;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public double getOverallRating() {
			return overallRating;
		}

		public void setOverallRating(double overallRating) {
			this.overallRating = overallRating;
		}
		
		
}
