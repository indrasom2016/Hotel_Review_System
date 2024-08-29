package com.indrasom.hotel.services;

import java.util.List;

import com.indrasom.hotel.entities.Hotel;

public interface HotelService {
	
	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);

}
