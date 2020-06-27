package com.jfsfeb.springassignment.dao;

public class EmployeeDAOJdbcImple implements EmployeeDAO {

	public EmployeeInfoBean getEmployeeByid(int empId) {
		EmployeeInfoBean employeeInfoBean = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/emp_db?useSSL=false&user=root&password=root");
			pstmt = connection.prepareStatement("select * from ems_db where id=?");
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				employeeInfoBean = new EmployeeInfoBean();
				employeeInfoBean.setId(rs.getInt("id"));
				employeeInfoBean.setAge(rs.getInt("age"));
				employeeInfoBean.setName(rs.getString("name"));
				employeeInfoBean.setMobilenumber(Long.parseLong(rs.getString("mobilenumber")));
				employeeInfoBean.setPassword(rs.getString("password"));
				employeeInfoBean.setEmailId("emailId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return employeeInfoBean;
	}


	public boolean updateEmployee(EmployeeInfoBean bean) {
		String query = "update ems_db set name=? where id=?";
		String url = "jdbc:mysql://localhost:3307/emp_db?useSSL=false&user=root&password=root";
		try (Connection connection = DriverManager.getConnection(url);
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			Class.forName("com.mysql.jdbc.Driver");
			preparedStatement.setString(1, bean.getName());
			preparedStatement.setInt(2, bean.getId());
			int res = preparedStatement.executeUpdate();
			if (res != 0) {
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteEmployee(int empId) {

		boolean isDeleted = false;

		try {

			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/emp_db?useSSL=false&user=root&password=root");
			PreparedStatement pstmt1 = connection.prepareStatement("delete from  ems_db  where id=?");

			pstmt1.setInt(1, empId);

			int result = pstmt1.executeUpdate();
			if (result > 0) {
				
				isDeleted = true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return isDeleted;
	}

	@Override
	public boolean addEmployee(EmployeeInfoBean bean) {

		String query1 = "insert into ems_db values(?,?,?,?,?,?)";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/emp_db?useSSL=false&user=root&password=root");

			PreparedStatement preparedStatement3 = connection.prepareStatement(query1);

			preparedStatement3.setInt(1, bean.getId());
			preparedStatement3.setString(2, bean.getName());
			preparedStatement3.setLong(3, bean.getMobilenumber());
			preparedStatement3.setString(4, bean.getEmailId());
			preparedStatement3.setString(5, bean.getPassword());
			preparedStatement3.setInt(6, bean.getAge());

			int result = preparedStatement3.executeUpdate();

			if (result != 0) {
				
				return true;
			}

			connection.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}

	

	@Override
	public List<EmployeeInfoBean> getAllEmployees() {
		List<EmployeeInfoBean> listInfo = new ArrayList<EmployeeInfoBean>();

		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/emp_db?useSSL=false&user=root&password=root");
			PreparedStatement pstmt1 = connection.prepareStatement("select * from ems_db");
			rs = pstmt1.executeQuery();

			while (rs.next()) {
				EmployeeInfoBean bean2 = new EmployeeInfoBean();

				
				 bean2.setId(rs.getInt("id"));
				 bean2.setAge(rs.getInt("age"));
				 bean2.setName(rs.getString("name"));
				 bean2.setMobilenumber(Long.parseLong(rs.getString("mobilenumber")));
				 bean2.setPassword(rs.getString("password"));
				 bean2.setEmailId("emailId");

				listInfo.add(bean2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		return listInfo;
	}

	@Override
	public EmployeeInfoBean authenticate(int empId, String password) {
		EmployeeInfoBean employeeInfoBean = getEmployeeByid(empId);
		if (!(employeeInfoBean != null && employeeInfoBean.getPassword().equals(password))) {
			employeeInfoBean = null;
		}
		return null;
	}
}
