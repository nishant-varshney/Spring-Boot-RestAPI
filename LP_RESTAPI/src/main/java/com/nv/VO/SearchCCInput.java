package com.nv.VO;

public class SearchCCInput {
	
	private String name;
	private String email;
	private String pincode;
	private String city;
	private String state;
	private String phoneno;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
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
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
	public SearchCCInput(String name, String email, String pincode,
			String city, String state, String phoneno) {
		super();
		this.name = name;
		this.email = email;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.phoneno = phoneno;
	}
	

	
	
}
