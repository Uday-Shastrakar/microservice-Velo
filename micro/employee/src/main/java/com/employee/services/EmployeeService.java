package com.employee.services;

import java.util.List;

import com.employee.models.Employee;

public interface EmployeeService {
	 
	
	Employee create(Employee employee);
	

	List<Employee> getAll();
	
	
	Employee get(String id);
	
	void delete(String id);
	
	Employee update(Employee employee);
}
