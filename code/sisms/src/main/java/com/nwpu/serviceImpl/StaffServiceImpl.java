package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.IStaffDao;
import com.nwpu.model.Office;
import com.nwpu.model.Staff;
import com.nwpu.service.StaffService;

@Service("staffService")
public class StaffServiceImpl implements StaffService {

	@Autowired
	private IStaffDao staffDao;

	public List<Staff> queryStaff() {

		return staffDao.queryStaff();
	}

	public void insertStaff(Staff staff) {

		staffDao.insertStaff(staff);
	}

	public void deleteStaff(String staffID) {
		staffDao.deleteStaff(staffID);

	}

	public Staff queryStaffBystaffID(String staffID) {

		return staffDao.queryStaffBystaffID(staffID);
	}

	public void updateStaff(Staff staff) {
		staffDao.updateStaff(staff);

	}

	public List<Staff> queryStaffBystaffName(String staffName) {

		return staffDao.queryStaffBystaffName(staffName);
	}

	public List<Staff> queryNewStaff() {

		return staffDao.queryNewStaff();
	}

	public List<Office> queryFemaleOffice() {

		return staffDao.queryFemaleOffice();
	}

	public List<Staff> queryNewStaffFemale() {

		return staffDao.queryNewStaffFemale();
	}

	public List<Staff> queryNewStaffMale() {

		return staffDao.queryNewStaffMale();
	}

	public List<Office> queryMaleOffice() {

		return staffDao.queryMaleOffice();
	}

	public void updateOffice(Office office) {

		staffDao.updateOffice(office);
	}

}
