package com.ft.familyTree.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ft.familyTree.dto.Member;
import com.ft.familyTree.exception.NotFoundException;
import com.ft.familyTree.repository.MemberRepository;

@Component
@Transactional
public class MemberService {
	
	private Logger vlog;

	@Autowired
	private MemberRepository memberRepo;
	
	/**
	 * This method returns all the members registered.
	 * @return List of members.
	 */
	public Optional<List<Member>> fetchAllMembers() {
		vlog.info("Fetching all members");
		return Optional.ofNullable(memberRepo.findAll());
	}
	
	/**
	 * This method registers a new member in family tree.
	 * @param pMember - pMember.
	 * @return same member registered.
	 */
	public Optional<Member> registerMember(Member pMember) {
		Optional<Member> opt = Optional.ofNullable(memberRepo.save(pMember));
		vlog.info("New member created with id : "+ opt.get().getMemberId());
		return opt;
	}
	
	/**
	 * This method fetches the member details for the provided member Id.
	 * @param id - id.
	 * @return member for the given id.
	 */
	public Member fetchMember(int memberId) {
		Optional<Member> member = memberRepo.findById(memberId);
		if (!member.isPresent()) {
			vlog.info("Member not found for the id : " + memberId);
			throw new NotFoundException("Member not found for the id : " + memberId);
		}
		return member.get();
	}
	
	/**
	 * This method removes the member for the provided member id.
	 * @param id - memberId.
	 * @return true if user identified and removed. Else throw UserNotFoundException.
	 */
	public boolean removeMember(int id) {
		Optional<Member> member = memberRepo.findById(id);
		if (!member.isPresent()) {
			vlog.info("No member found for the id : "+id);
			return Boolean.FALSE;
		}
		memberRepo.deleteById(id);
		vlog.info("Member deleted from DB with id : "+id);
		return Boolean.TRUE;
	}
	
	/**
	 * This method updates member in family tree.
	 * @param pMember - pMember.
	 * @return same member updated.
	 */
	public Optional<Member> updateMember(Member pMember) {
		Optional<Member> opt = Optional.ofNullable(memberRepo.save(pMember));
		opt.ifPresent(n -> vlog.info("Member updated with id : "+ opt.get().getMemberId()));
		return opt;
	}
}
