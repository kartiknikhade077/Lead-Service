package com.lead.dto;




public class ModuleAccess {
	

	private String moduleAccessId;
	private String companyId;
	private String employeeId;
	private boolean leadAccess;
	private boolean template;
	private boolean email;
	private boolean customerViewAll;
	private boolean customerOwnView;
	private boolean customerCreate;
	private boolean customerDelete;
	private boolean customerEdit;
	private boolean projectViewAll;
	private boolean projectOwnView;
	private boolean projectCreate;
	private boolean projectDelete;
	private boolean projectEdit;
	private boolean timeSheetAccess;
	private boolean timeSheetViewAll;
	private boolean timeSheetCreate;
	private boolean timeSheetDelete;
	private boolean timeSheetEdit;
	private boolean leadModuleAccess;
	private boolean leadViewAll;
	private boolean leadCreate;
	private boolean leadDelete;
	private boolean leadEdit;
	
	public ModuleAccess(String moduleAccessId, String companyId, String employeeId, boolean lead, boolean template,
			boolean email) {
		super();
		this.moduleAccessId = moduleAccessId;
		this.companyId = companyId;
		this.employeeId = employeeId;
		this.leadAccess = lead;
		this.template = template;
		this.email = email;
	}
	
	

	public ModuleAccess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getModuleAccessId() {
		return moduleAccessId;
	}



	public void setModuleAccessId(String moduleAccessId) {
		this.moduleAccessId = moduleAccessId;
	}



	public String getCompanyId() {
		return companyId;
	}


	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}


	public String getEmployeeId() {
		return employeeId;
	}



	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}



	public boolean isLeadAccess() {
		return leadAccess;
	}


	public void setLeadAccess(boolean leadAccess) {
		this.leadAccess = leadAccess;
	}



	public boolean isTemplate() {
		return template;
	}

	public void setTemplate(boolean template) {
		this.template = template;
	}

	public boolean isEmail() {
		return email;
	}

	public void setEmail(boolean email) {
		this.email = email;
	}


	public boolean isCustomerCreate() {
		return customerCreate;
	}



	public void setCustomerCreate(boolean customerCreate) {
		this.customerCreate = customerCreate;
	}



	public boolean isCustomerDelete() {
		return customerDelete;
	}



	public void setCustomerDelete(boolean customerDelete) {
		this.customerDelete = customerDelete;
	}



	public boolean isCustomerEdit() {
		return customerEdit;
	}



	public void setCustomerEdit(boolean customerEdit) {
		this.customerEdit = customerEdit;
	}



	public boolean isCustomerViewAll() {
		return customerViewAll;
	}



	public void setCustomerViewAll(boolean customerViewAll) {
		this.customerViewAll = customerViewAll;
	}



	public boolean isCustomerOwnView() {
		return customerOwnView;
	}



	public void setCustomerOwnView(boolean customerOwnView) {
		this.customerOwnView = customerOwnView;
	}



	public boolean isProjectViewAll() {
		return projectViewAll;
	}



	public void setProjectViewAll(boolean projectViewAll) {
		this.projectViewAll = projectViewAll;
	}



	public boolean isProjectOwnView() {
		return projectOwnView;
	}



	public void setProjectOwnView(boolean projectOwnView) {
		this.projectOwnView = projectOwnView;
	}



	public boolean isProjectCreate() {
		return projectCreate;
	}



	public void setProjectCreate(boolean projectCreate) {
		this.projectCreate = projectCreate;
	}



	public boolean isProjectDelete() {
		return projectDelete;
	}



	public void setProjectDelete(boolean projectDelete) {
		this.projectDelete = projectDelete;
	}



	public boolean isProjectEdit() {
		return projectEdit;
	}



	public void setProjectEdit(boolean projectEdit) {
		this.projectEdit = projectEdit;
	}



	public boolean isTimeSheetAccess() {
		return timeSheetAccess;
	}



	public void setTimeSheetAccess(boolean timeSheetAccess) {
		this.timeSheetAccess = timeSheetAccess;
	}



	public boolean isTimeSheetViewAll() {
		return timeSheetViewAll;
	}



	public void setTimeSheetViewAll(boolean timeSheetViewAll) {
		this.timeSheetViewAll = timeSheetViewAll;
	}



	public boolean isTimeSheetCreate() {
		return timeSheetCreate;
	}

	public void setTimeSheetCreate(boolean timeSheetCreate) {
		this.timeSheetCreate = timeSheetCreate;
	}

	public boolean isTimeSheetDelete() {
		return timeSheetDelete;
	}

	public void setTimeSheetDelete(boolean timeSheetDelete) {
		this.timeSheetDelete = timeSheetDelete;
	}

	public boolean isTimeSheetEdit() {
		return timeSheetEdit;
	}

	public void setTimeSheetEdit(boolean timeSheetEdit) {
		this.timeSheetEdit = timeSheetEdit;
	}

	public boolean isLeadModuleAccess() {
		return leadModuleAccess;
	}

	public void setLeadModuleAccess(boolean leadModuleAccess) {
		this.leadModuleAccess = leadModuleAccess;
	}

	public boolean isLeadViewAll() {
		return leadViewAll;
	}

	public void setLeadViewAll(boolean leadViewAll) {
		this.leadViewAll = leadViewAll;
	}

	public boolean isLeadCreate() {
		return leadCreate;
	}

	public void setLeadCreate(boolean leadCreate) {
		this.leadCreate = leadCreate;
	}

	public boolean isLeadDelete() {
		return leadDelete;
	}

	public void setLeadDelete(boolean leadDelete) {
		this.leadDelete = leadDelete;
	}

	public boolean isLeadEdit() {
		return leadEdit;
	}

	public void setLeadEdit(boolean leadEdit) {
		this.leadEdit = leadEdit;
	}
	
	
	
	
	
	
	

}
