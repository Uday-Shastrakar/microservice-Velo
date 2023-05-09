package com.department.service;

import java.util.List;

import com.department.model.Department;

public interface DepartmentService {

	Department createDepartment(Department dept);

	List<Department> getAllDepartments();

	List<Department> getDepartmentByUserId(String userId);

	List<Department> getDepatmentByEmployeeId(String EmployeeId);

	Department getDepartment(String deptId);

	void deleteDepartment(String deptId);

	Department updateDepartment(Department dept);

}
