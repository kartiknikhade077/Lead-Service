package com.lead.dto;



public class Employee {
	
	private String employeeId;
	private int userId;
	private String companyId;
	private String name;
	private String email;
	private String phone;
	private String description;
	private String department;
	private String gender;
	private String departmentId;
	private String roleId;
	private String roleName;
	public Employee(String employeeId, int userId, String companyId, String name, String email, String phone,
			String description, String department, String gender, String departmentId, String roleId, String roleName) {
		super();
		this.employeeId = employeeId;
		this.userId = userId;
		this.companyId = companyId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.description = description;
		this.department = department;
		this.gender = gender;
		this.departmentId = departmentId;
		this.roleId = roleId;
		this.roleName = roleName;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
	

}
