package com.nwpu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nwpu.dao.IOfficeDao;
import com.nwpu.model.Office;
import com.nwpu.model.OfficeApplication;
import com.nwpu.model.Staff;
import com.nwpu.service.OfficeService;

/*
 * OfficeService瀹炵幇绫�
 */
@Service("officeService")
public class OfficeServiceImpl implements OfficeService {

	@Autowired
	IOfficeDao officeDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nwpu.service.OfficeService#insertOffice(com.nwpu.model.Office)
	 */
	public void insertOffice(Office office) {

		officeDao.insertOffice(office);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nwpu.service.OfficeService#selectOffice()
	 */
	public List<Office> selectOffice() {

		return officeDao.selectOffice();
	}

	public List<Staff> queryStaff() {

		return officeDao.queryStaff();
	}

	public void deleteOffice(String officeNumber) {

		officeDao.deleteOffice(officeNumber);
	}

	public List<OfficeApplication> queryApply(String staffID) {

		return officeDao.queryApply(staffID);
	}

	public void insertApply(OfficeApplication officeApplication) {

		officeDao.insertApply(officeApplication);
	}

	public List<Staff> queryStaffById(String staffID) {

		return officeDao.queryStaffById(staffID);
	}

	public Staff getStaffById(String staffID) {
		// TODO Auto-generated method staffb
		return officeDao.getStaffById(staffID);
	}

	public List<Staff> queryStaffByDrom(String officeNumber) {
		// TODO Auto-generated method staffb
		return officeDao.queryStaffByDrom(officeNumber);
	}

	public Office queryOfficeByOfficeNumber(String officeNumber) {

		return officeDao.queryOfficeByOfficeNumber(officeNumber);
	}

	public void updateOffice(Office office) {
		officeDao.updateOffice(office);

	}

	public void updateStaff(Staff staff) {
		officeDao.updateStaff(staff);

	}

	public List<OfficeApplication> queryAllApply() {

		return officeDao.queryAllApply();
	}

	public void updateApply(String applyNumber) {

		officeDao.updateApply(applyNumber);
	}

}
