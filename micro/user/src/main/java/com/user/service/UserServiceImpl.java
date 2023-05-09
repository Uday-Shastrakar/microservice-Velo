package com.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.exceptions.ResourceNotFoundException;
import com.user.models.User;
import com.user.repositories.UserRepository;




@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserRepository repo;

@Autowired
private RestTemplate restTemplate;

private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);	
	@Override
	public User createUser(User user) {
		
		//this code will generate unique userId
 String randomUserId =	UUID.randomUUID().toString();
  
  user.setUserid(randomUserId);
		return repo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
	
		return repo.findAll();
	}

	@Override
	public User getUser(String userId) {
		//get user from database from user repository
		User user =repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
		//fetch  department of  the above user from department service
		//http://localhost:8084/department/users/986665b9-5b63-4fbc-beb1-73f7b079945a
	ArrayList forObject =restTemplate.getForObject("http://localhost:8084/department/users/986665b9-5b63-4fbc-beb1-73f7b079945a", ArrayList.class);
		logger.info("{}",forObject);
	return user;
	}

	@Override
	public void deleteUser(String userId) {
		
		  repo.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
	User user1 =	repo.findById(user.getUserid()).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
	user1.setName(user.getName());
	user1.setEmail(user.getEmail());
	user1.setAbout(user.getAbout());
	
	
		return repo.save(user1);
	}

}