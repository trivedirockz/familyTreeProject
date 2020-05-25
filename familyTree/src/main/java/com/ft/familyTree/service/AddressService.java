package com.ft.familyTree.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ft.familyTree.dto.Member;
import com.ft.familyTree.dto.MemberAddress;
import com.ft.familyTree.exception.NotFoundException;
import com.ft.familyTree.repository.MemberAddressRepository;

@Component
@Transactional
public class AddressService {

	final static Logger vlog = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	private MemberAddressRepository addressRepo;
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * This method first validates the member details for the memberId provided and
	 * then creates an address for the same member with the given details.
	 * 
	 * @param memberId - memberId.
	 * @param address - address.
	 * @return memberAddress - memberAdress.
	 */
	public MemberAddress addMemberAddress(int memberId, MemberAddress address) {
		Member member = memberService.fetchMember(memberId);
		address.setMember(member);
		List<MemberAddress> addressList = member.getAddressList();
		if (!CollectionUtils.isEmpty(addressList) && address.isPrimary()) {
			addressList.forEach(y -> {
				if (y.isPrimary()) {
					y.setPrimary(Boolean.FALSE);
					addressRepo.save(y);
				}
			});
		}
		MemberAddress memberAddress = addressRepo.save(address);
		if (memberAddress != null) {
			vlog.info("Address created with Id : "+memberAddress.getAddressId()+" for the member : "+memberId);
		}
		return memberAddress;
	}
	
	/**
	 * This method fetches the primary address of the member.
	 * @param memberId - memberId.
	 * @return memberAddress - primaryAddress.
	 */
	public MemberAddress fetchPrimaryMemberAddress(int memberId) {
		memberService.fetchMember(memberId);
		MemberAddress primaryAddress = addressRepo.findPrimaryMemberAddress(memberId, Boolean.TRUE);
		return primaryAddress;
	}
	
	/**
	 * This method fetches the addresses of the member.
	 * @param memberId - memberId.
	 * @return addressList - addressList.
	 */
	public List<MemberAddress> fetchAllMemberAddresses(int memberId) {
		Member member = memberService.fetchMember(memberId);
		List<MemberAddress> addressList = member.getAddressList();
		if (CollectionUtils.isEmpty(addressList)) 
			throw new NotFoundException("No address found for the member with id : "+memberId);
		vlog.info("AddressList for the member with id : "+memberId+" is : "+addressList);
		return addressList;
	}
	
	/**
	 * This method updates the address.
	 * @param address - address.
	 * @return memberAddress - memberAdress.
	 */
	public MemberAddress updateMemberAddress(MemberAddress address) {
		address = addressRepo.save(address);
		vlog.info("Address updated with id : "+address.getAddressId());
		return address;
	}
	
	/**
	 * This method deletes the address.
	 * @param boolean - boolean.
	 */
	public boolean deleteMemberAddressById(int addressId) {
		boolean result = Boolean.TRUE;
		Optional<MemberAddress> address = addressRepo.findById(addressId);
		if(!address.isPresent()) {
			result = Boolean.FALSE;
			vlog.info("No address found for the address with id : "+addressId);			
			throw new NotFoundException("No address found for the address with id : "+addressId);
		} else {
			addressRepo.deleteById(addressId);;
			vlog.info("Address deleted with id : "+addressId);
		}
		return result;
	}
}
