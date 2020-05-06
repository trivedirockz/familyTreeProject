package com.ft.familyTree.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ft.com.ft.familyTree.exception.FTApplicationException;
import com.ft.familyTree.dto.Constants;
import com.ft.familyTree.dto.Member;
import com.ft.familyTree.service.MemberService;

@RestController
@RequestMapping("/api/v2")
public class FamilyMembersController {
	
	@Autowired
	MemberService memberService;

	@GetMapping(path="/getMember/{memberId}")
	public Member getMember(@PathVariable(name="memberId") int pMemberId) {
		Member member = memberService.fetchMember(pMemberId);
		if (member == null) {
			throw new FTApplicationException(Constants.MEMBER_NOT_FOUND_EXCEPTION + " for the member Id :"+ pMemberId);
		}
		return member;
	}
	
	@GetMapping(path="/getAllMembers")
	public List<Member> fetchAllMembers() {
		List<Member> members = memberService.fetchAllMembers();
		return members;
	}
	
	@PostMapping(path="/addNewMember")
	public @ResponseBody ResponseEntity<Object> addNewMember(@RequestBody Member pMember) {
		Optional<Member> createdMember = memberService.addMember(pMember);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{memberId}").buildAndExpand(createdMember.get().getMemberId())
				.toUri().toString().replaceAll("/addNewMember", "/getMember");
		URI loc = URI.create(location);
		return ResponseEntity.created(loc).body(createdMember.get());
	}
}
