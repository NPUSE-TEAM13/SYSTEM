package com.nwpu.service;

import java.util.List;

import com.nwpu.model.Staff;
import com.nwpu.model.StaffRecord;

public interface LeaveService {

	List<Staff> queryStaff(String leaveDate);

	List<Staff> queryBorrow(String leaveDate);

	Staff queryStaffByID(String staffID);

	Staff queryBorrowByID(String staffID);

	List<StaffRecord> queryRecordByID(String staffID);

	List<Staff> queryStaffBystaffName(String staffName, String leaveDate);

	List<Staff> queryBorrowStaffBystaffName(String staffName, String leaveDate);
}
