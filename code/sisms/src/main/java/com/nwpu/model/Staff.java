package com.nwpu.model;

import java.util.List;

public class Staff {

	private String staffID;
	private String staffName;
	private String sex;
	private String identifyID;
	private String officeNumber;
	private boolean onJob;
	private String projectKind;
	private boolean isAdmin;
	private String leaveDate;
	private String groupID;
	private String address;
	private String email;
	
	private Office office;
	private List<Project> project;
	public String getStaffID() {
		return staffID;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getIdentifyID() {
		return identifyID;
	}
	public void setIdentifyID(String identifyID) {
		this.identifyID = identifyID;
	}
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public boolean isOnJob() {
		return onJob;
	}
	public void setOnJob(boolean onJob) {
		this.onJob = onJob;
	}
	public String getProjectKind() {
		return projectKind;
	}
	public void setProjectKind(String projectKind) {
		this.projectKind = projectKind;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	public List<Project> getProject() {
		return project;
	}
	public void setProject(List<Project> project) {
		this.project = project;
	}
	
	
	
}
