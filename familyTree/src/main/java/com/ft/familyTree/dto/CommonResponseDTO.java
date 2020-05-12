package com.ft.familyTree.dto;

import java.util.List;

import org.springframework.http.ResponseEntity;

public class CommonResponseDTO extends BaseResponseDTO {
	
	private Member member;
	private List<Member> membersList;
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
	

}
