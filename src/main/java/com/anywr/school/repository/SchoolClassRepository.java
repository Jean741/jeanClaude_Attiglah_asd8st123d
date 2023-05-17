package com.anywr.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.school.entity.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, UUID> {
    SchoolClass findByName(String studentClass);
}
