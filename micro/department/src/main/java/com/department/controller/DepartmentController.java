package com.department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.department.model.Department;
import com.department.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	@Autowired
	private DepartmentService service;

	@PostMapping
	public ResponseEntity<Department> createUser(@RequestBody Department dept) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.createDepartment(dept));
	}

	@GetMapping
	public ResponseEntity<List<Department>> getDepartments() {
		return ResponseEntity.ok(service.getAllDepartments());
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Department>> getDepartmentByUserId(@PathVariable String userId) {
		return ResponseEntity.ok(service.getDepartmentByUserId(userId));
	}

	@GetMapping("/employees")
	public ResponseEntity<List<Department>> getDepartmentByEmployeeId(@RequestParam String employeeId) {
		return ResponseEntity.ok(service.getDepatmentByEmployeeId(employeeId));
	}
}
