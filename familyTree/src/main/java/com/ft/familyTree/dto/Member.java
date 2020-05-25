package com.ft.familyTree.dto;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ftp_member")
public class Member {

	@Id
	@SequenceGenerator(name="seq-gen",sequenceName="ftp_member_id_sequence", allocationSize=1)
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="seq-gen")
	@Column(name = "member_id")
	private Integer memberId;
	
	@NotNull
	@Size(min = 2, message = "First Name should atleast contain 2 characters")
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@NotNull
	@Size(min = 2)
	@Column(name = "last_name")
	private String lastName;
	
	@Past
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name = "occupation")
	private String occupation;
	
	@Size(min = 10, max = 10, message = "Mobile number should contian exactly 10 digits")
	@Column(name = "mobile_number")
	private String mobileNumber;
	
	@Email(message = "Email should be valid")
	@Column(name = "email")
	private String email;
	
	@Size(min = 4, message = "Password should contain minimum 4 characters")
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "member")
	@JsonIgnore
	private List<MemberAddress> addressList;
	
	public Member() {
	}

	public Member(Integer memberId,
			@Size(min = 2, message = "First Name should atleast contain 2 characters") String firstName,
			String middleName, @Size(min = 2) String lastName, @Past LocalDate dateOfBirth, String occupation,
			String mobileNumber, String email, String password) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.occupation = occupation;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
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
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<MemberAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<MemberAddress> addressList) {
		this.addressList = addressList;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", occupation=" + occupation
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", password=" + password + "]";
	}


}
