package com.ft.familyTree.dto;

public class MemberAddress {

	private String addressId;
	private String doorNumber;
	private String houseName;
	private String streetName;
	private String laneNumber;
	private String landMark;
	private String area;
	private String city;
	private String state;
	private String country;
	private String pinCode;
	
	public MemberAddress(String addressId, String doorNumber, String houseName, String streetName, String laneNumber, String area, String landMark,
			String city, String state, String country, String pinCode) {
		super();
		this.addressId = addressId;
		this.doorNumber = doorNumber;
		this.houseName = houseName;
		this.streetName = streetName;
		this.laneNumber = laneNumber;
		this.landMark = landMark;
		this.area = area;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
	}
	public String getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLaneNumber() {
		return laneNumber;
	}
	public void setLaneNumber(String laneNumber) {
		this.laneNumber = laneNumber;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	@Override
	public String toString() {
		return "MemberAddress [addressId=" + addressId + ", doorNumber=" + doorNumber + ", houseName=" + houseName
				+ ", streetName=" + streetName + ", laneNumber=" + laneNumber + ", landMark=" + landMark + ", area="
				+ area + ", city=" + city + ", state=" + state + ", country=" + country + ", pinCode=" + pinCode + "]";
	}
	
}
