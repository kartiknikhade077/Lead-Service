package com.lead.dto;



public class Employee {
	
	private String employeeId;
	private int userId;
	private String companyId;
	private String Name;
	private String email;
	private String password;
	private String phone;
	private String description;
	private String department;
	private String gender;
	private long departmentId;
	private long roleId;
	private String roleName;
	
	//access module
	
	private boolean leadAccess;
	private boolean templateAccess;
	private boolean emailAccess;
	public Employee(String employeeId, int userId, String companyId, String name, String email, String password, String phone,
			String description, String department, String gender, long departmentId, long roleId, String roleName,
			boolean leadAccess, boolean templateAccess, boolean emailAccess) {
		super();
		this.employeeId = employeeId;
		this.userId = userId;
		this.companyId = companyId;
		Name = name;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.description = description;
		this.department = department;
		this.gender = gender;
		this.departmentId = departmentId;
		this.roleId = roleId;
		this.roleName = roleName;
		this.leadAccess = leadAccess;
		this.templateAccess = templateAccess;
		this.emailAccess = emailAccess;
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
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(long departmentId) {
		this.departmentId = departmentId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isLeadAccess() {
		return leadAccess;
	}
	public void setLeadAccess(boolean leadAccess) {
		this.leadAccess = leadAccess;
	}
	public boolean isTemplateAccess() {
		return templateAccess;
	}
	public void setTemplateAccess(boolean templateAccess) {
		this.templateAccess = templateAccess;
	}
	public boolean isEmailAccess() {
		return emailAccess;
	}
	public void setEmailAccess(boolean emailAccess) {
		this.emailAccess = emailAccess;
	}

	
	
	

}
