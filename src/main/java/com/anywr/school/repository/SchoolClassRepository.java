package com.anywr.school.repository;

import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, UUID> {
    SchoolClass findByName(String studentClass);
}
