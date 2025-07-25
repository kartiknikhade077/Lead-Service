/*package com.lead.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Leads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long leadId;
	private int employeeId;
	private int companyId;
	private LocalDateTime leadCreatedDate;
	private LocalDateTime leadUpdateDate;
	private String customerName;
	private String mobileNumber;
	private String phoneNumber;
	private String email;
	private String companyName;
	private String address;
	private String country ;
	private String state;
	private String city;
	private String zipCode;
	public Leads(long leadId, int employeeId, int companyId, LocalDateTime leadCreatedDate, LocalDateTime leadUpdateDate,
			String customerName, String mobileNumber, String phoneNumber, String email, String companyName,
			String address, String country, String state, String city, String zipCode) {
		super();
		this.leadId = leadId;
		this.employeeId = employeeId;
		this.companyId = companyId;
		this.leadCreatedDate = leadCreatedDate;
		this.leadUpdateDate = leadUpdateDate;
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.companyName = companyName;
		this.address = address;
		this.country = country;
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
	}
	public Leads() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getLeadId() {
		return leadId;
	}
	public void setLeadId(long leadId) {
		this.leadId = leadId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public LocalDateTime getLeadCreatedDate() {
		return leadCreatedDate;
	}
	public void setLeadCreatedDate(LocalDateTime leadCreatedDate) {
		this.leadCreatedDate = leadCreatedDate;
	}
	public LocalDateTime getLeadUpdateDate() {
		return leadUpdateDate;
	}
	public void setLeadUpdateDate(LocalDateTime leadUpdateDate) {
		this.leadUpdateDate = leadUpdateDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
*/