package com.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.department.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, String>{

	//custom methods
	
	List<Department> findByUserId(String userId);
	
	List<Department> findByEmployeeId(String EmployeeId);
	
	
}
