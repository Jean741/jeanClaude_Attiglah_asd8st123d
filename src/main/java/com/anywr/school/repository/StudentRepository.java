package com.anywr.school.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anywr.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}
