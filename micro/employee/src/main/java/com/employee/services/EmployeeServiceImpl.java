package com.employee.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.exceptions.ResourceNotFoundException;
import com.employee.models.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
  @Autowired
	private EmployeeRepository repo;
	
	@Override
	public Employee create(Employee employee) {
	String EmployeeId =	UUID.randomUUID().toString();
	  employee.setId(EmployeeId);
	
		return repo.save(employee);
	}

	@Override
	public List<Employee> getAll() {
		
		return repo.findAll();
	}

	@Override
	public Employee get(String id) {
		
		return  repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
	}
	@Override
	public void delete(String id) {
		
		repo.deleteById(id);
	}

	@Override
	public Employee update(Employee employee) {
		Employee emp =  repo.findById(employee.getId()).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
		
		emp.setName(employee.getName());
		emp.setMobileNumber(employee.getMobileNumber());
		emp.setAddharNo(employee.getAddharNo());
		emp.setAddress(employee.getAddress());
		emp.setPanNo(employee.getPanNo());
		emp.setSalary(employee.getSalary());
		
		
		return repo.save(emp);
	}

}
