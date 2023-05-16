package com.anywr.school.repository;

import com.anywr.school.entity.SchoolMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolMemberRepository extends JpaRepository<SchoolMember, UUID> {
}
