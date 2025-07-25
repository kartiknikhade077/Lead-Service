package com.lead.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leads") // MongoDB collection name
public class Lead {
	@Id
	private String id;
	private Long companyId;
	private Long employeeId;
	private String assignTo;
	private String status;
	private String source;

//    private String name;
//    private String companyName;
//    private String phoneNumber;
//    private String email;

//    private String assignTo;
//    private String address;
//    private String country;
//    private String state;
//    private String city;
//    private String zipcode;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private Map<String, Object> fields; // Flexible fields


	

	public Lead(String id, Long companyId, Long employeeId, String assignTo, String status, String source,
			LocalDateTime createdDate, LocalDateTime updatedDate, Map<String, Object> fields) {
		super();
		this.id = id;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.assignTo = assignTo;
		this.status = status;
		this.source = source;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.fields = fields;
	}

	public Lead() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields(Map<String, Object> fields) {
		this.fields = fields;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
}