package com.ft.familyTree.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ft.familyTree.dto.Member;
import com.ft.familyTree.dto.MemberAddress;
import com.ft.familyTree.exception.UserNotFoundException;

@Component
public class MemberService {

	private static List<Member> members = new ArrayList<>();
	private static int noOfMembers = 2;
	static {
		//members.add(new Member(1, "PRATAP", "TRIVEDI", "BICCAVOLE", LocalDate.of(1991,2,5), "SOFTWARE EMPLOYEE", new MemberAddress("ad1","A5", "Latha Residency", "Manjunatha Layout", "4", "Near Saibaba Temple", "Munnekolala", "Bengaluru", "Karnataka", "India", "560037"), null, Boolean.FALSE));
		//members.add(new Member(2, "LAKSHMI", "GAYATRI", "BICCAVOLE", LocalDate.of(1992,2,14), "SOFTWARE EMPLOYEE", new MemberAddress("ad2","A5", "Latha Residency", "Manjunatha Layout", "4", "Near Saibaba Temple", "Munnekolala", "Bengaluru", "Karnataka", "India", "560037"), null, Boolean.FALSE));
	}
	
	public Optional<List<Member>> fetchAllMembers() {
		return Optional.ofNullable(members);
	}
	
	public Optional<Member> addMember(Member pMember) {
		if(pMember.getMemberId() == null) {
			pMember.setMemberId(++noOfMembers);
		}
		members.add(pMember);
		Optional<Member> opt = Optional.ofNullable(pMember);
		return opt;
	}
	
	public Member fetchMember(int id) {
		Member member = members.stream().filter(y -> y.getMemberId() == id).findAny().orElse(null);
		return member;
	}
	
	public boolean removeMember(int id) {
		int noOfMembersBefore = noOfMembers;
		members.stream()
		.forEach(x -> {
			if (x.getMemberId() == id) {
				members.remove(x);
				noOfMembers--;
			}
		});
		boolean result = noOfMembersBefore > noOfMembers;
		if (!result) throw new UserNotFoundException("MemberNotFound for the id :" + id);
		return result;
	}
}
