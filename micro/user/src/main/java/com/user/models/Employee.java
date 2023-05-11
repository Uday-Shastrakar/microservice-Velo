package com.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	private String id;

	private String name;

	private Integer mobileNumber;

	private String address;

	private String panNo;

	private Integer addharNo;

	private Double salary;
}
