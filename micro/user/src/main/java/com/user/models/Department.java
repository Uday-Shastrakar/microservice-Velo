package com.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Department {
 private String departmentId;

 private String userId;
  
 private String EmployeeId;
 
 private String departmentName;
 
 private String location;
 
 private Employee employee;
}
