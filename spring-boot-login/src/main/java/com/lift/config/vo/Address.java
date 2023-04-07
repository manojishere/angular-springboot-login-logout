package com.lift.config.vo;

public class Address {
	
	private int addressId;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private int postalCode;
	
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
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
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", address1=" + address1 + ", address2=" + address2 + ", city="
				+ city + ", state=" + state + ", postalCode=" + postalCode + "]";
	}

}
