package com.indrasom.rating.services;

import java.util.List;

import com.indrasom.rating.entities.Rating;

public interface RatingService {
	
	Rating create(Rating rating);
	
	List<Rating> getRatings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String userId);
}
