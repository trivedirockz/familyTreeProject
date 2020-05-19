package com.ft.familyTree.dto;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CommonResponseDTO extends BaseResponseDTO {
	
	public CommonResponseDTO() {
		super();
	}
	public CommonResponseDTO(boolean success, String error, String statusCode) {
		super(success, error, statusCode);
	}
	private Member member;
	private MemberAddress address;
	private List<Member> membersList;
	private List<MemberAddress> addressList;
	private ResponseEntity<Object> responseEntity;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public List<Member> getMembersList() {
		return membersList;
	}
	public void setMembersList(List<Member> membersList) {
		this.membersList = membersList;
	}
	public ResponseEntity<Object> getResponseEntity() {
		return responseEntity;
	}
	public void setResponseEntity(ResponseEntity<Object> responseEntity) {
		this.responseEntity = responseEntity;
	}
	public List<MemberAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<MemberAddress> addressList) {
		this.addressList = addressList;
	}
	public MemberAddress getAddress() {
		return address;
	}
	public void setAddress(MemberAddress address) {
		this.address = address;
	}
}
