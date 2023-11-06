package entity;

import java.time.LocalDate;
import java.util.Date;

public class Customer {
	private int idCus;
	private String fullname;
	private boolean gender;
	private String picture;
	private LocalDate dob;
	public Customer() {}
	public Customer(int idCus, String fullname, boolean gender, String picture, LocalDate dob) {
		this.idCus = idCus;
		this.fullname = fullname;
		this.gender = gender;
		this.picture = picture;
		this.dob = dob;
	}
	public int getIdCus() {
		return idCus;
	}
	public void setIdCus(int idCus) {
		this.idCus = idCus;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Customer [idCus=" + idCus + ", fullname=" + fullname + ", gender=" + gender + ", picture=" + picture
				+ ", dob=" + dob + "]";
	}
	
	
}
