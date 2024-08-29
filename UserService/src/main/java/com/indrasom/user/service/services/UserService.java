package com.indrasom.user.service.services;

import java.util.List;

import com.indrasom.user.service.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUser();
	
	User getUser(String userId);
}
