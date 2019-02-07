package com.nv.Model;

public class SearchCC {
	
	private Integer coachingID;
	
	private String name;
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String phoneNo;
	
	private String address1;
	private String address2;
	
	
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	
	
	
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	
	
	
	public Integer getCoachingID() {
		return coachingID;
	}
	public void setCoachingID(Integer coachingID) {
		this.coachingID = coachingID;
	}
	
	public SearchCC(String name, String email, String pincode, String city,
			String state, String phoneNo) {
		super();
		this.name = name;
		this.email = email;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.phoneNo = phoneNo;
	}
	
	
	
	public SearchCC(Integer coachingID, String name, String email, String phoneNo,String address1, String address2,
			String pincode, String city, String state) {
		super();
		this.coachingID = coachingID;
		this.name = name;
		this.email = email;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.phoneNo = phoneNo;
		this.address1 = address1;
		this.address2 = address2;
	}
	
	
	public SearchCC() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
