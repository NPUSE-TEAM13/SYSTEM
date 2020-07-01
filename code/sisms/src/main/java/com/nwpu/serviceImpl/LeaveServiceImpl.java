package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.IleaveDao;
import com.nwpu.model.Staff;
import com.nwpu.model.StaffRecord;
import com.nwpu.service.LeaveService;

@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private IleaveDao leaveDao;

	public List<Staff> queryStaff(String leaveDate) {

		return leaveDao.queryStaff(leaveDate);
	}

	public List<Staff> queryBorrow(String leaveDate) {

		return leaveDao.queryBorrow(leaveDate);
	}

	public Staff queryStaffByID(String staffID) {

		return leaveDao.queryStaffByID(staffID);
	}

	public Staff queryBorrowByID(String staffID) {

		return leaveDao.queryBorrowByID(staffID);
	}

	public List<StaffRecord> queryRecordByID(String staffID) {

		return leaveDao.queryRecordByID(staffID);
	}

	public List<Staff> queryStaffBystaffName(String staffName, String leaveDate) {

		return leaveDao.queryStaffBystaffName(staffName, leaveDate);
	}

	public List<Staff> queryBorrowStaffBystaffName(String staffName, String leaveDate) {

		return leaveDao.queryBorrowStaffBystaffName(staffName, leaveDate);
	}
}
