package com.ft.familyTree.dto;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class Member {

	private Integer memberId;
	@Size(min = 2, message = "First Name should atleast contain 2 characters")
	private String firstName;
	private String middleName;
	@Size(min = 2)
	private String lastName;
	@Past
	private LocalDate dateOfBirth;
	private String occupation;
	private MemberAddress permanentAddress;
	private MemberAddress currentAddress;
	private boolean isCurrentAddressPermanent;
	
	public Member(Integer memberId, String firstName, String middleName, String lastName, LocalDate dateOfBirth,
			String occupation, MemberAddress permanentAddress, MemberAddress currentAddress,
			boolean isCurrentAddressPermanent) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.occupation = occupation;
		this.permanentAddress = permanentAddress;
		this.currentAddress = currentAddress;
		this.isCurrentAddressPermanent = isCurrentAddressPermanent;
	}
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public MemberAddress getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(MemberAddress permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public MemberAddress getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(MemberAddress currentAddress) {
		this.currentAddress = currentAddress;
	}
	public boolean isCurrentAddressPermanent() {
		return isCurrentAddressPermanent;
	}
	public void setCurrentAddressPermanent(boolean isCurrentAddressPermanent) {
		this.isCurrentAddressPermanent = isCurrentAddressPermanent;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", occupation=" + occupation
				+ ", permanentAddress=" + permanentAddress + ", currentAddress=" + currentAddress
				+ ", isCurrentAddressPermanent=" + isCurrentAddressPermanent + "]";
	}

}
