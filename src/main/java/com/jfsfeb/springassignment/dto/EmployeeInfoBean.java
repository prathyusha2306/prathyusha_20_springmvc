package com.jfsfeb.springassignment.dto;

import java.io.Serializable;

import lombok.Data;
@Data
public class EmployeeInfoBean implements Serializable{

	private int id;
	private String name;
	private long mobilenumber;
	private String emailId;
	private String password;
	private int age;
}
