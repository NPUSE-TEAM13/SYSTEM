package com.nwpu.dao;

import java.util.List;

import com.nwpu.model.Office;
import com.nwpu.model.Staff;

public interface IStaffDao {

	/*
	 * 查询员工
	 */
	List<Staff> queryStaff();

	/*
	 * 插入员工
	 */
	void insertStaff(Staff staff);

	/*
	 * 删除员工
	 */
	void deleteStaff(String staffID);

	/*
	 * 查询特定员工 by staffID
	 */
	Staff queryStaffBystaffID(String staffID);

	/*
	 * 更新员工信息
	 */
	void updateStaff(Staff staff);

	List<Staff> queryStaffBystaffName(String staffName);

	List<Staff> queryNewStaff();

	List<Office> queryFemaleOffice();

	List<Staff> queryNewStaffFemale();

	List<Staff> queryNewStaffMale();

	List<Office> queryMaleOffice();

	/*
	 * 状态更新
	 */
	void updateOffice(Office office);
}
