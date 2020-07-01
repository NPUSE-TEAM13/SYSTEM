package com.nwpu.service;

import java.util.List;

import com.nwpu.model.Office;
import com.nwpu.model.Staff;

public interface StaffService {

	List<Staff> queryStaff();

	void insertStaff(Staff staff);

	void deleteStaff(String staffID);

	Staff queryStaffBystaffID(String staffID);

	void updateStaff(Staff staff);

	List<Staff> queryStaffBystaffName(String staffName);

	List<Staff> queryNewStaff();

	List<Office> queryFemaleOffice();

	List<Staff> queryNewStaffFemale();

	List<Staff> queryNewStaffMale();

	List<Office> queryMaleOffice();

	void updateOffice(Office office);
}
