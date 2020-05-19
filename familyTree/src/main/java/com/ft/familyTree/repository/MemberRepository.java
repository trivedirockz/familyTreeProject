package com.ft.familyTree.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ft.familyTree.dto.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}

