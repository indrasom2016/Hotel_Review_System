package com.indrasom.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.indrasom.user.service.entities.Hotel;
import com.indrasom.user.service.entities.Rating;
import com.indrasom.user.service.entities.User;
import com.indrasom.user.service.exceptions.ResourceNotFoundException;
import com.indrasom.user.service.external.service.HotelService;
import com.indrasom.user.service.respositories.UserRepository;
import com.indrasom.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID not found: "+userId));
		
		Rating[] ratingForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
		logger.info("{}",ratingForUser);
		List<Rating> ratings = Arrays.stream(ratingForUser).toList();
		
		List<Rating> ratingList = ratings.stream().map(rating ->{
		
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			//Hotel hotel = forEntity.getBody();
			//logger.info("response status code {}",forEntity.getStatusCode());
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		
		user.setRatings(ratingList);
		
		return user;
	}

}
