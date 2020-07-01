package com.nwpu.service;

import java.util.List;

import com.nwpu.model.StaffRecord;

public interface StaffRecordService {
	public List<StaffRecord> getStaffRecordList(String staffID);

	public List<StaffRecord> getStaffRecordListAll();

	public List<StaffRecord> getStaffRecordByStaffId(String check);

	public String toString();

	public void updateStaffRecordByKey(String key);

	public void insertStaffRecord(StaffRecord record);

	String queryStaffNameByID(String staffID);
}
