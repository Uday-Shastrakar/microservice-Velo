package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.user.models.Department;

@FeignClient(name="DEPARTMENT-SERVICE")
public interface DepartmentService {

	//get
	
	@GetMapping("/department/{departmentId}")
	Department getDepartment(@PathVariable String departmentId);
	
	//Department createDepartment(Map<String, Objects> values);
	//post
	@PostMapping("/department")
	Department createDepartment(Department  values);
	
	//put
	@PutMapping("/department/{departmentId}")
	Department updateDepartment(@PathVariable String departmentId,  Department department);
	
	//delete
	@DeleteMapping("/department/{departmentId}")
	public void deleteDepartment(@PathVariable String departmentId);
}
