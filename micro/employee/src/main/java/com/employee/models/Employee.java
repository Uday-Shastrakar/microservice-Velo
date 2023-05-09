package com.employee.models;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {

	@Id
	private String id;

	private String name;

	private Integer mobileNumber;

	private String address;

	private String panNo;

	private Integer addharNo;

	private Double salary;

}
