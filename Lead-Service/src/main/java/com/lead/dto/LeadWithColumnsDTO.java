package com.lead.dto;

import java.util.List;
import java.util.Map;

import com.lead.entity.LeadColumn;

public class LeadWithColumnsDTO {
	private List<LeadColumn.ColumnDefinition> columns;
	private Map<String, Object> lead;
	private String status;
	private String source;
	private long employeeId;
	private String assignTo;

	public List<LeadColumn.ColumnDefinition> getColumns() {
		return columns;
	}

	public void setColumns(List<LeadColumn.ColumnDefinition> columns) {
		this.columns = columns;
	}

	public Map<String, Object> getLead() {
		return lead;
	}

	public void setLead(Map<String, Object> lead) {
		this.lead = lead;
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

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}
	
	
}
