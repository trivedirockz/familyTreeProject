package com.ft.familyTree.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ftp_address")
public class MemberAddress {

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="ftp_address_id_sequence", allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	@Column(name = "address_id")
	private Integer addressId;
	
	@NotNull
	@Column(name = "address_line_one")
	private String addressLineOne;
	
	@Column(name = "address_line_two")
	private String addressLineTwo;
	
	@Column(name = "land_mark")
	private String landMark;
	
	@NotNull
	@Column(name = "city")
	private String city;
	
	@NotNull
	@Column(name = "state")
	private String state;
	
	@NotNull
	@Column(name = "country")
	private String country;
	
	@NotNull
	@Column(name = "pincode")
	private String pinCode;
	
	@Column(name = "is_primary")
	private boolean isPrimary;
	
	@JsonIgnore
	@Column(name = "member_id")
	private Integer memberId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Member member;

	public MemberAddress(Integer addressId, String addressLineOne, String addressLineTwo, String landMark, String city,
			String state, String country, String pinCode, boolean isPrimary) {
		super();
		this.addressId = addressId;
		this.addressLineOne = addressLineOne;
		this.addressLineTwo = addressLineTwo;
		this.landMark = landMark;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pinCode = pinCode;
		this.isPrimary = isPrimary;
	}

	public MemberAddress() {
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressLineOne() {
		return addressLineOne;
	}

	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}

	public String getAddressLineTwo() {
		return addressLineTwo;
	}

	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
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

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "MemberAddress [addressId=" + addressId + ", addressLineOne=" + addressLineOne + ", addressLineTwo="
				+ addressLineTwo + ", landMark=" + landMark + ", city=" + city + ", state=" + state + ", country="
				+ country + ", pinCode=" + pinCode + ", isPrimary=" + isPrimary + "]";
	}
}
