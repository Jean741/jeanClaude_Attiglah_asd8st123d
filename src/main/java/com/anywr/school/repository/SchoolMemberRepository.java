package com.anywr.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.school.entity.SchoolMember;

public interface SchoolMemberRepository extends JpaRepository<SchoolMember, UUID> {
}
