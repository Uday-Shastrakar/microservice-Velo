package com.user.service;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.exceptions.ResourceNotFoundException;
import com.user.models.Department;
import com.user.models.Employee;
import com.user.models.User;
import com.user.repositories.UserRepository;
import com.user.service.external.services.DepartmentService;
import com.user.service.external.services.EmployeeService;




@Service
public class UserServiceImpl implements UserService{
@Autowired
private UserRepository repo;
@Autowired
private RestTemplate restTemplate;

@Autowired
private EmployeeService empService;
@Autowired
private DepartmentService deptService;

private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Override
	public User createUser(User user) {
		
		//this code will generate unique userId
 String randomUserId =	UUID.randomUUID().toString();
  
  user.setUserid(randomUserId);
		return repo.save(user);
	}

	@Override
	public List<User> getAllUsers() {
	// implementing department service using rest template
		return repo.findAll();
	}

	@Override
	public User getUser(String userId) {
		// get user from database with help of userRepository
		User user =repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
		//fetch  department from department service
	//	http://localhost:8083/department/users/985f599f-66cd-4a83-b6bd-4ea43a43639b
	Department[] departmentOfUser =restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/users/"+ user.getUserid(), Department[].class);
		
		logger.info("{}",departmentOfUser);
		
//	List<Department> departments= Arrays.stream(departmentOfUser).toArray();
//		
	List<Department> departmentList  =Arrays.stream(departmentOfUser).map(department -> {
			//api call to employee service to get the employee
		//http://localhost:8082/employee/6cbbcdc7-786a-4979-b3a0-c8824f5353f4
	//useing rest template
		//ResponseEntity<Employee> forEntity=	restTemplate.getForEntity("http://EMPLOYEE-SERVICE/employee/"+department.getEmployeeId(), Employee.class);
//	Employee employee = forEntity.getBody();
	// using Feign client	
		
		Employee employee = empService.getEmployee(department.getEmployeeId());
	
//	    logger.info("response status code ",forEntity.getStatusCode());
			//set the employee to department
	    department.setEmployee(employee);
			//return the department
			return department;
		}).collect(Collectors.toList());
		
		
		
		user.setDepartment(departmentList);
	
		return user;
	}

	@Override
	public void deleteUser(String userId) {
		
		  repo.deleteById(userId);
	}

	@Override
	public User updateUser(User user) {
	User user1 =	repo.findById(user.getUserid()).orElseThrow(() -> new ResourceNotFoundException("resource not found on Server!!"));
	user1.setName(user.getName());
	user1.setEmail(user.getEmail());
	user1.setAbout(user.getAbout());
	
	
		return repo.save(user1);
	}

}
