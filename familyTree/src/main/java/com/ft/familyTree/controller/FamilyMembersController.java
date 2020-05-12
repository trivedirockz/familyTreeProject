package com.ft.familyTree.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ft.familyTree.dto.CommonResponseDTO;
import com.ft.familyTree.dto.Constants;
import com.ft.familyTree.dto.Member;
import com.ft.familyTree.exception.UserNotFoundException;
import com.ft.familyTree.service.MemberService;

@RestController
@RequestMapping("/api/v2")
public class FamilyMembersController {
	
	@Autowired
	MemberService memberService;

	@GetMapping(path="/getMember/{memberId}")
	public @ResponseBody CommonResponseDTO getMember(@PathVariable(name="memberId") int pMemberId) {
		CommonResponseDTO response = new CommonResponseDTO();
		Member member = memberService.fetchMember(pMemberId);
		if (member == null) {
			response.setSuccess(Boolean.FALSE);
			response.setError(Constants.MEMBER_NOT_FOUND_EXCEPTION + " for the member Id :"+ pMemberId);
			response.setStatusCode(HttpStatus.NOT_FOUND.toString());
			throw new UserNotFoundException(Constants.MEMBER_NOT_FOUND_EXCEPTION + " for the member Id :"+ pMemberId);
		} else {
			response.setSuccess(Boolean.TRUE);
			response.setMember(member);
		}
		return response;
	}
	
	@GetMapping(path="/getAllMembers")
	public @ResponseBody ResponseEntity<Object> getAllMembers() {
		CommonResponseDTO response = new CommonResponseDTO();
		Optional<List<Member>> members = memberService.fetchAllMembers();
		if(!members.get().isEmpty()) {
			response.setMembersList(members.get());
			response.setSuccess(Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			response.setSuccess(Boolean.FALSE);
			response.setError("No members found");
			response.setStatusCode(HttpStatus.NOT_FOUND.toString());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}
	
	@PostMapping(path="/registerMember")
	public @ResponseBody ResponseEntity<Object> registerMember(@Valid @RequestBody Member pMember) {
		Optional<Member> createdMember = memberService.registerMember(pMember);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{memberId}").buildAndExpand(createdMember.get().getMemberId())
				.toUri().toString().replaceAll("/addNewMember", "/getMember");
		URI loc = URI.create(location);
		return ResponseEntity.created(loc).body(createdMember.get());
	}
	
	@DeleteMapping(path="/removeMember/{pMemberId}")
	public @ResponseBody CommonResponseDTO removeMember(@PathVariable int pMemberId) {
		CommonResponseDTO response = new CommonResponseDTO();
		boolean memberRemoved = memberService.removeMember(pMemberId);
		if(memberRemoved) {
			response.setSuccess(Boolean.TRUE);
		} else {
			throw new UserNotFoundException("MemberNotFound for the id :" + pMemberId);
		}
		return response;
	}
	
}
