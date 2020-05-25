package com.ft.familyTree.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ft.familyTree.dto.CommonResponseDTO;
import com.ft.familyTree.dto.Member;
import com.ft.familyTree.exception.NotFoundException;
import com.ft.familyTree.service.MemberService;

@RestController
@RequestMapping("/api/v2/manageMember")
public class FamilyMembersController {
	
	final static Logger vlog = LoggerFactory.getLogger(FamilyMembersController.class);
	
	@Autowired
	MemberService memberService;
	
	/**
	 * This method will fetch the member details based on memberId.
	 * @param pMemberId - pMemberId.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@GetMapping(path="/getMember/{memberId}")
	public @ResponseBody CommonResponseDTO getMember(@PathVariable(name="memberId") int pMemberId) {
		vlog.debug("FamilyMembersController.getMember() :: START");
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE, "Member not found for with id : "+pMemberId, HttpStatus.NOT_FOUND.toString());
		Member member = memberService.fetchMember(pMemberId);
		Optional.of(member).ifPresent(y -> {
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setMember(member);
			response.setStatusCode(HttpStatus.CREATED.toString());
		});
		vlog.debug("FamilyMembersController.getMember() :: END");
		return response;
	}
	
	/**
	 * This method will fetch all members registered.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@GetMapping(path="/getAllMembers")
	public @ResponseBody CommonResponseDTO getAllMembers() {
		vlog.debug("FamilyMembersController.getAllMembers() :: START");
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE, "No data found", HttpStatus.NOT_FOUND.toString());
		Optional<List<Member>> members = memberService.fetchAllMembers();
		members.ifPresent(y -> {
			response.setMembersList(members.get());
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		});
		vlog.debug("FamilyMembersController.getAllMembers() :: END");
		return response;
	}
	
	/**
	 * This method registers a new member.
	 * @param pMember - pMember.
	 * @return ResponseEntity - ResponseEntity.
	 */
	@PostMapping(path="/registerMember")
	public @ResponseBody ResponseEntity<Object> registerMember(@Valid @RequestBody Member pMember) {
		Optional<Member> createdMember = memberService.registerMember(pMember);
		String location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{memberId}").buildAndExpand(createdMember.get().getMemberId())
				.toUri().toString().replaceAll("/addNewMember", "/getMember");
		URI loc = URI.create(location);
		return ResponseEntity.created(loc).body(createdMember.get());
	}
	
	/**
	 * This method updates member details.
	 * @param pMember - pMember.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@PutMapping(path="/updateMember")
	public @ResponseBody CommonResponseDTO updateMember(@Valid @RequestBody Member pMember) {
		Optional<Member> updatedMember = memberService.updateMember(pMember);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE,
				"Delete Address failed", HttpStatus.NOT_FOUND.toString());
		Optional.of(updatedMember).ifPresent(n -> {
			response.setMember(n.get());
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		});
		return response;
	}
	
	/**
	 * This method removes a member.
	 * @param pMemberId - pMemberId.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@DeleteMapping(path="/removeMember/{pMemberId}")
	public @ResponseBody CommonResponseDTO removeMember(@PathVariable int pMemberId) {
		CommonResponseDTO response = new CommonResponseDTO();
		boolean memberRemoved = memberService.removeMember(pMemberId);
		if(memberRemoved) {
			response.setSuccess(Boolean.TRUE);
		} else {
			throw new NotFoundException("MemberNotFound for the id :" + pMemberId);
		}
		return response;
	}
	
}
