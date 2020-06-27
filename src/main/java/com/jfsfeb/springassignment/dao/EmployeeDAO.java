package com.jfsfeb.springassignment.dao;

import java.util.List;

public interface EmployeeDAO {

	public EmployeeInfoBean getEmployeeByid(int Id);
	public boolean addEmployee(EmployeeInfoBean bean);
	public boolean updateEmployee(EmployeeInfoBean bean);
	public boolean deleteEmployee(int Id);
	public List<EmployeeInfoBean> getAllEmployees();
	public EmployeeInfoBean authenticate(int empId,String password);
}
