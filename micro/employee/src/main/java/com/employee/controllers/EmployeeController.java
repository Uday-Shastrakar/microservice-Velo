package com.employee.controllers;

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

import com.employee.models.Employee;
import com.employee.services.EmployeeService;




@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	
	 @PostMapping
	public ResponseEntity<Employee> createUser(@RequestBody Employee user){
		Employee users = service.create(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(users);
	}
	
	 @GetMapping("/get/{userId}")
	 public ResponseEntity<Employee> getSingleUser(@PathVariable String userId ){
		 Employee user = service.get(userId);
		 return  ResponseEntity.ok(user);
	 }
	 
	 @GetMapping("/getall")
	 public ResponseEntity<List<Employee>> getAllUser(){
		 List<Employee> allUser =  service.getAll();
		 return ResponseEntity.ok(allUser);
	 }

	 @DeleteMapping("/del")
	 public ResponseEntity<Employee> deleteUser (@RequestParam String userId){
		 service.delete(userId);
		  return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
	 }
	
	
	
	 @PutMapping("/update")
	 public ResponseEntity<Employee> updateUser(@RequestBody Employee user){
		 Employee users = service.update(user);
		   return ResponseEntity.status(HttpStatus.ACCEPTED).body(users);
	 }
	 
}
