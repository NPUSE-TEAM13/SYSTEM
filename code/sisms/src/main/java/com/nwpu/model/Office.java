package com.nwpu.model;

public class Office {

	private String officeNumber;
	private int officeKind;
	private int tableNumber;
	private boolean isFull;
	private String type;
	public String getOfficeNumber() {
		return officeNumber;
	}
	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}
	public int getOfficeKind() {
		return officeKind;
	}
	public void setOfficeKind(int officeKind) {
		this.officeKind = officeKind;
	}
	public int getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}
	public boolean isFull() {
		return isFull;
	}
	public void setFull(boolean isFull) {
		this.isFull = isFull;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	

	
}
