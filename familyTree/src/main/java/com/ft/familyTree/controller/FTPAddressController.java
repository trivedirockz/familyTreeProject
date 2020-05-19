package com.ft.familyTree.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ft.familyTree.dto.CommonResponseDTO;
import com.ft.familyTree.dto.Constants;
import com.ft.familyTree.dto.MemberAddress;
import com.ft.familyTree.service.AddressService;

@RestController
@RequestMapping("/api/v2/manageAddress")
public class FTPAddressController {
	
	private Logger vlog;

	@Autowired
	private AddressService addressService;
	
	/**
	 * This method will create new address for the member.
	 * @param memberId - memberId.
	 * @param pAddress - pAddress.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@PostMapping("/addAddress/{memberId}")
	public @ResponseBody CommonResponseDTO addMemberAddress(@PathVariable("memberId") int memberId, @RequestBody MemberAddress pAddress) {
		vlog.debug("FTPAddressController.addMemberAddress() :: START");
		if (memberId == Constants.NUMBER_ZERO) {
			vlog.error("FTPAddressController.addMemberAddress() : Member Id is mandatory");
			throw new IllegalArgumentException("Member Id is mandatory");
		}
		MemberAddress address = addressService.addMemberAddress(memberId, pAddress);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE, "Add Address unsuccessful", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		Optional.of(address).ifPresent(n -> {
			response.setAddress(n);
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.CREATED.toString());
		});
		vlog.debug("FTPAddressController.addMemberAddress() :: END");
		return response;
	}
	
	/**
	 * This method fetches the members primary address.
	 * @param pMemberId - pMemberId.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@GetMapping("/primayAddress/{memberId}")
	public @ResponseBody CommonResponseDTO fetchPrimaryAddres(@PathVariable("memberId") int pMemberId) {
		vlog.debug("FTPAddressController.fetchPrimaryAddres() :: START");
		if (pMemberId == Constants.NUMBER_ZERO) {
			vlog.error("FTPAddressController.fetchPrimaryAddres() : Member Id is mandatory");
			throw new IllegalArgumentException("Member Id is mandatory");
		}
		MemberAddress primaryAddress = addressService.fetchPrimaryMemberAddress(pMemberId);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE,
				"No primary address found for the member id : " + pMemberId,
				HttpStatus.NOT_FOUND.toString());
		Optional.of(primaryAddress).ifPresent(n -> {
			response.setAddress(n);
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		});
		vlog.debug("FTPAddressController.fetchPrimaryAddres() :: END");
		return response;
	}
	
	/**
	 * This method fetches all addresses of the member.
	 * @param pMemberId - pMemberId.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@GetMapping("/allAddresses/{memberId}")
	public @ResponseBody CommonResponseDTO fetchAllAddresses(@PathVariable("memberId") int pMemberId) {
		vlog.debug("FTPAddressController.fetchAllAddresses() :: START");
		if (pMemberId == Constants.NUMBER_ZERO) {
			vlog.error("FTPAddressController.fetchAllAddresses() : Member Id is mandatory");
			throw new IllegalArgumentException("Member Id is mandatory");
		}
		List<MemberAddress> primaryAddress = addressService.fetchAllMemberAddresses(pMemberId);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE,
				"No address found for the member id : " + pMemberId,
				HttpStatus.NOT_FOUND.toString());
		Optional.of(primaryAddress).ifPresent(n -> {
			response.setAddressList(n);
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		});
		vlog.debug("FTPAddressController.fetchAllAddresses() :: END");
		return response;
	}
	
	/**
	 * This method updates the members existing address. 
	 * @param pAddress - pAddress.
	 * @return CommonResponseDTO - CommonResponseDTO.
	 */
	@PutMapping("/updateAddress")
	public @ResponseBody CommonResponseDTO updateAddress(@Valid @RequestBody MemberAddress pAddress) {
		vlog.debug("FTPAddressController.updateAddress() :: START");
		MemberAddress updatedAddress = addressService.updateMemberAddress(pAddress);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE,
				"Update Address failed", HttpStatus.INTERNAL_SERVER_ERROR.toString());
		Optional.of(updatedAddress).ifPresent(n -> {
			response.setAddress(n);
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		});
		vlog.debug("FTPAddressController.updateAddress() :: END");
		return response;
	}
	
	/**
	 * This method deletes the address.
	 * @param pAddressId - pAddressId.
	 * @return deleteAddress - deleteAddress.
	 */
	@DeleteMapping("/deleteAddress/{addressId}")
	public @ResponseBody CommonResponseDTO deleteAddressById(@PathVariable("addressId") int pAddressId) {
		vlog.debug("FTPAddressController.deleteAddressById() :: START");
		if (pAddressId == Constants.NUMBER_ZERO) {
			vlog.error("FTPAddressController.deleteAddressById() : AddressId is mandatory");
			throw new IllegalArgumentException("AddressId is mandatory");
		}
		boolean addressDeleted = addressService.deleteMemberAddressById(pAddressId);
		CommonResponseDTO response = new CommonResponseDTO(Boolean.FALSE,
				"Delete Address failed", HttpStatus.NOT_FOUND.toString());
		if(addressDeleted) {
			response.setSuccess(Boolean.TRUE);
			response.setError(null);
			response.setStatusCode(HttpStatus.OK.toString());
		}
		vlog.debug("FTPAddressController.deleteAddressById() :: END");
		return response;
	}
}
