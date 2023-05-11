package com.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.models.Employee;

@FeignClient(name ="EMPLOYEE-SERVICE")
public interface EmployeeService {
	
	@GetMapping("/employee/{employeeId}")
	Employee getEmployee(@PathVariable String employeeId);

}
