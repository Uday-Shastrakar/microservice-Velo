package com.user.service;

import java.util.List;

import com.user.models.User;

public interface UserService {

	User createUser(User user);
	
	List<User> getAllUsers();
	
	User getUser (String userId);
	
	void deleteUser( String userId);
	
	User updateUser(User user);
	
	
	
}
