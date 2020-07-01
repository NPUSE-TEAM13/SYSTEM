package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.Office;
import com.nwpu.model.OfficeApplication;
import com.nwpu.model.Staff;

public interface IOfficeDao {

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
