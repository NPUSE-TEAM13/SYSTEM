package com.nwpu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nwpu.model.Office;
import com.nwpu.model.OfficeApplication;
import com.nwpu.model.Staff;

public interface OfficeService {

	void insertOffice(Office office);

	List<Office> selectOffice();

	List<Staff> queryStaff();

	void deleteOffice(String officeNumber);

	List<OfficeApplication> queryApply(String staffID);

	void insertApply(OfficeApplication officeApplication);

	List<Staff> queryStaffById(String staffID);

	Staff getStaffById(String staffID);

	List<Staff> queryStaffByDrom(String officeNumber);

	Office queryOfficeByOfficeNumber(String officeNumber);

	void updateOffice(Office office);

	void updateStaff(Staff staff);

	List<OfficeApplication> queryAllApply();

	void updateApply(String applyNumber);
}
