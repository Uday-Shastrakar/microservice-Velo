package com.user.controller;

import java.util.List;

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

@RestController
@RequestMapping("/users")
public class UserController {
	 @Autowired
	private UserService  service;
	 
	 @PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User users = service.createUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}
	 @GetMapping("/{userId}")
	 public ResponseEntity<User> getSingleUser(@PathVariable String userId ){
		 User user = service.getUser(userId);
		 return  ResponseEntity.ok(user);
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
