package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.StaffRecordDao;
import com.nwpu.model.StaffRecord;
import com.nwpu.service.StaffRecordService;

@Service
public class StaffRecordServiceImpl implements StaffRecordService {

	@Autowired
	private StaffRecordDao staffRecordDao;// 员工DAO接口

	/**
	 * 列出员工交易记录
	 */
	public List<StaffRecord> getStaffRecordList(String staffID) {
		// System.out.println("serviceimpl执行");
		// System.out.println(staffRecordDao.getStaffRecordList().get(0).getStaffID());
		return staffRecordDao.getStaffRecordList(staffID);
	}

	/*
	 * 根据员工号查询员工交易记录
	 */
	public List<StaffRecord> getStaffRecordByStaffId(String check) {
		return staffRecordDao.queryStaffRecordByStaffId(check);
	}

	/*
	 * 更新员工交易记录
	 */
	public void updateStaffRecordByKey(String key) {
		staffRecordDao.updateStaffRecordByKey(key);
	}

	/*
	 * 发布收费信息
	 */
	public void insertStaffRecord(StaffRecord record) {
		staffRecordDao.insertStaffRecord(record);
	}

	public String queryStaffNameByID(String staffID) {

		return staffRecordDao.queryStaffNameByID(staffID);
	}

	public List<StaffRecord> getStaffRecordListAll() {
		// TODO Auto-generated method staffb
		return staffRecordDao.getStaffRecordListAll();
	}

}
