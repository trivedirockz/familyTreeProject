package com.ft.familyTree.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ft.familyTree.dto.Member;
import com.ft.familyTree.dto.MemberAddress;

@Component
public class MemberService {

	private static List<Member> members = new ArrayList<>();
	private static int noOfMembers = 2;
	static {
		members.add(new Member(1, "PRATAP", "TRIVEDI", "BICCAVOLE", LocalDate.of(1991,2,5), "SOFTWARE EMPLOYEE", new MemberAddress("ad1","A5", "Latha Residency", "Manjunatha Layout", "4", "Near Saibaba Temple", "Munnekolala", "Bengaluru", "Karnataka", "India", "560037"), null, Boolean.FALSE));
		members.add(new Member(2, "LAKSHMI", "GAYATRI", "BICCAVOLE", LocalDate.of(1992,2,14), "SOFTWARE EMPLOYEE", new MemberAddress("ad2","A5", "Latha Residency", "Manjunatha Layout", "4", "Near Saibaba Temple", "Munnekolala", "Bengaluru", "Karnataka", "India", "560037"), null, Boolean.FALSE));
	}
	
	/**
	 * This method returns all the members registered.
	 * @return List of members.
	 */
	public Optional<List<Member>> fetchAllMembers() {
		return Optional.ofNullable(members);
	}
	
	/**
	 * This method registers a new member in family tree.
	 * @param pMember - pMember.
	 * @return same member registered.
	 */
	public Optional<Member> registerMember(Member pMember) {
		if(pMember.getMemberId() == null) {
			pMember.setMemberId(++noOfMembers);
		}
		members.add(pMember);
		Optional<Member> opt = Optional.ofNullable(pMember);
		return opt;
	}
	
	/**
	 * This method fetches the member details for the provided member Id.
	 * @param id - id.
	 * @return member for the given id.
	 */
	public Member fetchMember(int id) {
		Member member = members.stream().filter(y -> y.getMemberId() == id).findAny().orElse(null);
		return member;
	}
	
	/**
	 * This method removes the member for the provided member id.
	 * @param id - memberId.
	 * @return true if user identified and removed. Else throw UserNotFoundException.
	 */
	public boolean removeMember(int id) {
		int noOfMembersBefore = noOfMembers;
		members.removeIf(x -> x.getMemberId() == id);
		noOfMembers = members.size();
		boolean result = noOfMembersBefore > noOfMembers;
		return result;
	}
}
