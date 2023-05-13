package com.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.models.User;
import com.user.service.UserService;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {
	 @Autowired
		private UserService  service;
		 
		 private Logger logger = LoggerFactory.getLogger(UserController.class);
		 
		 @PostMapping
		public ResponseEntity<User> createUser(@RequestBody User user){
			User users = service.createUser(user);

			return ResponseEntity.status(HttpStatus.CREATED).body(users);
		}
		 
		//get single user
		 int retryCount=1;
		 
		 @GetMapping("/{userId}")
//		 @CircuitBreaker(name = "departmentEmployeeBreaker",fallbackMethod = "departmentEmployeeFallback")
//		@Retry(name = "departmentEmployeeService",fallbackMethod  = "departmentEmployeeFallback")
		@RateLimiter(name = "userRateLimiter", fallbackMethod = "departmentEmployeeFallback")
		 public ResponseEntity<User> getSingleUser(@PathVariable String userId ){
			 logger.info("{}",retryCount);
			 retryCount ++;
			 User user = service.getUser(userId);
			 return  ResponseEntity.ok(user);
		 }
		 
		 //creating fallback method
		 
		 public ResponseEntity<User> departmentEmployeeFallback(String userId,Exception ex ){
		//	 logger.info("Fallback is executed",ex.getMessage());
		
			User user = User.builder()
			 .email("demo.gmail.com")
			 .name("demo")
			 .about("this is created because some services is down")
			 .userid("12232")
			 .build();
			 return new ResponseEntity<User>(user, HttpStatus.OK);
		 }
		 
		 
		 @GetMapping
		 public ResponseEntity<List<User>> getAllUser(){
			 List<User> allUser =  service.getAllUsers();
			 return ResponseEntity.ok(allUser);
		 }

		 @DeleteMapping("/del")
		 public ResponseEntity<User> deleteUser (@RequestParam String userId){
			 service.deleteUser(userId);
			  return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
		 }
		 
		 @PutMapping("/update")
		 public ResponseEntity<User> updateUser(@RequestBody User user){
			   User users = service.updateUser(user);
			   return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
		 }
		 
	 
	 
}
