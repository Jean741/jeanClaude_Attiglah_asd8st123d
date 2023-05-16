package com.anywr.school.repository;

import com.anywr.school.entity.SchoolClass;
import com.anywr.school.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Page<Student> findByStudentClass(Pageable pageable, SchoolClass schoolClass);
}
