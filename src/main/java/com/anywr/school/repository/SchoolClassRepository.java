package com.anywr.school.repository;

import com.anywr.school.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, UUID> {
    SchoolClass findByName(String studentClass);
    
	SchoolClass findByTeacher(String studentClass);
}
