package com.department.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.department.exception.ResourceNotFoundException;
import com.department.model.Department;
import com.department.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
	private DepartmentRepository repo;
	
	@Override
	public Department createDepartment(Department dept) {
	 String u	=UUID.randomUUID().toString();
	 dept.setDepartmentId(u);
		return repo.save(dept);
	}

	@Override
	public List<Department> getAllDepartments() {
	
		return repo.findAll();
	}

	@Override
	public Department getDepartment(String deptId) {
		
		return repo.findById(deptId).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
	}

	@Override
	public void deleteDepartment(String deptId) {
	
		repo.deleteById(deptId);
	}

	@Override
	public Department updateDepartment(Department dept) {
		Department dept1 = repo.findById(dept.getDepartmentId()).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
		dept1.setDepartmentName(dept.getDepartmentName());
		dept1.setEmployeeId(dept.getEmployeeId());
		dept1.setUserId(dept.getUserId());
        dept1.setLocation(dept.getLocation());
        
		return repo.save(dept1);
	}

	@Override
	public List<Department> getDepartmentByUserId(String userId) {
		
		return repo.findByUserId(userId);
	}

	@Override
	public List<Department> getDepatmentByEmployeeId(String EmployeeId) {
	
		return repo.findByEmployeeId(EmployeeId);
	}

}
