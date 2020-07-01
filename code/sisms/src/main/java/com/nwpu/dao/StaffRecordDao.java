package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.StaffRecord;

public interface StaffRecordDao {
	/*
	 * 查询集合
	 */
	List<StaffRecord> getStaffRecordList(String staffID);

	List<StaffRecord> getStaffRecordListAll();

	/*
	 * 根据员工号查询交易记录
	 */
	List<StaffRecord> queryStaffRecordByStaffId(String check);

	/*
	 * 点击缴费后从缴费通知中删除，添加到交易记录中
	 */
	void updateStaffRecordByKey(String key);

	/*
	 * 发布收费信息（Insert）
	 */
	void insertStaffRecord(StaffRecord record);

	/*
	 * 根据员工号查姓名
	 */
	String queryStaffNameByID(String staffID);
}
