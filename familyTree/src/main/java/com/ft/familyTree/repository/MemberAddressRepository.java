package com.ft.familyTree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ft.familyTree.dto.MemberAddress;

@Repository
public interface MemberAddressRepository extends JpaRepository<MemberAddress, Integer> {

	@Query("SELECT u FROM MemberAddress u WHERE u.memberId = :memberId and u.isPrimary = :isPrimary")
	MemberAddress findPrimaryMemberAddress(@Param("memberId") Integer memberId, 
	  @Param("isPrimary") boolean isPrimary);
}

